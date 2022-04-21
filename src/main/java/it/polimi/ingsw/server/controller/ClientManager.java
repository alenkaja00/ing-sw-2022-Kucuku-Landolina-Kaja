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

    public ClientManager(Socket socket, ServerController controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
    }

    public void run()
    {
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
                        try
                        {
                            if (controller.availableNickname(parameters.get(1)))
                            {
                                nickname = parameters.get(1);
                                controller.addPlayersocket(nickname, this);
                                System.out.println("Client " + nickname + " connected");
                                sendMessage("Correctly logged in");
                            }
                            else
                            {
                                System.out.println("unavailable");
                                sendMessage("Unavailable nickname");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //send all the other messages to the controller
                String receivedMessage = socketIn.nextLine();
                controller.parseMessage(receivedMessage);
            }
        } catch(NoSuchElementException e) {
            System.out.println("Connection closed");
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

    public void sendMessage(String message) throws IOException {
        socketOut.println(message);
        socketOut.flush();
    }
}
