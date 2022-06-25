package it.polimi.ingsw.client.view.gui;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.gui.controllers.*;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import it.polimi.ingsw.server.model.gameClasses.GameClassExpert;
import javafx.application.Platform;

import javax.sound.midi.Soundbank;

public class guiClass implements ViewInterface
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
        GameMapController controller = GameSceneSingleton.getInstance().getController();
        Platform.runLater(()->{
            controller.updateView(json);
        });
    }

    @Override
    public void wizardScene() {
        mainstage.wizardScene();
    }

    @Override
    public void helperScene() {
        mainstage.helperScene();
    }

    @Override
    public void messageScene(String message) {

    }

    @Override
    public void endScene(String endMessage) {

        System.out.println("Player "+endMessage+"won!");
        GameSceneSingleton.getInstance().getController().bannerMessage("Player "+endMessage+" is the winner!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GameSceneSingleton.getInstance().reset();
        mainstage.startScene();
    }
}