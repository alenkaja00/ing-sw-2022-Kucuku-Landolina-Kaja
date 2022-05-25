package it.polimi.ingsw.client.view.gui_java_fx.controllers;


import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Dashboard;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
    private ImageView Entrance0;
    @FXML
    private ImageView Entrance1;
    @FXML
    private ImageView Entrance2;
    @FXML
    private ImageView Entrance3;
    @FXML
    private ImageView Entrance4;
    @FXML
    private ImageView Entrance5;
    @FXML
    private ImageView Entrance6;
    @FXML
    private ImageView Entrance7;
    @FXML
    private ImageView Entrance8;


    @FXML
    private ImageView Tables0;
    @FXML
    private ImageView Tables1;
    @FXML
    private ImageView Tables2;
    @FXML
    private ImageView Tables3;
    @FXML
    private ImageView Tables4;
    @FXML
    private ImageView Tables5;
    @FXML
    private ImageView Tables6;
    @FXML
    private ImageView Tables7;
    @FXML
    private ImageView Tables8;
    @FXML
    private ImageView Tables9;
    @FXML
    private ImageView Tables10;
    @FXML
    private ImageView Tables11;
    @FXML
    private ImageView Tables12;
    @FXML
    private ImageView Tables13;
    @FXML
    private ImageView Tables14;
    @FXML
    private ImageView Tables15;
    @FXML
    private ImageView Tables16;
    @FXML
    private ImageView Tables17;
    @FXML
    private ImageView Tables18;
    @FXML
    private ImageView Tables19;
    @FXML
    private ImageView Tables20;
    @FXML
    private ImageView Tables21;
    @FXML
    private ImageView Tables22;
    @FXML
    private ImageView Tables23;
    @FXML
    private ImageView Tables24;
    @FXML
    private ImageView Tables25;
    @FXML
    private ImageView Tables26;
    @FXML
    private ImageView Tables27;
    @FXML
    private ImageView Tables28;
    @FXML
    private ImageView Tables29;
    @FXML
    private ImageView Tables30;
    @FXML
    private ImageView Tables31;
    @FXML
    private ImageView Tables32;
    @FXML
    private ImageView Tables33;
    @FXML
    private ImageView Tables34;
    @FXML
    private ImageView Tables35;
    @FXML
    private ImageView Tables36;
    @FXML
    private ImageView Tables37;
    @FXML
    private ImageView Tables38;
    @FXML
    private ImageView Tables39;
    @FXML
    private ImageView Tables40;
    @FXML
    private ImageView Tables41;
    @FXML
    private ImageView Tables42;
    @FXML
    private ImageView Tables43;
    @FXML
    private ImageView Tables44;
    @FXML
    private ImageView Tables45;
    @FXML
    private ImageView Tables46;
    @FXML
    private ImageView Tables47;
    @FXML
    private ImageView Tables48;
    @FXML
    private ImageView Tables49;

    @FXML
    private ImageView Professors0;
    @FXML
    private ImageView Professors1;
    @FXML
    private ImageView Professors2;
    @FXML
    private ImageView Professors3;
    @FXML
    private ImageView Professors4;

    @FXML
    private ImageView Towers0;
    @FXML
    private ImageView Towers1;
    @FXML
    private ImageView Towers2;
    @FXML
    private ImageView Towers3;
    @FXML
    private ImageView Towers4;
    @FXML
    private ImageView Towers5;
    @FXML
    private ImageView Towers6;
    @FXML
    private ImageView Towers7;


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

    ArrayList<ImageView> Entrance;

    ArrayList<ImageView> Tables;

    ArrayList<ImageView> Professors;

    ArrayList<ImageView> Towers;

    ColoredDisc clicked;


    @FXML
    public void initialize() {

        //Entrance4.setImage(yellowImage);
        Entrance = new ArrayList<>();
        Entrance.add(Entrance0);
        Entrance.add(Entrance1);
        Entrance.add(Entrance2);
        Entrance.add(Entrance3);
        Entrance.add(Entrance4);
        Entrance.add(Entrance5);
        Entrance.add(Entrance6);
        Entrance.add(Entrance7);
        Entrance.add(Entrance8);

        Tables = new ArrayList<>();

        Tables.add(Tables0);
        Tables.add(Tables1);
        Tables.add(Tables2);
        Tables.add(Tables3);
        Tables.add(Tables4);
        Tables.add(Tables5);
        Tables.add(Tables6);
        Tables.add(Tables7);
        Tables.add(Tables8);
        Tables.add(Tables9);
        Tables.add(Tables10);
        Tables.add(Tables11);
        Tables.add(Tables12);
        Tables.add(Tables13);
        Tables.add(Tables14);
        Tables.add(Tables15);
        Tables.add(Tables16);
        Tables.add(Tables17);
        Tables.add(Tables18);
        Tables.add(Tables19);
        Tables.add(Tables20);
        Tables.add(Tables21);
        Tables.add(Tables22);
        Tables.add(Tables23);
        Tables.add(Tables24);
        Tables.add(Tables25);
        Tables.add(Tables26);
        Tables.add(Tables27);
        Tables.add(Tables28);
        Tables.add(Tables29);
        Tables.add(Tables30);
        Tables.add(Tables31);
        Tables.add(Tables32);
        Tables.add(Tables33);
        Tables.add(Tables34);
        Tables.add(Tables35);
        Tables.add(Tables36);
        Tables.add(Tables37);
        Tables.add(Tables38);
        Tables.add(Tables39);
        Tables.add(Tables40);
        Tables.add(Tables41);
        Tables.add(Tables42);
        Tables.add(Tables43);
        Tables.add(Tables44);
        Tables.add(Tables45);
        Tables.add(Tables46);
        Tables.add(Tables47);
        Tables.add(Tables48);
        Tables.add(Tables49);

        Professors = new ArrayList<>();

        Professors.add(Professors0);
        Professors.add(Professors1);
        Professors.add(Professors2);
        Professors.add(Professors3);
        Professors.add(Professors4);

        Towers = new ArrayList<>();

        Towers.add(Towers0);
        Towers.add(Towers1);
        Towers.add(Towers2);
        Towers.add(Towers3);
        Towers.add(Towers4);
        Towers.add(Towers5);
        Towers.add(Towers6);
        Towers.add(Towers7);

        clicked = null;

        Dashboard dashboard = new Dashboard(3);
        dashboard.addToTables(ColoredDisc.RED);
        dashboard.addToTables(ColoredDisc.BLUE);
        dashboard.addToTables(ColoredDisc.BLUE);
        dashboard.AddToEntrance(ColoredDisc.GREEN,0);
        dashboard.AddToEntrance(ColoredDisc.YELLOW,1);
        HashSet<ColoredDisc> set = new HashSet<>();
        set.add(ColoredDisc.BLUE);
        dashboard.professorSpots = set;

        updateDashboard(dashboard);

    }

    public void updateDashboard(Dashboard dashboard)
    {
        // Update entrance :
        for(int i=0;i<dashboard.getEntranceSpots().length;i++)
        {
            updateEntranceSpot(dashboard.getEntranceSpots()[i],Entrance.get(i));
        }

        // Update Tables && Professors:
        for(int i=0 ; i<5; i++)
        {
            //Tables
            for(int j=0;j< dashboard.SittedStudents(fromIndexToColor(i));j++)
            {
                updateGenericImage(true,Tables.get((i*10)+j));
            }
            for(int j = dashboard.SittedStudents(fromIndexToColor(i));j<10;j++)
            {
                updateGenericImage(false,Tables.get((i*10)+j));
            }

            //Professors

            updateGenericImage(dashboard.professorSpots.contains(fromIndexToColor(i)),Professors.get(i));
        }



    }

    public void updateEntranceSpot(ColoredDisc disc, ImageView student) {

        Image image = null;

        if(disc==null)
        {
            student.setOpacity(0.0);
            return;
        }

        switch (disc)
        {
            case GREEN:
                image = greenImage;
                break;
            case RED:
                image = redImage;
                break;
            case YELLOW:
                image = yellowImage;
                break;
            case PINK:
                image = pinkImage;
                break;
            case BLUE:
                image = blueImage;
        }

        student.setOpacity(1.0);
        student.setImage(image);

    }

    public void updateGenericImage(Boolean bool, ImageView generic)
    {
        if(bool) generic.setOpacity(1.0);
        else generic.setOpacity(0.0);
    }
    public ColoredDisc fromIndexToColor(int ind)
    {
        ColoredDisc disc = null;
        switch (ind)
        {
            case 0:
                disc = ColoredDisc.GREEN;
                break;
            case 1:
                disc = ColoredDisc.RED;
                break;
            case 2:
                disc = ColoredDisc.YELLOW;
                break;
            case 3:
                disc = ColoredDisc.PINK;
                break;
            case 4:
                disc = ColoredDisc.BLUE;
                break;
        }
        return disc;
    }


    public void clickAttempt(MouseEvent mouseEvent) {

        Image image = null;
        for(int i=0; i<Entrance.size();i++)
        {
            if(Entrance.get(i).equals(mouseEvent.getSource())) image = Entrance.get(i).getImage();
        }
        if(image!=null)
        {
            if(image.equals(greenImage))
            {
                System.out.println("green");
                clicked = ColoredDisc.GREEN;
            }
            if(image.equals(redImage))
            {
                System.out.println("red");
                clicked = ColoredDisc.RED;
            }
            if(image.equals(yellowImage))
            {
                System.out.println("yellow");
                clicked = ColoredDisc.YELLOW;
            }
            if(image.equals(pinkImage))
            {
                System.out.println("pink");
                clicked = ColoredDisc.PINK;
            }
            if(image.equals(blueImage))
            {
                System.out.println("blue");
                clicked = ColoredDisc.BLUE;
            }

        }
    }



}
