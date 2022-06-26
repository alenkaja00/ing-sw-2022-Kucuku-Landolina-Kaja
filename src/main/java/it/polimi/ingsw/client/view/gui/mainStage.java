package it.polimi.ingsw.client.view.gui;

import it.polimi.ingsw.client.view.gui.controllers.StageSingleton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class mainStage extends Application {

    private Parent root;

    private Stage stage;

    private Scene scene;

    private String wizardPath = "/fxmlFiles/wizardScene.fxml";

    private String startPath = "/fxmlFiles/mainScene.fxml";

    private String waitingPath = "/fxmlFiles/waitingScene.fxml";

    private String deckPath = "/fxmlFiles/deckScene.fxml";

    public static void main(String[] args) {
        launch(args);
    }





    @Override
    public void start(final Stage primaryStage){

        stage = primaryStage;

        StageSingleton.getInstance().setStage(stage);

        primaryStage.setResizable(true);

       startScene();


    }

    /**
     * shows the start scene, where the player can choose between play, connection or quit
     */
    public void startScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource(startPath));
        try {
            root = loader.load();
            root.setId("pane");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        Platform.runLater(()->{stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();});
    }

    /**
     * shows the screen where the player can choose the helper card
     */
    public void helperScene()
    {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource(deckPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        // Platform.runLater(()->{
            stage.setTitle("Deck");
            stage.setScene(scene);
            //stage.show();});
            stage.show();
    }

    /**
     * this screen acts like a lobby
     */
    public void waitingScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource(waitingPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        stage.setTitle("Waiting");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * shows the screen where the player can select the wizard
     */
    public void wizardScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource(wizardPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        Platform.runLater(()->{stage.setTitle("Wizards");
            stage.setScene(scene);
            stage.show();});
    }
}
