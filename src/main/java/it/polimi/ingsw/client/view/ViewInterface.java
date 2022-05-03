package it.polimi.ingsw.client.view;

public interface ViewInterface
{
    //will return an IP and port
    public void connectionScreen(boolean error);

    //shows message when connected to server
    public void startScreen();

    //shows error message if nickname is taken
    public void nicknameScreen();

    //game creation screen, player number parameter, expert mode parameter,
    //possibitily to go back to the startScreen
    public void createGameScreen();

    //wait screen which can show many different messages
    public void waitScreen(String message); //trasparente

    //update view from json
    public void updateView(String json);

    //show a message
    public void messageScreen(String message);
}
