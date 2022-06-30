package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class ClientManager implements Runnable{

    private String nickname = null;
    private Socket socket;
    private Scanner socketIn;
    private ServerController controller;
    private PrintWriter socketOut;
    private boolean online;

    public ClientManager(Socket socket, ServerController controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
    }

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

    private void catchException()
    {
        //e.printStackTrace();
        System.out.println("Client "+nickname+" disconnected");
        if (nickname!= null)
            controller.managePlayerDisconnection(nickname);
        online = false;
    }

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
