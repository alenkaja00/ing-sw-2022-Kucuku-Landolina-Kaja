package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


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



    public void enterLobby(ActionEvent actionEvent) throws IOException, InterruptedException {
        /*FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/waitingScene.fxml").toURI().toURL());
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

        stage.show();*/

        FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/wizardScene.fxml").toURI().toURL());
        root = loader.load();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
        stage.setTitle("Wizards");
        stage.setScene(scene);
        stage.show();
    }

    public void callWizard(Stage stage1, boolean flag) throws InterruptedException, IOException {

        if(flag) {
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/wizardScene.fxml").toURI().toURL());
            root = loader.load();

            stage = (Stage) stage1.getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Wizards");
            stage.setScene(scene);
            stage.show();
        }


    }


    public void setPlayersNum(int playersNum){
        players = playersNum;
    }


}
