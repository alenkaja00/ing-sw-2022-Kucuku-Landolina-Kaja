package it.polimi.ingsw.server.controller;

import java.lang.reflect.Array;
import java.nio.file.FileAlreadyExistsException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameController
{
    private String ID;
    private ArrayList<String> players;
    private HashMap<String, Boolean> playersOnline;
    private HashMap<String, ClientManager> playerSockets;
    private ArrayList<Map.Entry<String, Integer>> playerOrder;

    public GameController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, HashMap<String, ClientManager> playerSockets)
    {
        this.ID = ID;
        this.players = nicknames;
        this.playersOnline = new HashMap<String, Boolean>();
        players.stream().forEach(x->playersOnline.put(x, true));
        this.playerSockets = playerSockets;
        this.playerOrder = new ArrayList<>();
        players.stream().forEach(x->playerOrder.add(Map.entry(x, new Random().nextInt(playerNumber))));
        orderByValue(playerOrder);
        System.out.println(playerOrder);
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
        //crea una partita gameClass o GameClassExpert

        //gestione turno


    }

    public void parseMessage(ArrayList<String> receivedMessage)
    {

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
