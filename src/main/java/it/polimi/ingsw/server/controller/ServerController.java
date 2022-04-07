package it.polimi.ingsw.server.controller;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

    public ServerController() {}

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


    public void createGame(int playerNumber, String nickname, Boolean experMode)
    {
        System.out.println("playernumber: " + playerNumber);
        System.out.println("nickname: " + nickname);
        System.out.println("expertMode: " + experMode);
    }
}
