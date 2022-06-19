package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerNumberController {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;



    @FXML
    private ToggleButton playersButton;
    @FXML
    private  ToggleButton modeButton;

    @FXML
    private Button submit;




    public void Submit(ActionEvent actionEvent) throws IOException {

        int playerNumber = 2;
        Boolean expertMode = false;

        if (playersButton.isSelected()) playerNumber=3;
        if(modeButton.isSelected()) expertMode = true;


        ClientController clientController = ClientControllerSingleton.getInstance().getClientController();
        if(!clientController.requestNewGame(playerNumber,expertMode))
        {
            // handle error
            System.out.println("unable to create game");
        }

    }
}
