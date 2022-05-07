package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientManager implements Runnable{

    private String nickname = null;
    private Socket socket;
    ServerController controller;
    PrintWriter socketOut;
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
        Scanner socketIn = null;
        try {
            socketIn = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
            System.out.println("Connection closed");
            if (nickname!= null)
                controller.managePlayerDisconnection(nickname);
            online = false;
        } finally {
            socketIn.close();
            socketOut.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
