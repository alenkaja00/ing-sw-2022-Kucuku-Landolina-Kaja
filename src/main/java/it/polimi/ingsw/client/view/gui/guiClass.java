package it.polimi.ingsw.client.view.gui;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.gui.controllers.*;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * this class implements the ViewInterface and its methods are called by the ClientController
 */
public class guiClass implements ViewInterface
{
    Gson gson = new Gson();
    GameClass gameData;
    ClientController controller;
    mainStage mainstage;

    /**
     * class constructor, runs mainstage, which extends application
     */
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
        mainstage.waitingScene("");
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

    public void manageReconnection()
    {
        Platform.runLater(()->{
            mainstage.waitingScene("Waiting for readmission to the game...");

            Task waitTurn = new Task<Void>() {
                @Override
                public Void call() {
                    ClientControllerSingleton.getInstance().getClientController().waitViewUnlock();
                    return null;
                }
            };
            waitTurn.setOnSucceeded(event -> {
                Stage stage = StageSingleton.getInstance().getStage();
                Scene scene = GameSceneSingleton.getInstance().getGameScene();

                stage.setTitle("GameMap");
                stage.setScene(scene);
                stage.show();

                Task gameLogic = new Task<Void>() {
                    @Override
                    public Void call() {
                        GameSceneSingleton.getInstance().getController().ETX();
                        return null;
                    }
                };
                new Thread(gameLogic).start();
            });
            new Thread(waitTurn).start();
        });
    }

    @Override
    public void endScene(String endMessage) {

        System.out.println("Player "+endMessage+"won!");
        for (int i=1; i<11; i++)
        {
            GameSceneSingleton.getInstance().getController().bannerMessage("Player "+endMessage+" is the winner! Returning to start screen in "+(11-i)+"s...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        GameSceneSingleton.getInstance().reset();
        mainstage.startScene();
    }

    @Override
    public void lockView()
    {
        GameSceneSingleton.getInstance().getController().lockGui();
    }
}