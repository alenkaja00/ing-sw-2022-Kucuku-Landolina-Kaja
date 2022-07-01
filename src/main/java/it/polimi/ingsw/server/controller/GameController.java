package it.polimi.ingsw.server.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.server.Logger;
import it.polimi.ingsw.server.model.cards.EffectCard;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Player;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import it.polimi.ingsw.server.model.gameClasses.GameClassExpert;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class GameController is the main controller class, it handles the phases of the game and coordinates them
 * the phases are the wizard selection, the pianification, the action , the effects, the game ended
 * it also handles the reconnection / disconnection of a player
 */
public class GameController
{
    private GameClass newGame;
    private Boolean gameEnded = false;
    private boolean expertMode;
    private String ID;
    private int playerNumber;
    private ArrayList<String> players;
    private HashMap<String, Boolean> playersOnline;
    private HashMap<String, ClientManager> playerSockets;
    private ArrayList<Map.Entry<String, Integer>> playerOrder;
    private HashMap<String, Wizard> playerWizards = new HashMap<>();;
    private ArrayList<ArrayList<String>> inputBuffer = new ArrayList<ArrayList<String>>();

    /**
     * Constructor instantiates a thread which manages the game calling other methods of the class
     * it establishes the turn logic by calling the phases of the game
     * @param playerNumber number of players
     * @param expertMode true if the game is in the expert variant
     * @param ID the identifier of the game Controller
     * @param nicknames list of player's nicknames
     * @param playerSockets hashmap of nicknames and client manager classes
     */
    public GameController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, HashMap<String, ClientManager> playerSockets)
    {
        new Thread(()->
        {
            this.ID = ID;
            this.players = nicknames;
            this.playersOnline = new HashMap<String, Boolean>();
            players.stream().forEach(x->playersOnline.put(x, true));
            this.playerSockets = playerSockets;
            this.playerOrder = new ArrayList<>();
            this.expertMode = expertMode;
            this.playerNumber = playerNumber;


            //create a random round order the first time
            players.stream().forEach(x->playerOrder.add(Map.entry(x, new Random().nextInt(playerNumber))));
            orderByValue(playerOrder);

            //get wizards from players && communicate with them
            acceptWizards();

            //order wizards according to the player who chose them
            ArrayList<Wizard> orderedWizards = new ArrayList<>();
            players.stream().forEach(x->orderedWizards.add(playerWizards.get(x)));

            //create gameClass or GameClassExpert
            if (expertMode)
                newGame = new GameClassExpert(ID, playerNumber, players, orderedWizards);
            else
                newGame = new GameClass(ID, playerNumber, players, orderedWizards);

            do {
                if (!gameEnded)
                {
                    //updateView();
                    newGame.BagToCloud();
                    updateView();
                }

                if (!gameEnded)
                    pianificationPhase();

                if (!gameEnded)
                    actionPhase();
            }while (true);
        }
        ).start();

    }

    /**
     * @return true if the game has ended
     */
    public boolean getGameEnded()
    {
        synchronized (gameEnded)
        {
            return gameEnded;
        }
    }

    /**
     * this method accepts the player's wizard choice
     * if a player disconnects in this phase, a random wizard will be associated to him
     */
    private void acceptWizards()
    {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    if (playerWizards.size()<playerNumber && players.stream().filter(x->playersOnline.get(x)).count() == 0)
                }
            }
        }).start();*/
        while (playerWizards.size()<playerNumber)
        {
            ArrayList<String> message = nextMessage("");
            //System.out.println("out of the next");

            if (!message.get(0).equals("DISCONNECTED"))
            {
                if (message.get(0).equals("PLAY") &&  !playerWizards.keySet().contains(message.get(1)) &&
                        message.get(2).equals("WIZARD") && !playerWizards.values().contains(Wizard.valueOf(message.get(3))))
                {
                    //System.out.println("received "+ message.get(3));
                    playerSockets.get(message.get(1)).sendMessage("OK");
                    //updateView(message.get(1));
                    playerWizards.put(message.get(1), Wizard.valueOf(message.get(3)));
                }
                else
                {
                    playerSockets.get(message.get(1)).sendMessage("NOK");
                }
            }
            //add a random wizard if a player disconnects during wizard choice
            for (int i=0; i<playerNumber; i++)
            {
                if (!playersOnline.get(players.get(i)) && !playerWizards.keySet().contains(players.get(i)))
                {
                    Logger.storeLog("Selecting a random wizard for a disconnected player");
                    playerWizards.put(players.get(i), Arrays.stream(Wizard.values()).filter(x->!playerWizards.values().contains(x)).collect(Collectors.toList()).get(0) );
                }
            }
        }
    }

    /**
     * this method handles the planification phase: each player has to play his helper card, so the method will accept the request of the player and send OK in case of success
     * in case of not success it will send NOK
     * the method also sends LOCK/UNLOCK messages to the client
     * based on the card value played it orders the players in the round
     */
    private void pianificationPhase()
    {
        players.stream().filter(x-> !playersOnline.get(x)).forEach(x->playerDisconnected(x));
        if (gameEnded) return;
        updateView();
        Logger.storeLog("Entering pianification phase");
        playerOrder.removeAll(playerOrder);
        //pianification phase
        for (int i = 0; i<players.size(); i++)
        {
            String currentPlayer = players.get(i);
            if (!playersOnline.get(currentPlayer))
                continue;
            playerSockets.get(currentPlayer).sendMessage("UNLOCK");
            //System.out.println(playerOrder);

            do {
                ArrayList<String> message = nextMessage(currentPlayer);
                if (message.get(0).equals("DISCONNECTED")) break;
                if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("HELPER"))
                {
                    try {
                        if (!playerOrder.stream().map(x->x.getValue()).collect(Collectors.toList()).contains(Integer.parseInt(message.get(3)))
                                || newGame.getPlayers().stream().filter(x->x.getNickname().equals(message.get(1))).collect(Collectors.toList())
                                .get(0).deck.returnUnused().size() == 1)
                        {
                            newGame.useHelperCard(i, Integer.parseInt(message.get(3)));
                            playerSockets.get(message.get(1)).sendMessage("OK");
                            playerOrder.add(Map.entry(message.get(1) , Integer.valueOf(message.get(3))));
                            //playerOrder.stream().filter(x -> x.getValue().equals(currentPlayer)).forEach(x -> x.setValue(Integer.valueOf(message.get(3))));
                            playerSockets.get(currentPlayer).sendMessage("LOCK");
                            updateView();
                            break;
                        }
                        else
                        {
                            playerSockets.get(message.get(1)).sendMessage("NOK");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        playerSockets.get(message.get(1)).sendMessage("NOK");
                    }
                }
                else
                {
                    playerSockets.get(currentPlayer).sendMessage("NOK");
                }
            } while (true);
        }

        orderByValue(playerOrder);
    }

    /**
     * this method handles the action phase where the player has to accomplish many steps: the method accepts the requests of ETI|ETT moves, NATURE moves or, CTE moves
     * in addition it calls the method manage effect in case of expert variant of the game
     * if the action is permitted or not it will send OK/NOK and LOCK/UNLOCK the interaction
     */
    private void actionPhase()
    {
        //action phase
        for (int i=0; i<playerOrder.size(); i++)
        {
            String currentPlayer = playerOrder.get(i).getKey();

            if (!playersOnline.get(currentPlayer) || playerOrder.get(i).getValue()<0)
                continue;

            playerSockets.get(currentPlayer).sendMessage("UNLOCK");


            //ETT OR ETI
            for (int n=0; n<newGame.getClouds().get(0).getCloudCapacity(); n++)
            {
                do
                {
                    ArrayList<String> message = nextMessage(currentPlayer);
                    if (message.get(0).equals("DISCONNECTED")) break;
                    if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("ETI"))
                    {
                        try {
                            newGame.EntranceToIsland(players.indexOf(currentPlayer), Integer.parseInt(message.get(3)), Integer.parseInt(message.get(4)));
                            playerSockets.get(currentPlayer).sendMessage("OK");
                            updateView();
                            break;
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            playerSockets.get(currentPlayer).sendMessage("NOK");
                        }
                    }
                    else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("ETT"))
                    {
                        try {
                            newGame.EntranceToTables(players.indexOf(currentPlayer), Integer.parseInt(message.get(3)));
                            playerSockets.get(currentPlayer).sendMessage("OK");
                            updateView();
                            break;
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            playerSockets.get(currentPlayer).sendMessage("NOK");
                        }
                    }
                    else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFFECT"))
                    {
                        try
                        {
                            manageEffect(players.indexOf(currentPlayer), message);
                        } catch (Exception e)
                        {
                            playerSockets.get(currentPlayer).sendMessage("NOK");
                        }
                    }
                    else
                    {
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }
                } while (true);
            }
            //updateView();
            if (!playersOnline.get(currentPlayer))
                continue;


            //MOVE MOTHER NATURE
            do
            {
                ArrayList<String> message = nextMessage(currentPlayer);
                if (message.get(0).equals("DISCONNECTED")) break;
                if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("NATURE"))
                {
                    try {
                        if (Integer.parseInt(message.get(3))<=newGame.playerMaxMoves(players.indexOf(currentPlayer)))
                        {
                            try
                            {
                                newGame.MoveMotherNature(Integer.parseInt(message.get(3)));
                                playerSockets.get(currentPlayer).sendMessage("OK");
                                break;
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                playerSockets.get(currentPlayer).sendMessage("NOK");
                            }
                        }
                        else
                        {
                            playerSockets.get(currentPlayer).sendMessage("NOK");
                        }
                    }catch(Exception e){
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }

                }
                else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFFECT"))
                {
                    try
                    {
                        manageEffect(players.indexOf(currentPlayer), message);
                    } catch (Exception e)
                    {
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }
                }
                else
                {
                    playerSockets.get(currentPlayer).sendMessage("NOK");
                }
            } while (true);
            updateView();
            if (newGame.towerGameEnded())
            {
                gameEnded(players.indexOf(currentPlayer));
                break;
            }
            if (newGame.islandsGameEnded())
            {
                gameEnded(newGame.lessTowersMoreProfessors());
                break;
            }
            if (!playersOnline.get(currentPlayer))
                continue;

            //CLOUD TO ENTRANCE
            do
            {
                ArrayList<String> message = nextMessage(currentPlayer);
                if (message.get(0).equals("DISCONNECTED")) break;
                if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("CTE"))
                {
                    try {
                        newGame.CloudToEntrance(Integer.parseInt(message.get(3)), players.indexOf(currentPlayer));
                        playerSockets.get(currentPlayer).sendMessage("OK");
                        break;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }
                }
                else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFFECT"))
                {
                    try
                    {
                        manageEffect(players.indexOf(currentPlayer), message);
                    } catch (Exception e)
                    {
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }
                }
                else
                {
                    playerSockets.get(currentPlayer).sendMessage("NOK");
                }
            } while (true);
            if (newGame.roundGameEnded())
            {
                gameEnded(newGame.lessTowersMoreProfessors());
                break;
            }
            playerSockets.get(currentPlayer).sendMessage("LOCK");
            updateView();
        }
        if (expertMode)
            ((GameClassExpert)newGame).endCardEffect();
    }

    /**
     * this method is called if an effect card message is received
     * based on the requested effect it applies it by calling the GameClass
     * @param playerID the identifier of the player who requested the effect
     * @param message the message string which contains the request
     */
    private void manageEffect(int playerID, ArrayList<String> message)
    {
        if (!expertMode) {
            Logger.storeLog("ERROR. Trying to play effect card in non expert game.");
            return;
        }


        /*Player currentPlayer = newGame.getPlayers().stream().filter(x->x.getID()==playerID).collect(Collectors.toList()).get(0);
        int PlayerCoins = currentPlayer.getCoinsAmount();
        int cardPrice = ((GameClassExpert)newGame).getCardPrices().get(message.get(3));
        if */

        Player currentPlayer = newGame.getPlayers().stream().filter(x->x.getID()==playerID).collect(Collectors.toList()).get(0);
        int PlayerCoins = currentPlayer.getCoinsAmount();
        EffectCard requestedEffect = ((GameClassExpert)newGame).getEffectCards().stream().filter(x->x.getID().toString().equals(message.get(3))).collect(Collectors.toList()).get(0);
        if (PlayerCoins<requestedEffect.getPrice())
        {
            playerSockets.get(players.get(playerID)).sendMessage("NOK");
            return;
        }

        switch (message.get(3))
        {
            case "CAVALIER":
            case "CENTAUR":
            case "VILLAIN":
            case "MAGICIAN":
                ((GameClassExpert)newGame).useCardEffect(playerID, EffectName.valueOf(message.get(3)));
                break;
            case "MONK":
                ((GameClassExpert)newGame).monkEffect(Integer.parseInt(message.get(4)), Integer.parseInt(message.get(5)));
                break;
            case "QUEEN":
                ((GameClassExpert)newGame).queenEffect(playerID, Integer.parseInt(message.get(4)));
                break;
            case "LADY":
                ((GameClassExpert)newGame).ladyEffect(Integer.parseInt(message.get(4)));
                break;
            case "JOLLY":
                ((GameClassExpert)newGame).jollyEffectCall(playerID, Integer.parseInt(message.get(4)), Integer.parseInt(message.get(5)));
                if (message.size()>6)
                    ((GameClassExpert)newGame).jollyEffectCall(playerID, Integer.parseInt(message.get(6)), Integer.parseInt(message.get(7)));
                if (message.size()>8)
                    ((GameClassExpert)newGame).jollyEffectCall(playerID, Integer.parseInt(message.get(8)), Integer.parseInt(message.get(9)));
                break;
            case "LORD":
                ((GameClassExpert)newGame).lordEffect(Integer.parseInt(message.get(4)));
                break;
            case "COOK":
                ((GameClassExpert)newGame).cookEffect(ColoredDisc.valueOf(message.get(4)));
                break;
            case "BANDIT":
                ((GameClassExpert)newGame).banditEffect(ColoredDisc.valueOf(message.get(4)));
                break;
            case "MUSICIAN":
                ((GameClassExpert)newGame).musicianEffect(playerID, Integer.parseInt(message.get(4)), ColoredDisc.valueOf(message.get(5)));
                if (message.size()>6)
                    ((GameClassExpert)newGame).musicianEffect(playerID, Integer.parseInt(message.get(6)), ColoredDisc.valueOf(message.get(7)));
                break;
        }

        currentPlayer.spendCoins(requestedEffect.getPrice());
        requestedEffect.useCard();
        playerSockets.get(players.get(playerID)).sendMessage("OK");
        updateView();
    }

    /**
     * if the game has ended it will send to all the connected player a message containing the winner's nickname
     * @param playerID identifier of the winner
     */
    private void gameEnded(int playerID)
    {
        synchronized (gameEnded)
        {
            gameEnded = true; //the object will be deleted by his creator
        }
        players.stream().forEach(x->{
            if (playerID != -1 && playerSockets.get(x) != null)
                playerSockets.get(x).sendMessage("WINNER|"+players.get(playerID));
        });
    }

    /**
     * this method is responsible for sending to the sockets the json containing the game data
     * it is called several times by other methods, for example after a movement or after accomplishing a task
     */
    private void updateView()
    {
        Gson gson = new Gson();
        String result;
        if (expertMode)
            result = gson.toJson((GameClassExpert) newGame);
        else
            result = gson.toJson(newGame);
        players.stream().filter(x->playersOnline.get(x)).forEach(x->playerSockets.get(x).sendMessage("JSON|"+result));
    }

    /*    private ArrayList<String> nextMessage()
        {
            ArrayList<String> message = new ArrayList<>();

            do
            {
                synchronized (inputBuffer)
                {
                    if (inputBuffer.size()>0)
                    {
                        message=inputBuffer.remove(0);
                        inputBuffer.notifyAll();
                    }
                }
                if (message.size()>0)
                    return (ArrayList<String>) message.clone();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);

        }*/

    /**
     * this method extracts from the buffer the first message that has to be evaluated
     * @param playerNickname nickname of the player who is online or is not already in the game
     * @return the first extracted method from the buffer
     */
    private ArrayList<String> nextMessage(String playerNickname)
    {
        ArrayList<String> message = new ArrayList<>();

        do {
            synchronized (inputBuffer) {
                if (inputBuffer.size() > 0) {
                    message = inputBuffer.remove(0);
                    inputBuffer.notifyAll();
                }
            }
            if (message.size() > 0)
                return (ArrayList<String>) message.clone();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((playerNickname.equals("") || playersOnline.get(playerNickname))
                && playersOnline.values().contains(true)
                && players.stream().filter(x->(!playersOnline.get(x) && !playerWizards.keySet().contains(x))).collect(Collectors.toList()).size() == 0);
        message.add("DISCONNECTED");
        return (ArrayList<String>) message.clone();
    }

    /**
     * @param receivedMessage message added to the input buffer in order to be parsed
     */
    public void parseMessage(ArrayList<String> receivedMessage)
    {
        if (gameEnded) return;
        synchronized (inputBuffer)
        {
            inputBuffer.add(receivedMessage);
            inputBuffer.notifyAll();
        }
    }

    /**
     * this method handles the disconnection of a player: his status will be "offline" and it removes him from the online players list and from the player order
     * if there is only 1 player left in the game after a timeout he will be considered as winner
     * if all of the players disconnected the match will be canceled
     * @param nickname nicnkame of the disconnected player
     */
    public void playerDisconnected(String nickname)
    {
        if (!players.contains(nickname))
            throw new InvalidParameterException();
        playersOnline.put(nickname, false);

        playerOrder.removeAll(playerOrder.stream().filter(x->x.getKey().equals(nickname)).collect(Collectors.toList()));

        if (newGame.getPlayers().get(players.indexOf(nickname)).online = false)
            return;
        else
            newGame.getPlayers().get(players.indexOf(nickname)).online = false;
        Logger.storeLog("Player "+nickname+" disconnected");
        if (playersOnline.values().stream().filter(x->x==true).collect(Collectors.toList()).size()==1)
        {
            String lastPlayer = players.stream().filter(x->playersOnline.get(x)).collect(Collectors.toList()).get(0);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (playersOnline.values().stream().filter(x->x==true).collect(Collectors.toList()).size()==1)
                    {
                        if (playersOnline.get(lastPlayer))
                            gameEnded(players.indexOf(lastPlayer));
                    }
                }
            }).start();
        }
        else if (playersOnline.values().stream().filter(x->x==true).collect(Collectors.toList()).size()==0)
        {
            gameEnded(-1);
        }
        updateView();
        //players.stream().filter(x->!x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("DISCONNECTED|"+nickname));

    }

    /**
     * this method handles the reconnection of a player and informs the others of him by updating his status to "ONLINE"
     * @param nickname nicnkame of the reconnected player
     */
    public void playerReconnected(String nickname)
    {
        if (!players.contains(nickname))
            throw new InvalidParameterException();
        playersOnline.put(nickname, true);
        newGame.getPlayers().get(players.indexOf(nickname)).online = true;
        //players.stream().filter(x->!x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("RECONNECTED|"+nickname));
        Logger.storeLog("Player "+nickname+" reconnected");
        updateView();
    }

    /**
     * @return the ID of the game Controller
     */
    public String getID()
    {
        return ID;
    }

    public ArrayList<String> getPlayers()
    {
        return (ArrayList<String>) players.clone();
    }

    public HashMap<String, Boolean> getPlayersOnline() {
        return (HashMap<String, Boolean>) playersOnline.clone();
    }

    /**
     * this method is used to order the players in the round based on the integer associated to the number of their last played helper card
     * @param playerList contains the players identifiers both with their played card number
     */
    public void orderByValue(ArrayList<Map.Entry<String, Integer>> playerList)
    {
        List<Map.Entry<String, Integer>> copy = (List<Map.Entry<String, Integer>>) playerList.clone();
        playerList.removeAll(playerList);
        playerList.addAll(copy.stream().sorted((x,y)->x.getValue()-y.getValue()).collect(Collectors.toList()));
    }
}
