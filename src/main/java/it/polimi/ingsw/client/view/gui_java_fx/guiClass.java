package it.polimi.ingsw.client.view.gui_java_fx;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.ConnectionController;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.DashboardHandler;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.IslandHandler;
import it.polimi.ingsw.client.view.jsonObjects.jGameClassExpert;
import it.polimi.ingsw.server.model.gameClasses.GameClass;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class guiClass implements ViewInterface
{
    Gson gson = new Gson();
    GameClass gameData ;




    ClientController controller;

    mainStage stage;
    public guiClass(ClientController controller)
    {
        this.controller = controller;
        stage = new mainStage();
        stage.setClientController(controller);
        stage.main(null);
    }

    @Override
    public void startScreen(String serverIP) {



    }


    @Override
    public void waitLobby() {

    }

    @Override
    public void updateView(String json) {

        gameData = gson.fromJson(json, GameClass.class);
        DashboardHandler dashboardHandler = new DashboardHandler();
        IslandHandler islandHandler = new IslandHandler();

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
    public void changeState(String start) {

    }


    @Override
    public void messageScreen(String message) {

    }

    @Override
    public void newGame() {

    }
}
