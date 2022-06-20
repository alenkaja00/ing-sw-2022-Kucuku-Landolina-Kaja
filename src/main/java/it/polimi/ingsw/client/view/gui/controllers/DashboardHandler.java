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

    ArrayList<String> helperPaths = new ArrayList<>(Arrays.asList("/Assistants/Assistente1.png","/Assistants/Assistente2.png","/Assistants/Assistente3.png",
            "/Assistants/Assistente4.png","/Assistants/Assistente5.png","/Assistants/Assistente6.png","/Assistants/Assistente7.png",
            "/Assistants/Assistente8.png","/Assistants/Assistente9.png","/Assistants/Assistente10.png"));

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
        CoinsAmount.setText(String.valueOf(player.coinsAmount)+"x ");

        if(CardValue>0)
        {
            HelperCard.setImage(new Image (helperPaths.get(CardValue-1)));
        }

        // Update entrance :
        for(int i=0;i<dashboard.entranceSpots.length;i++)
        {
            updateEntranceSpot(dashboard.entranceSpots[i],Entrance.get(i));
        }
        for(int i = dashboard.entranceSpots.length; i< Entrance.size(); i++ )
        {
            Entrance.get(i).setOpacity(0.0);
        }

        // Update Tables && Professors:
        for(int i=0 ; i<5; i++)
        {
            //Tables
            for(int j=0;j< dashboard.tables.get(fromIndexToColor(i));j++)
            {
                updateGenericImage(true,Tables.get((i*10)+j));
            }
            for(int j = dashboard.tables.get(fromIndexToColor(i));j<10;j++)
            {
                updateGenericImage(false,Tables.get((i*10)+j));
            }

            //Professors

            updateGenericImage(dashboard.professorSpots.contains(fromIndexToColor(i)),Professors.get(i));
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

    public int fromColorToIndex(ColoredDisc disc)
    {
        int index = -1;
        switch (disc)
        {
            case GREEN:
                index = 0;
                break;

            case RED:
                index =1;
                break;

            case YELLOW:
                index = 2;
                break;

            case PINK:
                index = 3;
                break;
            case BLUE:
                index = 4;
                break;
        }
        return index;
    }

    public void updateTower(Tower towerColor, ImageView towerView)
    {
        Image image = getImageFromTower(towerColor);
        towerView.setOpacity(1.0);
        towerView.setImage(image);
    }

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
