package it.polimi.ingsw.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileStore;
import java.util.*;

public class ClientNetwork{

    Socket socket;
    Scanner socketIn;
    ClientController controller;
    PrintWriter socketOut;
    Boolean connected = false;

    public ClientNetwork(String ip, int port, ClientController controller) throws IOException {
        this.socket = new Socket(ip, port);
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
        socketIn = new Scanner(socket.getInputStream());
        connected = true;

        new Thread(()->{
            InetAddress inet = null;
            while (true)
            {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    inet = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                try {
                    if (inet== null || !inet.isReachable(5000))
                    {
                        catchException();
                        finalException();
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("network disconnection");
                }
            }
        }).start();

        new Thread(()->{
            //System.out.println("Connection established");
            try
            {
                //manage reconnection
                String firstMessage = socketIn.nextLine();
                List<String> parsedMessage = Arrays.asList(firstMessage.split("\\|"));
                if (parsedMessage.get(0).equals("JSON"))
                    controller.reconnected(parsedMessage.get(1));
                else
                    controller.parseServerMessage(firstMessage);

                //manage normal messages
                while (true) {
                    String receivedLine = socketIn.nextLine();
                    this.controller.parseServerMessage(receivedLine);
                }
            }
            catch(Exception e)
            {
                catchException();
            }
            finally
            {
                finalException();
            }
        }).start();

        //Executors.newCachedThreadPool().execute(new ServerManager(socket, controller));
    }

    private void catchException()
    {
        //e.printStackTrace();
        System.out.println("Connection closed");
        controller.playerDisconnected();
        controller.lockGUI();
        connected = false;
    }

    private void finalException()
    {
        socketIn.close();
        socketOut.close();
        try {
            socket.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        socketOut.println(message);
        socketOut.flush();
    }

}
