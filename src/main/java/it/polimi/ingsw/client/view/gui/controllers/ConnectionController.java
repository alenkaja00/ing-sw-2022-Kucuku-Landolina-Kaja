package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.controller.ClientControllerSingleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * this class is used to show the connection screen, where the player can submit ip and port
 */
public class ConnectionController
{

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
    private Label portLabel, bannerText;

    private ClientController clientController;

    String Ip;
    int Port;
    String Nickname;

    /**
     * initializes the screen with buttons, labels and textfields
     */
    @FXML
    private void initialize()
    {
        bannerText.setText("Connect to your game server!");

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


    /**
     * if there are no errors, it connects with the server using the clientcontroller method and
     * updates the view showing the nickname textfield
     */
    @FXML
    private void requestConnection()
    {
        clientController = ClientControllerSingleton.getInstance().getClientController();
        Ip = ipTextField.getText();
        try {
            Port = Integer.parseInt(portTextField.getText());
        }catch (Exception e)
        {
            bannerText.setText("Invalid port number. Try again!");
            return;
        }

        bannerText.setText("Attempting Connection to the server...");

        Platform.runLater(()->
        {
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
                bannerText.setText("Select your Nickname!");
            }
            else
            {
                bannerText.setText("Connection failed... Try again!");
            }
        });


    }


    /**
     * without errors it manages to take the input from the nickname field and
     * pass it to the server using the clientcontroller's method
     * @throws IOException
     */
    public void requestNickname() throws IOException {

        Platform.runLater(()->{
            Nickname = nicknameTextField.getText();
            if (Nickname==null || Nickname.equals(""))
            {
                bannerText.setText("The field is empty! Insert a nickname to proceed!");
                return;
            }

            if(clientController.requestNickname(Nickname))
            {
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
            else
            {
                bannerText.setText("This nickname is already taken. Choose another one!");
            }

            System.out.println("Succesfully requested nickname");
        });
    }

    /**
     * returns to the main scene
     * @param actionEvent user generated event
     */
    public void goBack(ActionEvent actionEvent) {
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
