package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;

import java.io.IOException;

/**
 * main method that starts the GUI version of the game
 */
public class GUI
{
    public static void main( String[] args ) throws IOException
    {
        ClientController controller = new ClientController("GUI");
    }
}
