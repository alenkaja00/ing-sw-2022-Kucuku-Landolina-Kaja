package it.polimi.ingsw.client.view.gui_java_fx;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.SceneController;
import it.polimi.ingsw.client.view.gui_java_fx.controllers.StageSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class mainStage extends Application {

    private Parent root;

    private Stage stage;

    private Scene scene;

    private String wizardPath = "src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/wizardScene.fxml";

    private String startPath = "src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/mainScene.fxml";

    private String waitingPath = "src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/waitingScene.fxml";


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
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stage.setTitle("Start");
        stage.setScene(scene);
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
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
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
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stage.setTitle("Wizards");
        stage.setScene(scene);
        stage.show();


    }


}
