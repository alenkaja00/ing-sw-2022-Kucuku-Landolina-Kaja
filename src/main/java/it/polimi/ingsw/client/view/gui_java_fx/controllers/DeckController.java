package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class DeckController {
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private ImageView card5;
    @FXML
    private ImageView card6;
    @FXML
    private ImageView card7;
    @FXML
    private ImageView card8;
    @FXML
    private ImageView card9;
    @FXML
    private ImageView card10;
    @FXML
    private GridPane deckGrid;

    @FXML
    private Label infoLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private StackPane stack;

    private ArrayList<ImageView> deck;

    private double realScaleX;
    private double realScaleY;

    private boolean clicked;

    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    public void initialize()
    {
        deck = new ArrayList<>();
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        deck.add(card7);
        deck.add(card8);
        deck.add(card9);
        deck.add(card10);

        clicked = false;


        //
        stack.setPrefHeight(deckGrid.getWidth()*0.3);
        stack.setPrefWidth(deckGrid.getWidth()*0.16);
        //

        errorLabel.setVisible(false);
        errorLabel.setDisable(true);

        realScaleX = card1.getScaleX();
        realScaleY = card2.getScaleY();
    }

    public void chooseAssistant(MouseEvent mouseEvent) throws IOException {
        ImageView chosen = (ImageView) mouseEvent.getSource();
        for(ImageView image : deck){
            if(image != chosen){
                image.setOpacity(0.5);
            }
        }
        chosen.setOpacity(1);
        if(!clicked) {
            chosen.setScaleX(chosen.getScaleX() * 1.5);
            chosen.setScaleY(chosen.getScaleY() * 1.5);
        }
        clicked = true;


        int choise = -1;
        for(int i =0; i<deck.size();i++)
        {
            if(deck.get(i).equals(chosen))choise = i;
        }


        int finalChoise1 = choise;
        Platform.runLater(()->{
            System.out.println("Waiting for your turn");
            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (ClientControllerSingleton.getInstance().getClientController().getViewLocked());
            System.out.println("It's your turn");
            System.out.println("deck");



            //stage = (Stage) ((Node) submitButton).getScene().getWindow();
            stage = StageSingleton.getInstance().getStage();
            scene = GameSceneSingleton.getInstance().getGameScene();


            int finalChoise = finalChoise1 +1;
            if(ClientControllerSingleton.getInstance().getClientController().requestHelper(finalChoise))
            {  stage.setTitle("GameMap");
                stage.setScene(scene);
                stage.show();}
            }
          );

    }

    public void enlightenOpacity(MouseEvent mouseEvent) {
        if(!clicked){
            mouseEvent.getSource();
            ImageView image = (ImageView) mouseEvent.getSource();
            for(ImageView img : deck){
                if(img != image)
                {
                    img.setOpacity(0.5);
                }
            }
        }
    }

    public void resetOpacity(MouseEvent mouseEvent) {
        if(!clicked) {
            for(ImageView img : deck){
                img.setOpacity(1.0);
                img.setScaleY(realScaleX);
                img.setScaleX(realScaleY);
            }
        }
        clicked = false;
    }
}
