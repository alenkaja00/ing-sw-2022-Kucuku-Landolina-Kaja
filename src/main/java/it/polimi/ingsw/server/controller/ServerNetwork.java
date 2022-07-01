package it.polimi.ingsw.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class Server Network instantiates the server socket on the requested port
 * it uses the ExecutorService by instantiating a ClientManager class for every established connection with a client socket
 */
public class ServerNetwork
{
    private int port;
    ServerController controller;

    /**
     * Constructor method is responsible for starting the server by establishing the connection and linking to every client socket
     * @param serverPort port to connect
     * @param controller reference of the ServerController class
     */
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
                break;
            }
        }
        threadManager.shutdown();
    }
}


