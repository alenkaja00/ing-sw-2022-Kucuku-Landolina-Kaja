package it.polimi.ingsw.server.controller;

import com.google.gson.Gson;
import com.sun.jdi.PrimitiveValue;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import it.polimi.ingsw.server.model.gameClasses.GameClassExpert;

import java.lang.reflect.Array;
import java.nio.file.FileAlreadyExistsException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameController
{
    private GameClass newGame;
    private boolean expertMode;
    private String ID;
    private ArrayList<String> players;
    private HashMap<String, Boolean> playersOnline;
    private HashMap<String, ClientManager> playerSockets;
    private ArrayList<Map.Entry<String, Integer>> playerOrder;
    private HashMap<String, Wizard> playerWizards;
    private ArrayList<ArrayList<String>> inputBuffer;

    public GameController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, HashMap<String, ClientManager> playerSockets)
    {
        this.ID = ID;
        this.players = nicknames;
        this.playersOnline = new HashMap<String, Boolean>();
        players.stream().forEach(x->playersOnline.put(x, true));
        this.playerSockets = playerSockets;
        this.playerOrder = new ArrayList<>();
        this.expertMode = expertMode;

        // printa pralyer online
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(playersOnline);
                }
            }
        }).start();

        //create a random round order the first time
        players.stream().forEach(x->playerOrder.add(Map.entry(x, new Random().nextInt(playerNumber))));
        orderByValue(playerOrder);

        playerOrder.stream().forEach(x->playerSockets.get(x).sendMessage("Choose your wizard"));

        //get wizards from players && communicate with them
        playerWizards = new HashMap<>();
        while (playerWizards.size()<playerNumber-1)
        {
            ArrayList<String> message = nextMessage();
            if (message.get(0).equals("PLAY") &&  !playerWizards.keySet().contains(message.get(1)) &&
                    message.get(2).equals("WIZARD") && !playerWizards.values().contains(message.get(3)))
            {
                playerSockets.get(message.get(1)).sendMessage("OK");
                playerWizards.put(message.get(1), Wizard.valueOf(message.get(3)));
            }
            else
            {
                playerSockets.get(message.get(1)).sendMessage("NOK");
            }
        }

        //order wizards according to the player who chose them
        ArrayList<Wizard> orderedWizards = new ArrayList<>();
        players.stream().forEach(x->orderedWizards.add(playerWizards.get(x)));
        //create gameClass or GameClassExpert
        if (expertMode)
            newGame = new GameClassExpert(ID, playerNumber, players, orderedWizards);
        else
            newGame = new GameClass(ID, playerNumber, players, orderedWizards);

        do {
            updateView();
            newGame.BagToCloud();
            updateView();

            //pianification phase
            for (int i = 0; i<playerNumber; i++)
            {
                String currentPlayer = players.get(i);
                playerSockets.get(currentPlayer).sendMessage("UNLOCK");

                do {
                    ArrayList<String> message = nextMessage();
                    if (message.get(0).equals("PLAY") && message.get(1).equals(currentPlayer) && message.get(2).equals("HELPER"))
                    {
                        try {
                            newGame.useHelperCard(i, Integer.parseInt(message.get(3)));
                        } catch (InvalidKeyException e) {
                            e.printStackTrace();
                        }
                        playerOrder.stream().filter(x->x.getValue().equals(currentPlayer)).forEach(x->x.setValue(Integer.valueOf(message.get(3))));
                        playerSockets.get(message.get(1)).sendMessage("OK");
                        break;
                    }
                    else
                    {
                        playerSockets.get(currentPlayer).sendMessage("NOK");
                    }
                } while (true);

                playerSockets.get(players.get(i)).sendMessage("LOCK");

                updateView();
            }

            orderByValue(playerOrder);

            //action phase
            for (int i=0; i<playerNumber; i++)
            {
                String currentPlayer = playerOrder.get(i).getKey();
                playerSockets.get(currentPlayer).sendMessage("UNLOCK");


                //STT OR STI
                for (int n=0; n<newGame.getClouds().get(0).getCloudCapacity(); n++)
                {
                    do
                    {
                        ArrayList<String> message = nextMessage();
                        if (condizione STT)
                        {
                            newGame.stt
                            playerSockets.get(currentPlayer).sendMessage("OK");
                            break;
                        }
                        else if (condizione STI)
                        {
                            newGame.sti
                            playerSockets.get(currentPlayer).sendMessage("OK");
                            break;
                        }
                        else
                        {
                             playerSockets.get(currentPlayer).sendMessage("NOK");
                        }
                    } while (true);
                }

                //MOVE MOTHER NATURE

                //CLOUD TO ENTRANCE



                playerSockets.get(currentPlayer).sendMessage("UNLOCK");
            }
        }while (true);
    }


    private void updateView()
    {
        Gson gson = new Gson();
        String result;
        if (expertMode)
            result = gson.toJson((GameClassExpert) newGame);
        else
            result = gson.toJson(newGame);
        players.stream().forEach(x->playerSockets.get(x).sendMessage("JSON|"+result));
    }

    private ArrayList<String> nextMessage()
    {
        ArrayList<String> message = new ArrayList<>();

        do
        {
            synchronized (inputBuffer)
            {
                if (inputBuffer.size()>0)
                {
                    message=inputBuffer.remove(0);
                    notifyAll();
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
        players.stream().filter(x->!x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("Player "+nickname+" disconnected"));
        System.out.println("Player "+nickname+" disconnected");
    }
    public void playerReconnected(String nickname)
    {
        if (!players.contains(nickname))
            throw new InvalidParameterException();
        playersOnline.put(nickname, true);
        players.stream().filter(x->!x.equals(nickname)).forEach(x->playerSockets.get(x).sendMessage("Player "+nickname+" reconnected"));
        System.out.println("Player "+nickname+" reconnected");
        playerSockets.get(nickname).sendMessage("STATUS");
    }

    public String getID()
    {
        return ID;
    }

    public ArrayList<String> getPlayers()
    {
        return (ArrayList<String>) players.clone();
    }

    public void orderByValue(ArrayList<Map.Entry<String, Integer>> playerList)
    {
        List<Map.Entry<String, Integer>> copy = (List<Map.Entry<String, Integer>>) playerList.clone();
        playerList.removeAll(playerList);
        playerList.addAll(copy.stream().sorted((x,y)->x.getValue()-y.getValue()).collect(Collectors.toList()));
    }
}
