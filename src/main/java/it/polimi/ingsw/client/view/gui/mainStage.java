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

    private String wizardPath = "src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/wizardScene.fxml";

    private String startPath = "src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/mainScene.fxml";

    private String waitingPath = "src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/waitingScene.fxml";

    private String deckPath = "src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/deckScene.fxml";

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

    public void startScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(startPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
            root.setId("pane");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        try {
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Platform.runLater(()->{stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();});
    }

    public void helperScene()
    {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(deckPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        try {
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
       // Platform.runLater(()->{
            stage.setTitle("Deck");
            stage.setScene(scene);
            //stage.show();});
            stage.show();
    }

    public void waitingScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(waitingPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        try {
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stage.setTitle("Waiting");
        stage.setScene(scene);
        stage.show();
    }


    public void wizardScene() {
        stage = StageSingleton.getInstance().getStage();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(wizardPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        try {
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Platform.runLater(()->{stage.setTitle("Wizards");
            stage.setScene(scene);
            stage.show();});
    }
}