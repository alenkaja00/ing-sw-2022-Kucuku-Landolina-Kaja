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


    public void chooseWizard(MouseEvent mouseEvent) throws IOException {

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
        }
    }
}
