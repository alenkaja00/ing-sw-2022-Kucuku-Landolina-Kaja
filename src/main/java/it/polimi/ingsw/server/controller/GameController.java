package it.polimi.ingsw.server.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import it.polimi.ingsw.server.model.gameClasses.GameClassExpert;

import javax.sound.midi.Soundbank;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

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

    public boolean getGameEnded()
    {
        synchronized (gameEnded)
        {
            return gameEnded;
        }
    }

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
            System.out.println("out of the nextm");

            if (!message.get(0).equals("DISCONNECTED"))
            {
                if (message.get(0).equals("PLAY") &&  !playerWizards.keySet().contains(message.get(1)) &&
                        message.get(2).equals("WIZARD") && !playerWizards.values().contains(Wizard.valueOf(message.get(3))))
                {
                    /*
                    System.out.println(playerWizards.values().contains(message.get(3)));
                    System.out.println(playerWizards);
                    System.out.println(message);*/
                    System.out.println("received "+ message.get(3));
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
                    System.out.println("selecting random wizard");
                    playerWizards.put(players.get(i), Arrays.stream(Wizard.values()).filter(x->!playerWizards.values().contains(x)).collect(Collectors.toList()).get(0) );
                }
            }
        }
    }

    private void pianificationPhase()
    {
        players.stream().filter(x-> !playersOnline.get(x)).forEach(x->playerDisconnected(x));
        if (gameEnded) return;
        updateView();
        System.out.println("Entering pianification phase");
        playerOrder.removeAll(playerOrder);
        //pianification phase
        for (int i = 0; i<players.size(); i++)
        {
            String currentPlayer = players.get(i);
            if (!playersOnline.get(currentPlayer))
                continue;
            playerSockets.get(currentPlayer).sendMessage("UNLOCK");
            System.out.println(playerOrder);

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
                    else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFF"))
                    {
                        manageEffect(players.indexOf(currentPlayer), message);
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
                    System.out.println(newGame.playerMaxMoves(0));
                    System.out.println(newGame.playerMaxMoves(1));
                    System.out.println(newGame.playerMaxMoves(2));
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
                }
                else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFF"))
                {
                    manageEffect(players.indexOf(currentPlayer), message);
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
                else if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("EFF"))
                {
                    manageEffect(players.indexOf(currentPlayer), message);
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
    }

    private void manageEffect(int playerID, ArrayList<String> message)
    {
        if (!expertMode) {
            System.out.println("ERROR. Trying to play effect card in non expert game.");
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

        playerSockets.get(players.get(playerID)).sendMessage("OK");
        updateView();
    }

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
    private void updateView(String nickname)
    {
        Gson gson = new Gson();
        String result;
        if (expertMode)
            result = gson.toJson((GameClassExpert) newGame);
        else
            result = gson.toJson(newGame);
        players.stream().filter(x->x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("JSON|"+result));
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
    public void parseMessage(ArrayList<String> receivedMessage)
    {
        synchronized (inputBuffer)
        {
            inputBuffer.add(receivedMessage);
            inputBuffer.notifyAll();
        }
    }

    public void playerDisconnected(String nickname)
    {
        if (!players.contains(nickname))
            throw new InvalidParameterException();
        playersOnline.put(nickname, false);
        if (newGame.getPlayers().get(players.indexOf(nickname)).online = false)
            return;
        else
            newGame.getPlayers().get(players.indexOf(nickname)).online = false;
        System.out.println("Player "+nickname+" disconnected");
        if (playersOnline.values().stream().filter(x->x==true).collect(Collectors.toList()).size()==1)
        {
            String lastPlayer = players.stream().filter(x->playersOnline.get(x)).collect(Collectors.toList()).get(0);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(15000);
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
    public void playerReconnected(String nickname)
    {
        if (!players.contains(nickname))
            throw new InvalidParameterException();
        playersOnline.put(nickname, true);
        newGame.getPlayers().get(players.indexOf(nickname)).online = true;
        //players.stream().filter(x->!x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("RECONNECTED|"+nickname));
        System.out.println("Player "+nickname+" reconnected");
        updateView();
    }

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

    public void orderByValue(ArrayList<Map.Entry<String, Integer>> playerList)
    {
        List<Map.Entry<String, Integer>> copy = (List<Map.Entry<String, Integer>>) playerList.clone();
        playerList.removeAll(playerList);
        playerList.addAll(copy.stream().sorted((x,y)->x.getValue()-y.getValue()).collect(Collectors.toList()));
    }
}
