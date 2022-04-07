package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientManager implements Runnable{

    private Socket socket;
    ServerController controller;

    public ClientManager(Socket socket, ServerController controller) {
        this.socket = socket;
        this.controller = controller;
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
                String socketLine = socketIn.nextLine();
                controller.parseMessage(socketLine);
            }
        } catch(NoSuchElementException e) {
            System.out.println("Connection closed");
        } finally {
            socketIn.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        socketOut.println(message);
        socketOut.flush();
        socket.close();
    }
}
