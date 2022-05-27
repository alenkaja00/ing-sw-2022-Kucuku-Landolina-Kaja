package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

public class IslandHandler {

    @FXML
    private TilePane tilePane;

    @FXML
    private ImageView island1;

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


    @FXML
    public void initialize() {



    }


    public void addGreen(MouseEvent mouseEvent) {
        ImageView newView  = new ImageView();
        newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.setImage(greenImage);
        tilePane.getChildren().add(newView);
        System.out.println("tilePane max height : "+tilePane.getMaxHeight());
        System.out.println("island height : " + island1.getFitHeight() );
        System.out.println("tilepane real height : "+ tilePane.heightProperty().floatValue());
    }

    public void addRed(MouseEvent mouseEvent) {
        ImageView newView  = new ImageView();
        newView.setImage(redImage);
        tilePane.getChildren().add(newView);
    }

    public void addYellow(MouseEvent mouseEvent) {
        ImageView newView  = new ImageView();
        newView.setImage(yellowImage);
        tilePane.getChildren().add(newView);
    }

    public void addPink(MouseEvent mouseEvent) {
        ImageView newView  = new ImageView();
        newView.setImage(pinkImage);
        tilePane.getChildren().add(newView);
    }

    public void addBlue(MouseEvent mouseEvent) {
        ImageView newView  = new ImageView();
        newView.setImage(blueImage);
        tilePane.getChildren().add(newView);
    }

    public void clickAttempt()
    {

    }

    public void MoveToDashboard()
    {

    }



}
