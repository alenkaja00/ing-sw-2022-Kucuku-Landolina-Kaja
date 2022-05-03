package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerNetwork
{
    private int port;
    ServerController controller;

    public ServerNetwork(int serverPort, ServerController controller)
    {
        this.port = serverPort;
        this.controller = controller;

        ExecutorService threadManager = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // porta non disponibile
            return;
        }

        System.out.println("Server ready");
        while (true) {
            try {
                Socket openSocket = serverSocket.accept();
                threadManager.submit(new ClientManager(openSocket, controller));
            } catch(IOException e) {
                break; // entrerei qui se serverSocket venisse chiuso
            }
        }
        threadManager.shutdown();
    }
}


