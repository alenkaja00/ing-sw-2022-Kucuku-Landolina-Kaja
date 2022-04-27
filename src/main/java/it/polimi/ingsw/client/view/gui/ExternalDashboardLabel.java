package it.polimi.ingsw.client.view.gui;


import com.google.gson.Gson;
import it.polimi.ingsw.client.view.jsonObjects.jDashboard;
import it.polimi.ingsw.server.model.components.ColoredDisc;
import it.polimi.ingsw.server.model.components.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// This will allow the client to see other players' dashboard.
// No interaction with the user --> no mouseListener
// An update method will update the view using the json string
public class ExternalDashboardLabel extends JLabel
{
    jDashboard dashboard;
    Gson gson;
    ImageIcon greenDisk;
    ImageIcon redDisk;
    ImageIcon yellowDisk;
    ImageIcon pinkDisk;
    ImageIcon blueDisk;
    int entranceX = 40;
    int entranceX2 = 115;
    int entranceY = 65;
    int dashboardX = 235;
    int deltaX = 60;
    int deltaY = 90;
    int circleRadius = 30;
    graphicalSpot[] entrance;
    graphicalSpot[] graphicalDashboard;
    public ExternalDashboardLabel(String startingJson)
    {
           gson = new Gson();
           dashboard = gson.fromJson(startingJson, jDashboard.class);


           redDisk = new ImageIcon(this.getClass().getResource("/redDisk.png"));
           blueDisk = new ImageIcon(this.getClass().getResource("/blueDisk.png"));
           pinkDisk = new ImageIcon(this.getClass().getResource("/pinkDisk.png"));
           yellowDisk = new ImageIcon(this.getClass().getResource("/yellowDisk.png"));
           greenDisk = new ImageIcon(this.getClass().getResource("/greenDisk.png"));
           entrance = new graphicalSpot[9];
           graphicalDashboard = new graphicalSpot[50];
           entrance[0] = new graphicalSpot(entranceX2, entranceY, false);

           for (int i = 0; i < 4; i++) {
               entrance[(2 * i) + 1] = new graphicalSpot(entranceX, entranceY + ((i + 1) * deltaY), false);
               entrance[(2 * i) + 2] = new graphicalSpot(entranceX2, entranceY + ((i + 1) * deltaY), false);
           }

           for(int i=0;i<5;i++)
           {
               ColoredDisc disc = ColoredDisc.RED;
               switch (i)
               {
                   case 0 :
                       disc = ColoredDisc.GREEN;
                       break;
                   case 1 :
                       disc = ColoredDisc.RED;
                       break;
                   case 2:
                       disc = ColoredDisc.YELLOW;
                       break;
                   case 3:
                       disc = ColoredDisc.PINK;
                       break;
                   case 4 :
                       disc = ColoredDisc.BLUE;
                       break;
               }

               for(int j=0; j<10; j++)
               {
                    graphicalDashboard[(i*10)+j] = new graphicalSpot(dashboardX + (j * deltaX),entranceY + (i * deltaY),true,disc);
               }
           }

    }


    public void update(String json)
    {
        dashboard = gson.fromJson(json, jDashboard.class);
        ColoredDisc[] entranceData = dashboard.entranceSpots;
        for(int i = 0; i< 9; i++)
        {
            if(entranceData[i]== null)
            {
                entrance[i].isThereDisk = false;
            }
            else
            {
                entrance[i].isThereDisk = true;
                entrance[i].disc = entranceData[i];
            }
        }

        int i = 0;
        for(ColoredDisc color : ColoredDisc.values())
        {
            int n = dashboard.tables.get(graphicalDashboard[(i*10)].disc);
            for(int j = 0; j< n ; j++)
            {
                graphicalDashboard[(i*10)+j].isThereDisk = true;
            }
            for(int j=n;j<10;j++)
            {
                graphicalDashboard[(i*10)+j].isThereDisk = false;
            }
            i++;
        }

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(graphicalSpot spot : entrance)
        {
            setImageDisk(spot);
            if(spot.image != null)
            {
                spot.image.paintIcon(this,g,spot.x,spot.y);
            }
        }
        for(graphicalSpot spot : graphicalDashboard)
        {
            setImageDisk(spot);
            if(spot.image != null)
            {
                spot.image.paintIcon(this,g,spot.x,spot.y);
            }
        }
        repaint();
    }

    public void setImageDisk( graphicalSpot spot)
    {
        if(spot.isThereDisk)
        {
            switch(spot.disc)
            {
                case GREEN:
                    spot.image = greenDisk;
                    break;
                case RED:
                    spot.image = redDisk;
                    break;
                case BLUE:
                    spot.image = blueDisk;
                    break;
                case YELLOW:
                    spot.image =   yellowDisk;
                    break;
                case PINK:
                    spot.image = pinkDisk;
                    break;
            }

        }

    }

}
