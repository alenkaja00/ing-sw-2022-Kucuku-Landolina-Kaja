package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.view.jsonObjects.jDashboard;
import it.polimi.ingsw.client.view.jsonObjects.jPlayer;
import it.polimi.ingsw.server.model.cards.Wizard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Tower;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * util that handles some methods related to dashboards
 */
public class DashboardHandler {


    String greenPath = "/StudentDisks/greenDisk.png";
    String redPath = "/StudentDisks/redDisk.png";
    String yellowPath = "/StudentDisks/yellowDisk.png";
    String pinkPath = "/StudentDisks/pinkDisk.png";
    String bluePath = "/StudentDisks/blueDisk.png";

    Image greenImage = new Image(greenPath);
    Image redImage = new Image(redPath);
    Image yellowImage = new Image(yellowPath);
    Image pinkImage = new Image(pinkPath);
    Image blueImage = new Image(bluePath);

    String blackPath = "/Towers/black_tower.png";
    String greyPath = "/Towers/grey_tower.png";
    String whitePath = "/Towers/white_tower.png";

    Image blackImage = new Image(blackPath);
    Image greyImage = new Image(greyPath);
    Image whiteImage = new Image(whitePath);

    String onlinePath = "/online.png";
    String offlinePath = "/offline.png";

    Image online = new Image(onlinePath);
    Image offline = new Image(offlinePath);

    String professorGreen = "/Professors/greenTeacher.png";
    String professorRed = "/Professors/redTeacher.png";
    String professorYellow = "/Professors/yellowTeacher.png";
    String professorPink = "/Professors/pinkTeacher.png";
    String professorBlue = "/Professors/blueTeacher.png";

    Image greenTeacher = new Image(professorGreen);
    Image redTeacher = new Image(professorRed);
    Image yellowTeacher = new Image(professorYellow);
    Image pinkTeacher = new Image(professorPink);
    Image blueTeacher = new Image(professorBlue);


    ArrayList<String> helperPaths = new ArrayList<>(Arrays.asList("/Assistants/Assistente1.png","/Assistants/Assistente2.png","/Assistants/Assistente3.png",
            "/Assistants/Assistente4.png","/Assistants/Assistente5.png","/Assistants/Assistente6.png","/Assistants/Assistente7.png",
            "/Assistants/Assistente8.png","/Assistants/Assistente9.png","/Assistants/Assistente10.png"));

    /**
     * uses all the methods before to update the dashboard view using the jdashboard object
     * @param dashboard is the object we want to represent
     * @param Entrance contains all the imageviews of the entrance
     * @param Tables contains all the imageviews of the tables
     * @param Professors contains all the imageviews of the professors
     * @param Towers contains all the imageviews of the towers
     * @param towerColor can be white,black or grey
     * @param player is the player who owns the dashboard
     * @param Status is either connected or not
     * @param Nickname is the player's nickname
     * @param CoinsAmount is the amount of coins a player has
     * @param HelperCard is the selected helpercard in that turn
     * @param CardValue is the value of that card
     */
    public void updateDashboard(jDashboard dashboard, ArrayList<ImageView> Entrance, ArrayList<ImageView> Tables, ArrayList<ImageView> Professors, ArrayList<ImageView> Towers, Tower towerColor, jPlayer player, ImageView Status, Label Nickname, Label CoinsAmount, ImageView HelperCard, int CardValue)
    {
        //update labels
        if(player.online)
        {
            Status.setImage(online);
        }
        else
        {
            Status.setImage(offline);
        }

        Nickname.setText(player.nickname);
        CoinsAmount.setText(player.coinsAmount + "x ");

        if(CardValue>0)
        {
            HelperCard.setImage(new Image (helperPaths.get(CardValue-1)));
        }

        // Update entrance :
        for(int i=0;i<dashboard.entranceSpots.length;i++)
        {
            updateSpot(dashboard.entranceSpots[i],Entrance.get(i));
        }
        for(int i = dashboard.entranceSpots.length; i< Entrance.size(); i++ )
        {
            Entrance.get(i).setOpacity(0.0);
        }

        // Update Tables && Professors:
        for(int i = 0; i < 5 ; i++)
        {
            for(int j=0; j < dashboard.tables.get(fromIndexToColor(i)); j++)
            {
                updateSpot(fromIndexToColor(i), Tables.get((i*10)+j));
            }
            for(int j = dashboard.tables.get(fromIndexToColor(i));j<10;j++)
            {
                updateSpot(null, Tables.get((i*10)+j));
            }

            updateProfessorSpot(dashboard.professorSpots.contains(fromIndexToColor(i)), fromIndexToColor(i),  Professors.get(i));
        }

        //Towers:
        for(int i=0;i<dashboard.towerNumber;i++)
        {
            updateTower(towerColor,Towers.get(i));
        }
        for(int i = dashboard.towerNumber;i<Towers.size();i++)
        {
            Towers.get(i).setOpacity(0.0);
        }
    }

    /**
     * @param disk is the wanted color
     * @param professor specifies if the wanted image is a professor or a student
     * @return a student or a professor image of the selected color
     */
    private Image getImageFromColor(ColoredDisc disk, boolean professor){
        Image student = null;
        Image teacher = null;
        switch (disk)
        {
            case GREEN:
                student = greenImage;
                teacher = greenTeacher;
                break;
            case RED:
                student = redImage;
                teacher = redTeacher;
                break;
            case YELLOW:
                student = yellowImage;
                teacher = yellowTeacher;
                break;
            case PINK:
                student = pinkImage;
                teacher = pinkTeacher;
                break;
            case BLUE:
                student = blueImage;
                teacher = blueTeacher;
        }
        if(professor)
        {
            return teacher;
        }
        return student;
    }

    /**
     * sets the appropriate image to the imageview
     * @param disc can be a color or null
     * @param spot the chosen imageview
     */
    public void updateSpot(ColoredDisc disc, ImageView spot)
    {
        Image image;
        if(disc==null)
        {
            spot.setOpacity(0.0);
            return;
        }

        image = getImageFromColor(disc,false);
        spot.setOpacity(1.0);
        spot.setImage(image);

    }

    /**
     * sets the image to the professor spot if bool is true
     * @param bool flag to control the method
     * @param color is the professor color
     * @param professor is the imageview in the screen
     */
    public void updateProfessorSpot(Boolean bool, ColoredDisc color, ImageView professor)
    {
        if (bool) professor.setOpacity(1.0);
        else professor.setOpacity(0.0);
        professor.setImage(getImageFromColor(color,true));
    }

    /**
     * this util matches an index to a color
     * @param ind is the index
     * @return the corrisponding color
     */
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

    /**
     * changes the color of the tower
     * @param towerColor can be white, black, grey
     * @param towerView is the imageview that contains the image
     */
    public void updateTower(Tower towerColor, ImageView towerView)
    {
        Image image = getImageFromTower(towerColor);
        towerView.setOpacity(1.0);
        towerView.setImage(image);
    }

    /**
     * this util returns an image depending on the wanted color
     * @param towerColor is the chosen color
     * @return the corrisponding image
     */
    public Image getImageFromTower(Tower towerColor)
    {
        Image image = null;
        switch (towerColor)
        {
            case BLACK:
                image = blackImage;
                break;
            case GREY:
                image = greyImage;
                break;
            case WHITE:
                image = whiteImage;
                break;
        }
        return  image;
    }
}