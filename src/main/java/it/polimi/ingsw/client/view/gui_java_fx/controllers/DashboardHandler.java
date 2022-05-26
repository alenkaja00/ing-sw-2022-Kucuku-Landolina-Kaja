package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Dashboard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class DashboardHandler {


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




    public void updateDashboard(Dashboard dashboard, ArrayList<ImageView> Entrance, ArrayList<ImageView> Tables, ArrayList<ImageView> Professors)
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








}
