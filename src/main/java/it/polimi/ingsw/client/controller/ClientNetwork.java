package it.polimi.ingsw.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientNetwork{

    Socket socket;
    ClientController controller;
    PrintWriter socketOut;
    Boolean connected = false;

    public ClientNetwork(String ip, int port, ClientController controller) throws IOException {
        this.socket = new Socket(ip, port);
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
        Scanner socketIn = new Scanner(socket.getInputStream());

        new Thread(()->{
            System.out.println("Connection established");
            connected = true;
            try
            {
                //manage reconnection
                String firstMessage = socketIn.nextLine();
                ArrayList<String> parsedMessage = (ArrayList<String>) Arrays.asList(firstMessage.split("\\|"));
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
            catch(NoSuchElementException e)
            {
                System.out.println("Connection closed");
                controller.playerDisconnected();
                connected = false;
            }
            finally
            {
                socketIn.close();
                socketOut.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //Executors.newCachedThreadPool().execute(new ServerManager(socket, controller));
    }

    public void sendMessage(String message) {
        socketOut.println(message);
        socketOut.flush();
    }

}
