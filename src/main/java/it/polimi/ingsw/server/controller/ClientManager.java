package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Class ClientManager communicates with the socket, accepts the messages from the associated client and sends them to the controller
 * this class contains references and attributes of the socket out, the player nickname, the server controller and the online/offline status of the relative client
 */
public class ClientManager implements Runnable{

    private String nickname = null;
    private Socket socket;
    private Scanner socketIn;
    private ServerController controller;
    private PrintWriter socketOut;
    private boolean online;

    /**
     * Constructor method initializes the sockets and the controller associated to the class
     * @param socket
     * @param controller
     * @throws IOException
     */
    public ClientManager(Socket socket, ServerController controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
    }

    /**
     * method responsible for communication with the client, it sends the received messages to the controller
     * initially it waits for the choice of the nickname; after connecting it will receive all the messages of the relative player
     * it contains also a ping method to keep the connection alive between client/server
     */
    public void run()
    {
        online = true;
        System.out.println("Connection established");
        socketIn = null;
        try {
            socketIn = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    inet = InetAddress.getByName(String.valueOf(socket.getInetAddress()).substring(1));
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


        try {
            while (true) {
                //setup nickname && subscription to controller clientSockets list
                while (nickname == null)
                {
                    String firstMessage = socketIn.nextLine();
                    List<String> parameters = Arrays.asList(firstMessage.split("\\|"));
                    if (parameters.get(0).equals("NICKNAME"))
                    {
                        if (controller.availableNickname(parameters.get(1)))
                        {
                            nickname = parameters.get(1);
                            controller.addPlayersocket(nickname, this);
                            System.out.println("Client " + nickname + " connected");
                            sendMessage("OK");
                        }
                        else
                        {
                            System.out.println("unavailable");
                            sendMessage("NOK");
                        }
                    }
                }
                //send all the other messages to the controller
                String receivedMessage = socketIn.nextLine();
                controller.parseMessage(receivedMessage);
            }
        } catch(Exception e) {
            catchException();
        } finally {
            finalException();
        }
    }

    /**
     * method called after a disconnection event
     */
    private void catchException()
    {
        //e.printStackTrace();
        System.out.println("Client "+nickname+" disconnected");
        if (nickname!= null)
            controller.managePlayerDisconnection(nickname);
        online = false;
    }

    /**
     * closes the socket after a disconnection
     */
    private void finalException()
    {
        socketIn.close();
        socketOut.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method prints a message string on the socketOut
     * @param message
     */
    public void sendMessage(String message) {
        if (online)
        {
            socketOut.println(message);
            socketOut.flush();
        }
        else
        {
            System.out.println("Message for "+ nickname+" ignored. Player offline.");
        }

    }
}