package it.polimi.ingsw.client.view.cli;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.cli.components.EriantysCLI;

import java.io.Console;

public class cliClass implements ViewInterface
{
    ClientController controller;
    Console viewConsole;
    EriantysCLI eriantysCLI;

    public cliClass(ClientController controller)
    {
        this.controller = controller;
        this.viewConsole = System.console();
        eriantysCLI = new EriantysCLI();
    }

    @Override
    public void startScreen(String serverIP) {
        eriantysCLI.clearConsole();
        eriantysCLI.welcomeScene();
    }


    @Override
    public void waitScreen(String message) {

    }

    @Override
    public void updateView(String json) {

    }

    @Override
    public void playHelper() {

    }

    @Override
    public void playETX() {

    }

    @Override
    public void playNature() {

    }

    @Override
    public void playCTE() {

    }

    @Override
    public void gameEnded(String endMessage) {

    }

    @Override
    public void messageScreen(String message) {

    }

    @Override
    public void newGame() {

    }
}
