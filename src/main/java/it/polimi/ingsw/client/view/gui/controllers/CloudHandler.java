package it.polimi.ingsw.client.view.gui.controllers;

import it.polimi.ingsw.client.view.jsonObjects.jCloud;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * util that handles some methods related to clouds
 */
public class CloudHandler {

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


    /**
     * if disc is null, the image is set invisble, otherwise the color is changed
     * @param disc is the new color or null
     * @param student is the image that is updated
     */
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


    /**
     * the graphic cloud is updated using the jcloud
     * @param students us the array of graphical students in the cloud
     * @param cloud contains the data needed to represent a cloud
     */
    public void updateCloud(ArrayList<ImageView> students, jCloud cloud)
    {
        ArrayList<ColoredDisc> studentSpots = cloud.studentSpots;

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
