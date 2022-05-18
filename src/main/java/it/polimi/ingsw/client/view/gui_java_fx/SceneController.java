package it.polimi.ingsw.client.view.gui_java_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SceneController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    private boolean connectionEstablished = false;

    public void setConnectionEstablished(boolean setconn)
    {
        connectionEstablished = setconn;
    }




    public void connectionScene(javafx.event.ActionEvent actionEvent) throws IOException {

            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/connectionScene.fxml").toURI().toURL());
            root = loader.load();
            //Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/connectionScene.fxml").toURI().toURL());
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/cssfiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Ip and Port");
            stage.setScene(scene);

            stage.show();

    }

    public void quitScene(javafx.event.ActionEvent actionEvent) throws IOException {

    }




    public void modeScene(javafx.event.ActionEvent actionEvent) throws IOException {

            Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/modeScene.fxml").toURI().toURL());
            root.setId("mode");
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/cssfiles/main.css").toURI().toURL().toExternalForm());

            stage.setTitle("Mode Scene");
            stage.setScene(scene);

            stage.show();



    }


    public void playerNumberScene(javafx.event.ActionEvent actionEvent) throws IOException {

        if(connectionEstablished)
        {
            Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/playerNumber.fxml").toURI().toURL());
            root.setId("playerNum");
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/cssfiles/main.css").toURI().toURL().toExternalForm());

            stage.setTitle("Player Number");
            stage.setScene(scene);

            stage.show();
        }


    }



    public void mainScene(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/main.fxml").toURI().toURL());
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/cssfiles/main.css").toURI().toURL().toExternalForm());

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }


}