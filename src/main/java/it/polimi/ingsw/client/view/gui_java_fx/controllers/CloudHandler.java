package it.polimi.ingsw.client.view.gui_java_fx.controllers;

import it.polimi.ingsw.server.model.components.Cloud;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class CloudHandler {

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


    public void updateCloudSpot(ColoredDisc disc, ImageView student){

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


    public void updateCloud(ArrayList<ImageView> students, Cloud cloud)
    {
        ArrayList<ColoredDisc> studentSpots = cloud.getStudents();

        for(int i=0 ; i< studentSpots.size() ; i++ )
        {
            updateCloudSpot(studentSpots.get(i), students.get(i));
        }
        for(int i=studentSpots.size() ; i< students.size() ; i++ )
        {
            students.get(i).setOpacity(0.0);
        }

    }

}
