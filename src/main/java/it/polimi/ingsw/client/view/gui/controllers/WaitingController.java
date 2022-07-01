package it.polimi.ingsw.client.view.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class WaitingController {


    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private Label waitingText;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button home;

    @FXML
    public void initialize()
    {
        showButton(true);
    }

    public void connectionRequest(int playerNum, boolean expert){
        System.out.println("WAITING");
    }

    public void showMessage(String message)
    {
        waitingText.setText(message);
    }

    public void showButton(Boolean val)
    {
        home.setDisable(!val);
        home.setVisible(val);
    }

    public void Home(ActionEvent actionEvent) {
        ClientControllerSingleton.getInstance().getClientController().quitLobby();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/mainScene.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setId("startScene");
        SceneController controller = loader.getController();
        controller.setConnectionEstablished(true);

        //stage = (Stage) ((Node) submitButton).getScene().getWindow();
        stage = StageSingleton.getInstance().getStage();
        scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        stage.setTitle("Main Scene");
        stage.setScene(scene);

        stage.show();
    }
}
