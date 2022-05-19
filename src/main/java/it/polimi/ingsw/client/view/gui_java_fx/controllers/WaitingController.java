package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class WaitingController {

    @FXML
    private Label waitingText;
    @FXML
    private ProgressBar progressBar;

    public void connectionRequest(int playerNum, boolean expert){
        System.out.println("WAITING");
    }

}
