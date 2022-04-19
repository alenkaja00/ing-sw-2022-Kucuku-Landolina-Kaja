package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.controller.ClientController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ContentHandler;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class ClientNetwork{

    Socket socket;
    ClientController controller;
    PrintWriter socketOut;

    public ClientNetwork(String ip, int port, ClientController controller) throws IOException {
        this.socket = new Socket(ip, port);
        this.controller = controller;
        socketOut = new PrintWriter(socket.getOutputStream());
        Scanner socketIn = new Scanner(socket.getInputStream());

        new Thread(()->{
            System.out.println("Connection established");
            try
            {
                while (true) {
                    String receivedLine = socketIn.nextLine();
                    this.controller.parseMessage(receivedLine);
                }
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Connection closed");
            }
            finally
            {
                socketIn.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //Executors.newCachedThreadPool().execute(new ServerManager(socket, controller));
    }

    public void sendMessage(String message) throws IOException {
        socketOut.println(message);
        socketOut.flush();
    }

}
