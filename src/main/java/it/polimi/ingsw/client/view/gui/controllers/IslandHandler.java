package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.view.jsonObjects.jIsland;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Tower;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IslandHandler {


    String greenPath = "/StudentDisks/greenDisk.png";
    String redPath = "/StudentDisks/redDisk.png";
    String yellowPath = "/StudentDisks/yellowDisk.png";
    String pinkPath = "/StudentDisks/pinkDisk.png";
    String bluePath = "/StudentDisks/blueDisk.png";
    String motherNaturePath = "mother_nature.png";

    Image greenImage = new Image(greenPath);
    Image redImage = new Image(redPath);
    Image yellowImage = new Image(yellowPath);
    Image pinkImage = new Image(pinkPath);
    Image blueImage = new Image(bluePath);

    ArrayList<String> towersPath = new ArrayList<>(Arrays.asList("/Towers/black_tower.png","/Towers/grey_tower.png","/Towers/white_tower.png"));
    ArrayList<String> unitedIslands = new ArrayList<>(Arrays.asList("/Islands/United/island2.png",
            "/Islands/United/island3.png","/Islands/United/island4.png","/Islands/United/island5.png",
            "/Islands/United/island6.png","/Islands/United/island7.png","/Islands/United/island8.png"));

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

        newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.25));
        newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.25));
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

    public void updateIsland(TilePane tilePane, ImageView imageView, jIsland island, int currentIslandID)
    {
        tilePane.getChildren().removeAll(tilePane.getChildren());

        if (island.graphicalIsland.size()>1)
            imageView.setImage(new Image(unitedIslands.get(island.graphicalIsland.size()-2)));


        if (island.ID == currentIslandID)
        {
            ImageView newView  = new ImageView();
            Image image = new Image(motherNaturePath);

            newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.setImage(image);
            tilePane.getChildren().add(newView);
        }

        HashMap<ColoredDisc,Integer> students = island.students;
        for(ColoredDisc color : students.keySet())
        {
            for (int i = 0; i < students.get(color); i++)
            {
                addStudent(tilePane, color);
            }
        }


        /*// Update Students
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
        }*/

        // Update Towers
        for(Tower tower : island.towerList)
        {
            ImageView newView = new ImageView();
            Image image = null;
            switch(tower)
            {
                case BLACK: image = new Image(towersPath.get(0)); break;
                case GREY:  image = new Image(towersPath.get(1)); break;
                case WHITE: image = new Image(towersPath.get(2)); break;

            }
            newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.setImage(image);
            tilePane.getChildren().add(newView);
        }
    }








}
