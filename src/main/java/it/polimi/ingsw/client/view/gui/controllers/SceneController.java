package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.controller.ClientControllerSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * contains method to change screen
 */
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

    /**
     * changes scene to connectionScene
     * @param actionEvent triggers this method
     */
    public void connectionScene(javafx.event.ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/connectionScene.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setId("connectionScene");
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/cssFiles/main.css").toExternalForm());
        stage.setTitle("Ip and Port");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * changes scene to quitScene
     * @param actionEvent triggers this method
     */
    public void quitScene(javafx.event.ActionEvent actionEvent)
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

    /**
     * changes the scene to the playernumberscene
     * @param actionEvent triggers this method
     * @throws IOException
     */
    public void playerNumberScene(javafx.event.ActionEvent actionEvent)
    {
        if(!ClientControllerSingleton.getInstance().getClientController().getServerIP().equals(""))
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxmlFiles/playerNumberScene.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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