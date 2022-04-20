package it.polimi.ingsw.server.controller;

import javax.swing.text.html.parser.Entity;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerController {

    private HashMap<String, Socket> playerSockets = new HashMap<String, Socket>();
    private HashMap<String, Map.Entry<Integer,Boolean>> playerLobby = new HashMap<String, Map.Entry<Integer,Boolean>>();
    public ServerController() {}

    public boolean availableNickname(String nickname)
    {
        if (playerSockets.keySet().contains(nickname))
            return false;
        else
            return true;
    }
    public void addPlayersocket(String nickname, Socket hisSocket)
    {
        playerSockets.put(nickname, hisSocket);
    }


    public void parseMessage(String line){
        System.out.println("Sono il server, ho ricevuto: "+ line);

        ArrayList<String> parameters = new ArrayList<String>();
        parameters.addAll(List.of(line.split("|")));

        switch (parameters.get(0))
        {
            case "GAME":
                System.out.println("message GAME");
                break;
            case "PLAY":
                System.out.println("message PLAY");
                break;
            case "STOP":
                System.out.println("message PLAY");
                break;
        }
    }

    public void manageGameMessage(ArrayList<String> message)
    {

    }

    public void managePlayMessage(ArrayList<String> message)
    {

    }

    public void manageStopMessage(ArrayList<String> message)
    {

    }

    public void createGame(int playerNumber, String nickname, Boolean experMode)
    {
        System.out.println("playernumber: " + playerNumber);
        System.out.println("nickname: " + nickname);
        System.out.println("expertMode: " + experMode);
    }
}
