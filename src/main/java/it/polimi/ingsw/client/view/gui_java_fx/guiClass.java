package it.polimi.ingsw.client.view.gui_java_fx;

import com.google.gson.Gson;
import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.ViewInterface;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.DashboardHandler;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.GameMapController;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.IslandHandler;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.StageSingleton;
import it.polimi.ingsw.server.model.gameClasses.GameClass;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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

        String gamePath = "src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/gameMap.fxml";
        Stage stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        Parent  root = null;
        Scene scene;
        try {
            loader = new FXMLLoader(new File(gamePath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameMapController controller = loader.getController();
            scene = new Scene(root);

            try {
                scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        Platform.runLater(()->{stage.setTitle("Main Scene");
            stage.setScene(scene);

            stage.show();
            controller.updateView(json);
        });





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