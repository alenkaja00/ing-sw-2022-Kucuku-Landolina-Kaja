package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class ModeController {

    @FXML
    Parent root;
    @FXML
    private Button normal;
    @FXML
    private Button expert;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;


    int players = 0;
    boolean expertMode = false;



    public void enterLobby(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/waitingScene.fxml").toURI().toURL());
        root = loader.load();

        //connection reques with parameters  playernumber, mode
        if(actionEvent.getSource().equals(expert))
        {
            expertMode = true;
            System.out.println("EXPERT ON");
        }
        else if(actionEvent.getSource().equals(normal))
        {
            System.out.println("NORMAL");
        }


        WaitingController controller = loader.getController();
        controller.connectionRequest(players, expertMode);


        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        stage.setTitle("Lobby");
        stage.setScene(scene);

        stage.show();

    }


    public void setPlayersNum(int playersNum){
        players = playersNum;
    }


}
