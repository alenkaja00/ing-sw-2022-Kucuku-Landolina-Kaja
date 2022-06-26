package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;

import java.io.IOException;

public class CLI
{
    public static void main( String[] args ) throws IOException
    {
        ClientController controller = new ClientController("CLI");
    }
}
