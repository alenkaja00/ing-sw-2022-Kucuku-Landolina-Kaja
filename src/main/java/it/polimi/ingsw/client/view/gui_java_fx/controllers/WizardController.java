package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class WizardController {
    @FXML
    ImageView wizard1;
    @FXML
    ImageView wizard2;
    @FXML
    ImageView wizard3;
    @FXML
    ImageView wizard4;

    public void chooseWizard(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(wizard1)){
            System.out.println("MAGO1");
        }
        if(mouseEvent.getSource().equals(wizard2)){
            System.out.println("MAGO2");
        }
        if(mouseEvent.getSource().equals(wizard3)){
            System.out.println("MAGO3");
        }
        if(mouseEvent.getSource().equals(wizard4)){
            System.out.println("MAGO4");
        }
    }
}
