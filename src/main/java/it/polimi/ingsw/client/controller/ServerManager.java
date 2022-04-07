package it.polimi.ingsw.client.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerManager
{
    private Socket socket;
    private ClientController controller;

    public ServerManager(Socket socket, ClientController controller)
    {
        this.socket = socket;
        this.controller = controller;

    }
}
