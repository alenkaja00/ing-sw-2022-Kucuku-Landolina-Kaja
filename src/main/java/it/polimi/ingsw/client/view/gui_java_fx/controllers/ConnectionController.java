package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
public class ConnectionController {

    @FXML
    private TextField portTextField;
    @FXML
    private Button submitButton ;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField ipTextField;



    public void nicknameScene() throws IOException {

        String ip = ipTextField.getText();
        String port = portTextField.getText();

        if(valid(ip,port))
        {
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/nicknameScene.fxml").toURI().toURL());
            root = loader.load();

            stage = (Stage) ((Node) submitButton).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Nickname Scene");
            stage.setScene(scene);

            stage.show();

        }




    }

    public boolean valid (String ip, String port)
    {
        return (ip.equals("127.0.0.1") && port.equals("3030"));

    }

}
