package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.cliClass;
import it.polimi.ingsw.client.view.gui.guiClass;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController
{
    private ArrayList<ArrayList<String>> serverBuffer = new ArrayList<ArrayList<String>>();
    private ArrayList<String> viewBuffer = new ArrayList<String>();
    private ClientNetwork connectivity;
    ViewInterface view;

    public ClientController(String viewMode) throws IOException {

         viewController(viewMode);
    }

    private void viewController(String viewMode)
    {
        if (viewMode.equals("GUI"))
            view = new guiClass();
        else if (viewMode.equals("CLI"))
            view = new cliClass();


        //get the ip and port and start the connection
        //IPPORT|X.Y.Z.K|1234
        int connected = 0;
        do {
            String ipport = "";
            //the view only allows an ip and port as the first message
            view.connectionScreen(connected >0 ? true : false);
            ipport = nextViewMessage();
            ArrayList<String> parameters = new ArrayList<String>();
            parameters.addAll(List.of(ipport.split("\\|")));
            try {
                this.connectivity = new ClientNetwork(parameters.get(1),Integer.parseInt(parameters.get(2)), this);
                connected = -1;
            } catch (IOException e) {
                e.printStackTrace();
                connected++;
            }
        } while (connected != -1);

        //show the start screen
        view.startScreen();


    }



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
