package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

public class IslandHandler {


    String greenPath = "/greenDisk.png";
    String redPath = "/redDisk.png";
    String yellowPath = "/yellowDisk.png";
    String pinkPath = "/pinkDisk.png";
    String bluePath = "/blueDisk.png";

    Image greenImage = new Image(greenPath);
    Image redImage = new Image(redPath);
    Image yellowImage = new Image(yellowPath);
    Image pinkImage = new Image(pinkPath);
    Image blueImage = new Image(bluePath);



    public void addStudent(TilePane tilePane, ColoredDisc disc) {

        ImageView newView  = new ImageView();
        Image image = null;
        switch(disc)
        {
            case GREEN:
                image = new Image(greenPath);
                break;
            case BLUE:
                image = new Image(bluePath);
                break;
            case RED:
                image = new Image(redPath);
                break;
            case PINK:
                image = new Image(pinkPath);
                break;
            case YELLOW:
                image = new Image(yellowPath);
                break;
        }

        newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.setImage(image);
        tilePane.getChildren().add(newView);

    }





}
