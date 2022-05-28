package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.cliClass;
import it.polimi.ingsw.client.view.gui_java_fx.guiClass;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController
{
    private ArrayList<List<String>> serverBuffer = new ArrayList<List<String>>();
    private ArrayList<String> viewBuffer = new ArrayList<String>();
    private ClientNetwork connectivity;
    private String serverIP = "";
    private ViewInterface view;
    private String playerNickname;
    private Thread currentView;
    private Boolean viewLocked = false;

    public ClientController(String viewMode) throws IOException
    {
        if (viewMode.equals("GUI"))
            view = new guiClass(this);
        else if (viewMode.equals("CLI"))
            view = new cliClass(this);
    }

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
                view.changeState("WAITLOBBY");
                new Thread(() ->
                {
                    List<String> ok = nextServerMessage();
                    if (ok.get(0).equals("OK")) {
                        view.changeState("GAME");
                    }
                    else
                        view.changeState("START");
                }
                ).start();
                return true;
            }
            if (message.get(0).equals("OK")) {
                view.changeState("GAME");
                return true;
            }
            else
                return false;
        }
    }

    public void quitLobby()
    {
        connectivity.sendMessage("QUITLOBBY|"+playerNickname);
        clearServerBuffer();
    }

    public Boolean requestWizard(Wizard wizard)
    {
        clearServerBuffer();
        connectivity.sendMessage("PLAY|"+playerNickname+"|WIZARD|"+wizard);

        List<String> message = nextServerMessage();
        if (message.get(0).equals("OK")) {

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

    private void manageGameProsecution()
    {
        System.out.println("Game prosecution");
        List<String> message;

        //receive unlock or JSON
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

        view.changeState("HELPER");

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

    public boolean requestETI(int IslandIndex, int entranceIndex)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|ETI|"+IslandIndex+"|"+entranceIndex);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public boolean requestString(String message)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|"+message);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public boolean requestETT(int entranceIndex)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|ETI|"+entranceIndex);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public boolean requestNature(int mNatureMoves)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|NATURE|"+mNatureMoves);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

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

    public Boolean getViewLocked()
    {
        return viewLocked;
    }

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

    private void clearServerBuffer()
    {
        synchronized (serverBuffer)
        {
            serverBuffer.removeAll(serverBuffer);
            serverBuffer.notifyAll();
        }
    }

    public void parseServerMessage(String message)
    {
        System.out.println("LOG [parseServerMessage]: I am the client and I received: " + message);

        List<String> parsedMessage = Arrays.asList(message.split("\\|"));

        if (parsedMessage.get(0).equals("WINNER"))
        {
            view.gameEnded(parsedMessage.get(1));
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

    public void playerDisconnected()
    {
        clearServerBuffer();
        view.messageScreen("You disconnected from the server...");
        view.startScreen("");
    }

    public void reconnected(String jsonString)
    {
        view.updateView(jsonString);
        manageGameProsecution();
    }
}
