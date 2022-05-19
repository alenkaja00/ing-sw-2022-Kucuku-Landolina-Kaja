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

public class NicknameController {

    private String Nickname;
    @FXML
    private Parent root;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private Button submitButton;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;


    public void goBack() throws IOException
    {
        Nickname = nicknameTextField.getText();
        if(valid(Nickname))
        {
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/fxmlFiles/mainScene.fxml").toURI().toURL());
            root = loader.load();
            SceneController controller = loader.getController();
            controller.setConnectionEstablished(true);

            stage = (Stage) ((Node) submitButton).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui_java_fx/cssFiles/main.css").toURI().toURL().toExternalForm());
            stage.setTitle("Main Scene");
            stage.setScene(scene);

            stage.show();
        }

    }

    public boolean valid(String Nickname)
    {
        return !(Nickname.contains(" "));

    }

}


