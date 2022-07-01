package it.polimi.ingsw.client.controller;

/**
 * this class makes the clientcontroller visible from all the classes
 */
public class ClientControllerSingleton {

    private ClientController clientController ;
    private static ClientControllerSingleton instance ;

    private ClientControllerSingleton(){}

    /**
     * @return the unique instance of the class
     */
    public static ClientControllerSingleton getInstance()
    {
        if(instance == null)
        {
            instance= new ClientControllerSingleton();
        }
         return instance;
    }

    /**
     * @return the unique clientcontroller
     */
    public ClientController getClientController()
    {
        return clientController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
