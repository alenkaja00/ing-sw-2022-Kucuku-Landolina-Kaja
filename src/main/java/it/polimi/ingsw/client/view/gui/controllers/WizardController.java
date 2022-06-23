package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.server.model.cards.Wizard;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WizardController {
    @FXML
    ImageView wizard1, wizard2, wizard3, wizard4;

    @FXML
    private Stage stage;
    private Scene scene;

    private ArrayList<ImageView> wizards;

    private ImageView clickedWizard;

    @FXML
    private Label bannerText;

    @FXML
    public void initialize(){
        wizards = new ArrayList<>(Arrays.asList(wizard1, wizard2, wizard3, wizard4));

        bannerText.setText("Select your wizard!");

        wizards.stream().forEach(x-> {
            x.setDisable(false);
            x.setOpacity(1);
            x.setScaleX(1);
            x.setScaleY(1);
        });
    }

    private void resetCards()
    {
        wizards.stream().forEach(x-> {
            x.setDisable(false);
            x.setOpacity(1);
            x.setScaleX(1);
            x.setScaleY(1);
            clickedWizard = null;
        });
    }

    @FXML
    private void chooseWizard(MouseEvent mouseEvent) throws IOException
    {
        clickedWizard = (ImageView) mouseEvent.getSource();
        Wizard wizard = null;

        if(mouseEvent.getSource().equals(wizard1)){
            System.out.println("MAGO1");
            wizard = Wizard.WIZARD1;
        }
        if(mouseEvent.getSource().equals(wizard2)){
            System.out.println("MAGO2");
            wizard = Wizard.WIZARD2;
        }
        if(mouseEvent.getSource().equals(wizard3)){
            System.out.println("MAGO3");
            wizard = Wizard.WIZARD3;
        }
        if(mouseEvent.getSource().equals(wizard4)){
            System.out.println("MAGO4");
            wizard = Wizard.WIZARD4;
        }

        clickedWizard.setScaleX(1.1);
        clickedWizard.setScaleY(1.1);

        for(ImageView wiz : wizards)
        {
            wiz.setDisable(true);
            if(wiz != clickedWizard)
            {
                wiz.setOpacity(0.5);
            }
        }

        if (ClientControllerSingleton.getInstance().getClientController().requestWizard(wizard))
        {
            bannerText.setText("Waiting for game start...");
            Task waitTurn = new Task<Void>() {
                @Override
                public Void call() {
                    do {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (ClientControllerSingleton.getInstance().getClientController().getViewLocked());
                    return null;
                }
            };
            waitTurn.setOnSucceeded(event -> {
                stage = StageSingleton.getInstance().getStage();
                scene = GameSceneSingleton.getInstance().getGameScene();

                stage.setTitle("GameMap");
                stage.setScene(scene);
                stage.show();

                Task gameLogic = new Task<Void>() {
                    @Override
                    public Void call() {
                        GameSceneSingleton.getInstance().getController().ETX();
                        return null;
                    }
                };
                new Thread(gameLogic).start();
            });
            new Thread(waitTurn).start();
        }
        else
        {
            bannerText.setText("Invalid selection! Choose another wizard");
            resetCards();
        }
    }

    @FXML
    private void mouseEntered(MouseEvent mouseEvent)
    {
        if (clickedWizard !=null) return;
        ImageView image = (ImageView) mouseEvent.getSource();
        for(ImageView img : wizards)
        {
            if(img.equals(image))
            {
                img.setScaleY(1.05);
                img.setScaleX(1.05);
            }
        }
    }

    @FXML
    private void mouseExited(MouseEvent mouseEvent)
    {
        if (clickedWizard !=null) return;
        resetCards();
    }
}
