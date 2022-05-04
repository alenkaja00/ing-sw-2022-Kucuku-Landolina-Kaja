package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.cliClass;
import it.polimi.ingsw.client.view.gui.guiClass;
import it.polimi.ingsw.server.model.cards.Wizard;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController
{
    private ArrayList<ArrayList<String>> serverBuffer = new ArrayList<ArrayList<String>>();
    private ArrayList<String> viewBuffer = new ArrayList<String>();
    private ClientNetwork connectivity;
    private String serverIP;
    private ViewInterface view;
    private String playerNickname;

    public ClientController(String viewMode) throws IOException {

         viewController(viewMode);
    }

    private void viewController(String viewMode)
    {
        if (viewMode.equals("GUI"))
            view = new guiClass(this);
        else if (viewMode.equals("CLI"))
            view = new cliClass(this);


        view.startScreen(serverIP);
    }

    public boolean tryConnection(String ip, int port)
    {
        try {
            this.connectivity = new ClientNetwork(ip, port, this);
            serverIP = ip;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            serverIP = "";
            this.connectivity = null;
            return false;
        }
    }

    public boolean sendNickname(String nickname)
    {
        clearServerBuffer();
        connectivity.sendMessage("NICKNAME|"+nickname);
        ArrayList<String> response = nextServerMessage();
        if (response.get(0).equals("OK"))
        {
            playerNickname = nickname;
            return true;
        }
        return false;
    }

    public boolean gameRequest(int playerNumber, Boolean expertMode)
    {
        if (!connectivity.connected)
        {
            return false;
        }
        else
        {
            clearServerBuffer();
            connectivity.sendMessage("GAME|"+playerNickname+"|"+playerNumber+"|"+expertMode);
            ArrayList<String> message = nextServerMessage();
            if (message.get(0).equals("WAIT"))
            {
                view.waitScreen("Waiting for more Players...");

            }
            message = nextServerMessage();
            if (message.get(0).equals("OK")) {
                new Thread() {public void runnable() {view.newGame();}};
                return true;
            }
            else
                return false;
        }
    }

    public void quitLobby()
    {
        connectivity.sendMessage("QUITLOBBY");
        clearServerBuffer();
    }

    public Boolean requestWizard(Wizard wizard)
    {
        clearServerBuffer();
        connectivity.sendMessage("PLAY|"+playerNickname+"|WIZARD|"+wizard);
        if (nextServerMessage().get(0).equals("OK")) {
            new Thread() {
                public void runnable() {
                    ArrayList<String> message = nextServerMessage();
                    if (message.get(0).equals("JSON"))
                    {
                        view.updateView(message.get(1));
                        manageGameProsecution();
                    }
                    else
                        System.out.println("Fatal error, did not receive a JSON but "+message);
                }
            };
            return true;
        }
        return false;
    }
    private void manageGameProsecution()
    {
        ArrayList<String> message;
        //receive unlock of JSON
        do {
            message = nextServerMessage();
            if (message.get(0).equals("JSON"))
            {
                view.updateView(message.get(1));
                break;
            }
            else if (message.get(0).equals("UNLOCK"))
                break;
            else
                System.out.println("FATAL ERROR: waiting for json or unlock, received "+ message);
        } while (true);

        view.playHelper();

        //receive LOCK
        if (!nextServerMessage().get(0).equals("LOCK"))
            System.out.println("FATAL ERROR: expected to receive a lock but received "+message);

        //receive unlock of JSON
        do {
            message = nextServerMessage();
            if (message.get(0).equals("JSON"))
            {
                view.updateView(message.get(1));
                break;
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
    }

    public void requestHelper(int helperID)
    {
        connectivity.sendMessage("PLAY|"+playerNickname+"|HELPER|"+helperID);
    }

    public boolean requestEffect(String effectMessage)
    {
        connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;
    }

    public boolean requestETI()
    {
        /*connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;*/
    }

    public boolean requestETT()
    {
        /*connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;*/
    }

    public boolean requestNature()
    {
        /*connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;*/
    }

    public boolean requestCTE()
    {
        /*connectivity.sendMessage(effectMessage);
        if (nextServerMessage().get(0).equals("OK"))
            return true;
        else
            return false;*/
    }

    /*DEPRECATED
    private String nextViewMessage()
    {
        String message = null;

        do
        {
            synchronized (viewBuffer)
            {
                if (viewBuffer.size()>0)
                {
                    message= viewBuffer.remove(0);
                    notifyAll();
                }
            }
            if (message != null)
                return message;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);

    }

    public void parseViewMessage(String message)
    {
        System.out.println("The view told me: " + message);

        synchronized (viewBuffer)
        {
            viewBuffer.add(message);
            viewBuffer.notifyAll();
        }
    }
    */


    private ArrayList<String> nextServerMessage()
    {
        ArrayList<String> message = new ArrayList<>();

        do
        {
            synchronized (serverBuffer)
            {
                if (serverBuffer.size()>0)
                {
                    message= serverBuffer.remove(0);
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

    private void clearServerBuffer()
    {
        serverBuffer.removeAll(serverBuffer);
    }

    public void parseServerMessage(String message)
    {
        System.out.println("I am the client and I received: " + message);

        ArrayList<String> parsedMessage = (ArrayList<String>) Arrays.asList(message.split("\\|"));

        synchronized (serverBuffer)
        {
            serverBuffer.add(parsedMessage);
            serverBuffer.notifyAll();
        }
    }
}
