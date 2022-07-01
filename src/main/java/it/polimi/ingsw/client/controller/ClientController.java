package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.cliClass;
import it.polimi.ingsw.client.view.gui.controllers.ClientControllerSingleton;
import it.polimi.ingsw.client.view.gui.guiClass;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClientController class sends the requests of actions from the user view classes to the connectivity
 */
public class ClientController
{
    private ArrayList<List<String>> serverBuffer = new ArrayList<List<String>>();
    private ArrayList<String> viewBuffer = new ArrayList<String>();
    private ClientNetwork connectivity;
    private String serverIP = "";
    private ViewInterface view;
    private String playerNickname;
    private Boolean viewLocked = false;
    private String viewMode = "";

    /**
     * Constructor method initializes the view Mode and sets the instance of the ClientControllerSingleton
     * @param viewMode input view mode, can be CLI (command line version) or GUI (graphical user interface)
     */
    public ClientController(String viewMode) throws IOException
    {
        this.viewMode = viewMode;
        ClientControllerSingleton instance = ClientControllerSingleton.getInstance();
        instance.setClientController(this);
        if (viewMode.equals("GUI")) {
            view = new guiClass();
        }
        else if (viewMode.equals("CLI"))
            view = new cliClass();
    }

    /**
     * sends a connection request from a client
     */
    public boolean requestConnection(String ip, int port)
    {
        try {
            this.connectivity = new ClientNetwork(ip, port, this);
            serverIP = ip;
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            serverIP = "";
            this.connectivity = null;
            return false;
        }
    }

    /**
     * sends a request of nickname selection
     * @param nickname desired nickname
     * @return true in case of success, false otherwise
     */
    public boolean requestNickname(String nickname)
    {
        clearServerBuffer();
        connectivity.sendMessage("NICKNAME|"+nickname);
        List<String> response = nextServerMessage();
        if (response.get(0).equals("OK"))
        {
            playerNickname = nickname;
            return true;
        }
        return false;
    }

    public String getNickname()
    {
        return playerNickname;
    }

    /**
     * called when a player wants to create a new game
     * @param playerNumber number of players of the game
     * @param expertMode true in case of expert game
     * @return true in case of success, false otherwise
     */
    public boolean requestNewGame(int playerNumber, Boolean expertMode)
    {
        if (connectivity== null || !connectivity.connected)
        {
            return false;
        }
        else
        {
            clearServerBuffer();
            connectivity.sendMessage("GAME|"+playerNickname+"|"+playerNumber+"|"+expertMode);
            List<String> message = nextServerMessage();
            if (message.get(0).equals("WAIT"))
            {
                view.waitLobbyScene();
                new Thread(() ->
                {
                    List<String> ok = nextServerMessage();
                    if (ok.get(0).equals("OK")) {
                        view.wizardScene();
                    }
                    else
                        view.startScene(serverIP);
                }
                ).start();
                return true;
            }
            if (message.get(0).equals("OK")) {
                view.wizardScene();
                return true;
            }
            else
                return false;
        }
    }

    /**
     * informs of a quit lobby action
     */
    public void quitLobby()
    {
        connectivity.sendMessage("QUITLOBBY|"+playerNickname);
        clearServerBuffer();
    }

    /**
     * sends the selected wizard requested
     * @param wizard the wanted wizard
     * @return true if the selection is allowed, false otherwise
     */
    public Boolean requestWizard(Wizard wizard)
    {
        clearServerBuffer();
        connectivity.sendMessage("PLAY|"+playerNickname+"|WIZARD|"+wizard);

        List<String> message = nextServerMessage();
        if (message.get(0).equals("OK"))
        {
            /*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<String> message = nextServerMessage();
                    if (message.get(0).equals("JSON"))
                    {
                        view.updateView(message.get(1));
                        manageGameProsecution();
                    }
                    else
                        System.out.println("Fatal error, did not receive a JSON but "+message);
                }
            }).start();*/
            viewLocked = true;
            return true;
        }
        return false;
    }

