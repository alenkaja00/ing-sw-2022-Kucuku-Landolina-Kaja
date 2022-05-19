package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.client.view.gui_java_fx.controllers.ModeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PlayerNumberController {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private Button twoPlayers;
    @FXML
    private Button threePlayers;


    public void setPlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/modeScene.fxml").toURI().toURL());
        root = loader.load();

        ModeController controller = loader.getController();

        if(actionEvent.getSource().equals(twoPlayers)){
            controller.setPlayersNum(2);
        }
        else if(actionEvent.getSource().equals(threePlayers)) {
            controller.setPlayersNum(3);
        }

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        stage.setTitle("Mode");
        stage.setScene(scene);

        stage.show();
    }
}
