package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.view.jsonObjects.jEffectCard;
import it.polimi.ingsw.client.view.jsonObjects.jIsland;
import it.polimi.ingsw.server.model.cards.EffectCard;
import it.polimi.ingsw.server.model.cards.EffectName;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Tower;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * util that handles some methods related to islands
 */
public class IslandHandler {


    private String greenPath = "/StudentDisks/greenDisk.png";
    private String redPath = "/StudentDisks/redDisk.png";
    private String yellowPath = "/StudentDisks/yellowDisk.png";
    private String pinkPath = "/StudentDisks/pinkDisk.png";
    private String bluePath = "/StudentDisks/blueDisk.png";
    private String motherNaturePath = "mother_nature.png";
    private String denialPath = "/denial.png";

    private Image greenImage = new Image(greenPath);
    private Image redImage = new Image(redPath);
    private Image yellowImage = new Image(yellowPath);
    private Image pinkImage = new Image(pinkPath);
    private Image blueImage = new Image(bluePath);

    private ArrayList<String> towersPath = new ArrayList<>(Arrays.asList("/Towers/black_tower.png","/Towers/grey_tower.png","/Towers/white_tower.png"));
    private ArrayList<String> unitedIslands = new ArrayList<>(Arrays.asList("/Islands/United/island2.png",
            "/Islands/United/island3.png","/Islands/United/island4.png","/Islands/United/island5.png",
            "/Islands/United/island6.png","/Islands/United/island7.png","/Islands/United/island8.png"));

    /**
     * @param disc is the color that specifies the wanted image
     * @return the appropriate image depending on the chosen color
     */
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


    /**
     * adds a student to the island
     * @param tilePane is the pane that contains the student
     * @param disc is the student we want to add
     */
    public ImageView addStudent(TilePane tilePane, ColoredDisc disc)
    {
        ImageView newView  = new ImageView();
        Image image = getImageFromColor(disc);

        newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.25));
        newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.25));
        newView.setImage(image);
        tilePane.getChildren().add(newView);
        return newView;
    }

    /**
     * @param disc is the chosen color
     * @return the string of the chosen color
     */
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

    /**
     * given the objects, it updates the graphical island
     * @param tilePane is the pane that represents the island
     * @param imageView is the imageview of the united islands
     * @param island is the object we represent
     * @param currentIslandID is the ID of the island where mother nature is located
     */
    public void updateIsland(TilePane tilePane, ImageView imageView, jIsland island, int currentIslandID)
    {
        tilePane.getChildren().removeAll(tilePane.getChildren());

        if (island.graphicalIsland.size()>1)
            imageView.setImage(new Image(unitedIslands.get(island.graphicalIsland.size()-2)));



        if (island.prohibited)
        {
            ImageView newView  = new ImageView();
            Image image = new Image(denialPath);

            newView.fitHeightProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.fitWidthProperty().bind(tilePane.widthProperty().multiply(0.25));
            newView.setImage(image);
            tilePane.getChildren().add(newView);
        }

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
