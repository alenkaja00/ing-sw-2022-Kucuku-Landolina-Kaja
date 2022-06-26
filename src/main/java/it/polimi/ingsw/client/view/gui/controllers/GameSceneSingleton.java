package it.polimi.ingsw.client.view.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * this class makes the gamescene visible from all the classes
 */
public class GameSceneSingleton {

    private Scene gameScene;
    private static GameSceneSingleton instance;
    private Parent root;
    private GameMapController controller;

    /**
     * class constructor
     * loads the gamemap.fxml file
     */
    private GameSceneSingleton(){ FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource("/fxmlFiles/gameMap.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameScene = new Scene(root);

        gameScene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());


        controller = loader.getController();
    }

    /**
     * @return the unique instance of the class
     */
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

    public void reset()
    {
        instance = new GameSceneSingleton();
    }
}
