package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.server.model.cards.Wizard;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class WizardController {
    @FXML
    ImageView wizard1;
    @FXML
    ImageView wizard2;
    @FXML
    ImageView wizard3;
    @FXML
    ImageView wizard4;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<ImageView> wizards;
    private double realScaleX;
    private double realScaleY;


    @FXML
    public void initialize(){
        wizards = new ArrayList<>();
        wizards.add(wizard1);
        wizards.add(wizard2);
        wizards.add(wizard3);
        wizards.add(wizard4);

        realScaleX = wizard1.getScaleX();
        realScaleY = wizard2.getScaleY();
    }

    public void chooseWizard(MouseEvent mouseEvent) throws IOException {

        Wizard wizard = null;
        ImageView chosen = (ImageView) mouseEvent.getSource();

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

        for(ImageView wiz : wizards)
        {
            chosen.setScaleX(realScaleX * 1.5);
            chosen.setScaleY(realScaleY * 1.5);

            if(mouseEvent.getSource()!=wiz)
            {
                wiz.setOpacity(0.7);
            }
        }

        if(wizard!=null)
        {
            if (ClientControllerSingleton.getInstance().getClientController().requestWizard(wizard))
            {
                Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        System.out.println("Waiting for your turn");
                        do {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (ClientControllerSingleton.getInstance().getClientController().getViewLocked());
                        System.out.println("It's your turn");
                        return null;
                    }
                };
                task.setOnSucceeded(taskFinishEvent -> Platform.runLater(
                        ()->
                        {
                            FXMLLoader loader = null;
                            try {
                                loader = new FXMLLoader(new File("src/main/java/it/polimi/ingsw/client/view/gui/fxmlFiles/deckScene.fxml").toURI().toURL());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);


                            try {
                                scene.getStylesheets().add(new File("src/main/java/it/polimi/ingsw/client/view/gui/cssFiles/main.css").toURI().toURL().toExternalForm());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            stage.setTitle("Ip and Port");
                            stage.setScene(scene);


                            stage.show();
                        }
                ));
                new Thread(task).start();
            }
            else
            {
                for(ImageView wiz : wizards)
                {
                    wiz.setOpacity(1.0);
                }
                chosen.setScaleX(realScaleX);
                chosen.setScaleY(realScaleY);
            }
        }
    }

    public void enlightenOpacity(MouseEvent mouseEvent)
    {
        ImageView image = (ImageView) mouseEvent.getSource();
        for(ImageView img : wizards)
        {
            if(img != image)
            {
                img.setOpacity(0.5);
            }
        }
    }

    public void resetOpacity(MouseEvent mouseEvent)
    {
        for(ImageView img : wizards)
        {
            img.setOpacity(1.0);
            img.setScaleY(realScaleX);
            img.setScaleX(realScaleY);

        }
    }
}
