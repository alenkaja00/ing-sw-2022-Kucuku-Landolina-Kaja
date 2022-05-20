package it.polimi.ingsw.client.view.gui_java_fx.controllers;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ExternalDashboardController {


    @FXML
    Parent root;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private GridPane gridPane;



    @FXML
    public void initialize() {
        System.out.println("sld(jfn");
        Image image = new Image(getClass().getResource("greenDisk.png").toString());
        gridPane.add(new ImageView(image),1,2);
    }



}
