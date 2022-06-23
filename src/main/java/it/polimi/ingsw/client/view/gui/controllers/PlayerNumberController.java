package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.stream.FactoryConfigurationError;
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

    @FXML
    private Label bannerText;

    @FXML
    private ToggleButton twoPlayers, threePlayers, expert, normal;

    int playerNumber = 2;
    Boolean expertMode = false;

    public void initialize()
    {
        bannerText.setText("Select player number and game mode!");
        normal.setSelected(true);
        twoPlayers.setSelected(true);
        expert.setSelected(false);
        threePlayers.setSelected(false);
    }

    @FXML
    private void buttonClicked(ActionEvent actionEvent)
    {
        ToggleButton chosen = (ToggleButton) actionEvent.getSource();
        if (chosen.equals(twoPlayers))
        {
            twoPlayers.setSelected(true);
            threePlayers.setSelected(false);
            playerNumber = 2;
        }
        else if (chosen.equals(threePlayers))
        {
            twoPlayers.setSelected(false);
            threePlayers.setSelected(true);
            playerNumber = 3;
        }
        else if (chosen.equals(expert))
        {
            normal.setSelected(false);
            expert.setSelected(true);
            expertMode = true;
        }
        else if (chosen.equals(normal))
        {
            normal.setSelected(true);
            expert.setSelected(false);
            expertMode = false;
        }
    }


    public void Submit(ActionEvent actionEvent) throws IOException
    {
        ClientController clientController = ClientControllerSingleton.getInstance().getClientController();
        if(!clientController.requestNewGame(playerNumber,expertMode))
        {
            // handle error
            bannerText.setText("Unable to create a game in this moment!");
        }

    }
}
