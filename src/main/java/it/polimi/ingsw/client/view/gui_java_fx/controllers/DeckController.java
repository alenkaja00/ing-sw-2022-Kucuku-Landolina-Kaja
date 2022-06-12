package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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

    public void chooseAssistant(MouseEvent mouseEvent) {
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
