package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ServerController {

    private HashMap<String, ClientManager> playerSockets = new HashMap<String, ClientManager>();
    private ArrayList<Map.Entry<String, Map.Entry<Integer, Boolean>>> playerLobby = new ArrayList<Map.Entry<String, Map.Entry<Integer, Boolean>>>();
    private ArrayList<GameController> openGames = new ArrayList<GameController>();

    public ServerController()
    {

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
                    synchronized (playerLobby)
                    {
                        System.out.print(playerLobby.toString());
                        playerLobby.notifyAll();
                    }
                }
            }
        }).start();
    }

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
        openGames.stream().filter(x->x.getPlayers().contains(nickname)).forEach(x->x.playerReconnected(nickname));
    }

    /**
     * removes disconnected players from the playerLobby
     * removes disconnected players from the playerScokets list
     * informs the needed active games that a player disconnected
     */
    public void managePlayerDisconnection(String nickname)
    {
        if (playerSockets.keySet().contains(nickname))
            playerSockets.remove(nickname);

        synchronized (playerLobby) {playerLobby.removeAll(playerLobby.stream().filter(x->x.getKey().equals(nickname)).collect(Collectors.toList())); playerLobby.notifyAll();}

        openGames.stream().filter(x->x.getPlayers().contains(nickname)).forEach(x->x.playerDisconnected(nickname));
    }

    public void parseMessage(String line){
        System.out.println("Sono il server, ho ricevuto: "+ line);

        ArrayList<String> parameters = new ArrayList<String>();
        parameters.addAll(List.of(line.split("\\|")));

        switch (parameters.get(0))
        {
            case "GAME":
                synchronized (playerLobby) { manageGameMessage(parameters); playerLobby.notifyAll();}
                break;
            case "QUITLOBBY":
                synchronized (playerLobby) { manageQuitLobbyMessage(parameters); playerLobby.notifyAll(); }
                break;
            case "PLAY":
                managePlayMessage(parameters);
                break;
        }
    }


    /**
     * checks the lobby if there are enough, players, creates the game and informs all players
     * GAME|playerNickname|playerNumber|expertModeEnabled
     */
    private void manageGameMessage(ArrayList<String> message) {
        String nickname = message.get(1);
        Integer playerNumber = Integer.valueOf(message.get(2));
        Boolean expertOn = Boolean.valueOf(message.get(3));

        List<Map.Entry<String, Map.Entry<Integer, Boolean>>> compatiblePlayers =
        playerLobby.stream().filter(x->!x.getKey().equals(nickname) && x.getValue().getKey() == playerNumber && x.getValue().getValue()==expertOn).collect(Collectors.toList());

        if (compatiblePlayers.size()>=playerNumber-1)
        {
            ArrayList<String> players = new ArrayList<>();
            players.add(nickname);
            for (int i=0; i<playerNumber-1; i++)
                players.add(compatiblePlayers.get(i).getKey());
            String newGameID = String.join("", players.stream().collect(Collectors.toList()));
            openGames.add(new GameController(playerNumber, expertOn, newGameID, (ArrayList<String>) players.clone(), playerSockets));
            playerLobby.removeAll(compatiblePlayers);
            players.stream().forEach(x-> playerSockets.get(x).sendMessage("Creating your game"));
        }
        else
        {
            playerLobby.add(Map.entry(nickname, Map.entry(playerNumber, expertOn)));
            playerSockets.get(nickname).sendMessage("Waiting for more players to connect");
        }
    }

    /**
     * removes a player from the lobby in order to make him make another request if he wants
     * QUITLOBBY|playerNickname
     */
    private void manageQuitLobbyMessage(ArrayList<String> message)
    {
        playerLobby.removeAll(playerLobby.stream().filter(x->x.getKey().equals(message.get(1))).collect(Collectors.toList()));
    }

    /**
     * sends the PLAY message to the relevant controller
     * PLAY|playerNickname|otherstuff
     */
    private void managePlayMessage(ArrayList<String> parameters)
    {
        openGames.stream().filter(x->x.getPlayers().contains(parameters.get(1))).forEach(x->x.parseMessage((ArrayList<String>) parameters.clone()));
    }
}
