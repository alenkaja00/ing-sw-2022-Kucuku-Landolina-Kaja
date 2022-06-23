package it.polimi.ingsw.client.view.gui.controllers;

import javafx.application.Platform;
import javafx.stage.Stage;

public class StageSingleton {

    private Stage stage;
    private static StageSingleton instance;

    private StageSingleton(){}

    public static StageSingleton getInstance()
    {
        if(instance == null)
        {
            instance= new StageSingleton();
        }
        return instance;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setMinWidth(950);
        stage.setMinHeight(700);

        stage.setOnCloseRequest(event->
        {
            Platform.exit();
            System.exit(0);
        });
    }
}
