package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.controller.ClientNetwork;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.ClientControllerSingleton;
import it.polimi.ingsw.server.controller.ServerNetwork;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClientController controller = new ClientController("GUI");

        //connectivity.sendMessage("NICKNAME|Alen");
        //connectivity.sendMessage("GAME|Alen|2|true");
        //connectivity.sendMessage("PLAY|ENDI");
        //connectivity.sendMessage("QUIT|ENDI");
        //connectivity.sendMessage("GAME|ENDI");
    }
}
