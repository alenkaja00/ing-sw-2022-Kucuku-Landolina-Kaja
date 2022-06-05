package it.polimi.ingsw.client.view.gui_java_fx;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.DashboardHandler;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.IslandHandler;
import it.polimi.ingsw.server.model.gameClasses.GameClass;

public class guiClass  implements ViewInterface
{
    Gson gson = new Gson();
    GameClass gameData;
    ClientController controller;
    mainStage mainstage;

    public guiClass()
    {
        mainstage = new mainStage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mainstage.main(null);
            }
        }).start();
    }


    @Override
    public void startScene(String serverIP) {

        mainstage.startScene();

    }

    @Override
    public void waitLobbyScene() {

        mainstage.waitingScene();
    }

    @Override
    public void updateView(String json) {

        gameData = gson.fromJson(json, GameClass.class);
        DashboardHandler dashboardHandler = new DashboardHandler();
        IslandHandler islandHandler = new IslandHandler();

    }

    @Override
    public void wizardScene() {
        mainstage.wizardScene();
    }

    @Override
    public void helperScene() {

    }

    @Override
    public void messageScene(String message) {

    }

    @Override
    public void endScene(String endMessage) {

    }
}