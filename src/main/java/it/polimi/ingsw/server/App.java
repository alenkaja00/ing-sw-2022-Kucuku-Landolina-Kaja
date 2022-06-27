package it.polimi.ingsw.server;

import it.polimi.ingsw.server.controller.ServerNetwork;
import it.polimi.ingsw.server.controller.ServerController;
import java.io.IOException;

/**
 * main method that loads and initializes the server of the game
 */
public class App 
{

    public static void main( String[] args ) throws IOException {

        ServerController theController = new ServerController();
        ServerNetwork myconn = new ServerNetwork(3030, theController);

    }
}

