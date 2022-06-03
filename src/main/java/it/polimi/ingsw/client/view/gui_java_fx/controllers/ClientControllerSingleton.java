package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.client.controller.ClientController;

public class ClientControllerSingleton {

    private ClientController clientController ;
    private static ClientControllerSingleton instance ;

    private ClientControllerSingleton(){}

    public static ClientControllerSingleton getInstance()
    {
        if(instance == null)
        {
            instance= new ClientControllerSingleton();
        }
         return instance;
    }
    public ClientController getClientController()
    {
        return clientController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
