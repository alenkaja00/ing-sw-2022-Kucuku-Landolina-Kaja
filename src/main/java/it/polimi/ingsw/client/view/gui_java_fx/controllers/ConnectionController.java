package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.gui_java_fx.mainStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
public class ConnectionController implements Initializable {

    @FXML
    private TextField portTextField;
    @FXML
    private Button submitButton ;
    @FXML
    private Button submitButton2;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Label nicknameLabel;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private Label ipLabel;
    @FXML
    private Label portLabel;

    private ClientController clientController;

    String Ip;
    int Port;
    String Nickname;





    public void requestConnection()
    {
        clientController = ClientControllerSingleton.getInstance().getClientController();
        Ip = ipTextField.getText();
        Port = Integer.parseInt(portTextField.getText());

        if(clientController.requestConnection(Ip,Port))
        {

            nicknameLabel.setVisible(true);
            nicknameLabel.setDisable(false);
            nicknameLabel.setManaged(true);
            nicknameTextField.setVisible(true);
            nicknameTextField.setDisable(false);
            nicknameTextField.setManaged(true);
            submitButton2.setVisible(true);
            submitButton2.setDisable(false);
            submitButton2.setManaged(true);

            ipLabel.setVisible(false);
            ipLabel.setDisable(true);
            ipLabel.setManaged(false);
            portLabel.setVisible(false);
            portLabel.setDisable(true);
            portLabel.setManaged(false);
            ipTextField.setVisible(false);
            ipTextField.setDisable(true);
            ipTextField.setManaged(false);
            portTextField.setVisible(false);
            portTextField.setDisable(true);
            portTextField.setManaged(false);
            submitButton.setVisible(false);
            submitButton.setDisable(true);
            submitButton.setManaged(false);




        }


    }


    public void requestNickname() throws IOException {

        Nickname = nicknameTextField.getText();

        if(clientController.requestNickname(Nickname))
        {
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/mainScene.fxml").toURI().toURL());
            root = loader.load();
            SceneController controller = loader.getController();
            controller.setConnectionEstablished(true);

            //stage = (Stage) ((Node) submitButton).getScene().getWindow();
            stage = StageSingleton.getInstance().getStage();
            scene = new Scene(root);

            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Main Scene");
            stage.setScene(scene);

            stage.show();

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nicknameLabel.setVisible(false);
        nicknameLabel.setDisable(true);
        nicknameLabel.setManaged(false);
        nicknameTextField.setVisible(false);
        nicknameTextField.setDisable(true);
        nicknameTextField.setManaged(false);
        submitButton2.setVisible(false);
        submitButton2.setDisable(true);
        submitButton2.setManaged(false);
    }
}
