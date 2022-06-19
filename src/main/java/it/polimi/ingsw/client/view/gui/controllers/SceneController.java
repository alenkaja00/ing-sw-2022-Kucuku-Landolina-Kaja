package it.polimi.ingsw.client.view.gui.controllers;

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

            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/connectionScene.fxml").toURI().toURL());
            root = loader.load();
            //Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui/connectionScene.fxml").toURI().toURL());
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Ip and Port");
            stage.setScene(scene);
            stage.show();

    }

    public void quitScene(javafx.event.ActionEvent actionEvent) throws IOException {
        //System.exit(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit");
            alert.setHeaderText("Are you sure you want to exit? ");
            if(alert.showAndWait().get() == ButtonType.OK)
            {
                stage = (Stage) quitButton.getScene().getWindow();
                stage.close();
            }
    }



    public void playerNumberScene(javafx.event.ActionEvent actionEvent) throws IOException {

        if(connectionEstablished)
        {
            Parent root = FXMLLoader.load(new File("src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/playerNumberScene.fxml").toURI().toURL());
            root.setId("playerNum");
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());

            stage.setTitle("Player Number");
            stage.setScene(scene);

            stage.show();
        }


    }


}