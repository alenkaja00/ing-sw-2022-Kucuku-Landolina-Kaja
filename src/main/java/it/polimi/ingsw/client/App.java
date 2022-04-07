package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.controller.ClientNetwork;
import it.polimi.ingsw.server.controller.ServerNetwork;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClientController controller = new ClientController();
        ClientNetwork connectivity = new ClientNetwork("127.0.0.1",3030, controller);

        //connectivity.sendMessage("GAME|ENDI");
        //connectivity.sendMessage("PLAY|ENDI");
        //connectivity.sendMessage("QUIT|ENDI");
        //connectivity.sendMessage("GAME|ENDI");
    }
}