    /**
     * called after the reconnection of a player
     */
    private void manageGameProsecution()
    {
        System.out.println("Game prosecution");
        /*List<String> message;

        //receive unlock or JSON
        do {
            message = nextServerMessage();
            if (message.get(0).equals("JSON"))
            {
                System.out.println("json executed");
                view.updateView(message.get(1));
            }
            else if (message.get(0).equals("UNLOCK"))
            {
                System.out.println("unlock executed");
                break;
            }
            else
                System.out.println("FATAL ERROR: waiting for json or unlock, received "+ message);
        } while (true);
        System.out.println("qui");*/
        viewLocked = true;
        view.manageReconnection();

        /*
        do{



            /*
            //receive LOCK
            if (!nextServerMessage().get(0).equals("LOCK"))
                System.out.println("FATAL ERROR: expected to receive a lock but received "+message);

            //receive unlock of JSON
            do {
                message = nextServerMessage();
                if (message.get(0).equals("JSON"))
                {
                    view.updateView(message.get(1));
                }
                else if (message.get(0).equals("UNLOCK"))
                    break;
                else
                    System.out.println("FATAL ERROR: waiting for json or unlock, received "+ message);
            } while (true);

            view.playETX();

            view.playNature();

            view.playCTE();

            //receive lock
            if (!nextServerMessage().get(0).equals("LOCK"))
                System.out.println("FATAL ERROR: expected to receive a lock but received "+message);

        } while (true);*/
    }

    /**
     * sends a generic request
     * @param message string containing the request
     * @return true in case of success, false otherwise
     */
    public boolean requestString(String message)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|"+message);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    /**
     * sends a request of helper card selection
     * @param helperID id of the card to play
     * @return true in case of success, false otherwise
     */
    public boolean requestHelper(int helperID)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|HELPER|"+helperID);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public boolean requestEffect(String effectMessage)
    {
        connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    /**
     * sends a request of entrance to island move
     * @param IslandIndex index of the destination island
     * @param entranceIndex index of the student to move
     * @return true in case of success, false otherwise
     */
    public boolean requestETI(int IslandIndex, int entranceIndex)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|ETI|"+IslandIndex+"|"+entranceIndex);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    /**
     * sends a request of entrance to tables move
     * @param entranceIndex index of the student to remove from entrance
     * @return true in case of success, false otherwise
     */
    public boolean requestETT(int entranceIndex)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|ETT|"+entranceIndex);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    /**
     * sends to the ClientNetwork instance the message of cloud selection
     * @param mNatureMoves number of requested moves
     * @return true in case of success, false otherwise
     */
    public boolean requestNature(int mNatureMoves)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|NATURE|"+mNatureMoves);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    /**
     * sends to the ClientNetwork instance the message of cloud selection
     * @param cloudIndex index of the wanted cloud
     * @return true in case of success, false otherwise
     */
    public boolean requestCTE(int cloudIndex)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|CTE|"+cloudIndex);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public String getServerIP() {
        return serverIP;
    }

    /**
     * locks until the view is locked
     */
    public void waitViewUnlock()
    {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (ClientControllerSingleton.getInstance().getClientController().getViewLocked());
    }

    public Boolean getViewLocked()
    {
        return viewLocked;
    }

    /**
     * the next instruction to be processed
     * @return the first message of the buffer extracted in order to be processed
     */
    private List<String> nextServerMessage()
    {
        List<String> message = new ArrayList<>();

        do
        {
            synchronized (serverBuffer)
            {
                if (serverBuffer.size()>0)
                {
                    message = serverBuffer.remove(0);
                    serverBuffer.notifyAll();
                }
                serverBuffer.notifyAll();
            }
            if (message.size()>0)
                return new ArrayList<>(message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);

    }

    /**
     * empties the server buffer from the messages contained in it
     */
    private void clearServerBuffer()
    {
        synchronized (serverBuffer)
        {
            serverBuffer.removeAll(serverBuffer);
            serverBuffer.notifyAll();
        }
    }

    /**
     * accepts a message from the server and depending on it calls view scenes or puts it in the buffer to later process the message
     * @param message server string message
     */
    public void parseServerMessage(String message)
    {
        //System.out.println("[LOG] Client received: " + message);

        List<String> parsedMessage = Arrays.asList(message.split("\\|"));

        if (parsedMessage.get(0).equals("WINNER"))
        {
            view.endScene(parsedMessage.get(1));
            return;
        }
        else if (parsedMessage.get(0).equals("JSON"))
        {
            view.updateView(parsedMessage.get(1));
            return;
        }
        else if (parsedMessage.get(0).equals("LOCK"))
        {
            viewLocked = true;
            return;
        }
        else if (parsedMessage.get(0).equals("UNLOCK"))
        {
            viewLocked = false;
            return;
        }

        synchronized (serverBuffer)
        {
            serverBuffer.add(parsedMessage);
            serverBuffer.notifyAll();
        }
    }

    /**
     * calls the start scene of the view and shows a disconnection message
     */
    public void playerDisconnected()
    {
        clearServerBuffer();
        view.messageScene("You disconnected from the server...");
        view.startScene("");
    }

    /**
     * after reconnection updates the view and calls the game prosecution
     */
    public void reconnected(String jsonString)
    {
        view.updateView(jsonString);
        manageGameProsecution();
    }

    public void lockGUI() {
        viewLocked = true;
    }
}
