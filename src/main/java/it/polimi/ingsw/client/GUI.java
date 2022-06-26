package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;

import java.io.IOException;

public class GUI
{
    public static void main( String[] args ) throws IOException
    {
        ClientController controller = new ClientController("GUI");
    }
}
