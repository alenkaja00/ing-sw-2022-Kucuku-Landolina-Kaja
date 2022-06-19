package it.polimi.ingsw.client.view.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class GameSceneSingleton {

    private Scene gameScene;
    private static GameSceneSingleton instance;
    private Parent root;
    private GameMapController controller;

    private GameSceneSingleton(){ FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/gameMap.fxml").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameScene = new Scene(root);

        try {
            gameScene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        controller = loader.getController();
    }

    public static GameSceneSingleton getInstance() {
        if (instance == null) instance = new GameSceneSingleton();
        return instance;
    }
        public Scene getGameScene()
        {
            return gameScene;
        }

        public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
    public GameMapController getController(){return controller;}

}
