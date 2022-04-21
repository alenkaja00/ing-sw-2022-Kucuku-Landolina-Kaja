package it.polimi.ingsw.server.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameController
{
    private String ID;
    private ArrayList<String> players;
    private HashMap<String, Boolean> playersOnline;
    HashMap<String, ClientManager> playerSockets;

    public GameController(int playerNumber, Boolean expertMode, String ID, ArrayList<String> nicknames, HashMap<String, ClientManager> playerSockets)
    {
        this.ID = ID;
        this.players = nicknames;
        this.playersOnline = new HashMap<String, Boolean>();
        players.stream().forEach(x->playersOnline.put(x, true));
        this.playerSockets = playerSockets;
    }

    public void parseMessage(String receivedMessage)
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
}
