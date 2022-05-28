package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Island;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.HashMap;

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


    public Image getImageFromColor(ColoredDisc disc)
    {
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
        return image;

    }



    public void addStudent(TilePane tilePane, ColoredDisc disc) {

        ImageView newView  = new ImageView();
        Image image = getImageFromColor(disc);

        newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.1));
        newView.setImage(image);
        tilePane.getChildren().add(newView);

    }

    public String getStringFromColor(ColoredDisc disc)
    {
        String ret  = "";
        switch (disc) {
            case GREEN:
                ret = "green";
                break;
            case RED:
                ret = "red";
                break;
            case YELLOW:
                ret = "yellow";
                break;
            case PINK:
                ret = "pink";
                break;
            case BLUE:
                ret = "blue";
                break;

        }
        return ret;
    }


    public int calculateStudents(TilePane tilePane, ColoredDisc disc)
    {
        int studentsNumber = 0;

        ObservableList<Node> graphicalStudents = tilePane.getChildren();
        for(Node node : graphicalStudents)
        {
            if(!(node instanceof ImageView)) continue;
            ImageView graphicalStudent = (ImageView) node;
            if(graphicalStudent.getImage().getUrl().contains(getStringFromColor(disc)))
            {
                studentsNumber ++;
            }
        }

        return studentsNumber;
    }



    public void removeStudent(TilePane tilePane, ColoredDisc disc)
    {
        for(Node node : tilePane.getChildren())
        {
            if(!(node instanceof ImageView)) continue;
            ImageView graphicalStudent = (ImageView) node;
            if(graphicalStudent.getImage().getUrl().contains(getStringFromColor(disc)))
            {
                tilePane.getChildren().remove(node);
                return;
            }
        }

    }

    public void updateIsland(TilePane tilePane, Island island)
    {
        // Update Students
        HashMap<ColoredDisc,Integer> students = island.getStudents();
        for(ColoredDisc color : ColoredDisc.values())
        {
            int colorNumber = students.get(color);
            int currentStudentsNumber = calculateStudents(tilePane,color);
            if(currentStudentsNumber < colorNumber)
            {
                for(int i =0; i< colorNumber-currentStudentsNumber;i++)
                {
                    addStudent(tilePane,color);
                }
            }
            else if(currentStudentsNumber > colorNumber)
            {
                for(int i=0; i<currentStudentsNumber-colorNumber;i++)
                {
                    removeStudent(tilePane,color);
                }
            }
        }

        // Update Towers
    }








}
