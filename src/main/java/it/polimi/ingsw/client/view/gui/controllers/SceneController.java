package it.polimi.ingsw.client.view.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SceneController {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private Button quitButton;


    private boolean connectionEstablished = false;


    public SceneController()
    {

    }


    public void setConnectionEstablished(boolean setconn)
    {
        connectionEstablished = setconn;
    }




    public void connectionScene(javafx.event.ActionEvent actionEvent) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/connectionScene.fxml"));
            root = loader.load();
            root.setId("connectionScene");
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
            stage.setTitle("Ip and Port");
            stage.setScene(scene);
            stage.show();

    }

    public void quitScene(javafx.event.ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
        /*//System.exit(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit");
            alert.setHeaderText("Are you sure you want to exit? ");
            if(alert.showAndWait().get() == ButtonType.OK)
            {

            }*/
    }



    public void playerNumberScene(javafx.event.ActionEvent actionEvent) throws IOException {

        if(connectionEstablished)
        {
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/playerNumberScene.fxml"));
            root.setId("playerNumScene");
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource( "/cssFiles/main.css").toExternalForm());

            stage.setTitle("Player Number");
            stage.setScene(scene);

            stage.show();
        }


    }


}