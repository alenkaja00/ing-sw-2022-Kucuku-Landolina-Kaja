package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.controller.ClientController;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerController {

    private HashMap<String, ClientManager> playerSockets = new HashMap<String, ClientManager>();
    private HashMap<String, Map.Entry<Integer,Boolean>> playerLobby = new HashMap<String, Map.Entry<Integer,Boolean>>();
    public ServerController() {}

    public boolean availableNickname(String nickname)
    {
        if (playerSockets.keySet().contains(nickname))
            return false;
        else
            return true;
    }
    public void addPlayersocket(String nickname, ClientManager hisManager)
    {
        playerSockets.put(nickname, hisManager);
    }


    public void parseMessage(String line){
        System.out.println("Sono il server, ho ricevuto: "+ line);

        ArrayList<String> parameters = new ArrayList<String>();
        parameters.addAll(List.of(line.split("\\|")));

        switch (parameters.get(0))
        {
            case "GAME":
                manageGameMessage(parameters);
                break;
            case "PLAY":
                managePlayMessage(parameters);
                break;
            case "STOP":
                manageStopMessage(parameters);
                break;
        }
    }

    public void manageGameMessage(ArrayList<String> message) {
        String nickname = message.get(1);
        Integer playerNumber = Integer.valueOf(message.get(2));
        Boolean expertOn = Boolean.valueOf(message.get(3));

        try {
            playerSockets.get(nickname).sendMessage("Looking for other players");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
