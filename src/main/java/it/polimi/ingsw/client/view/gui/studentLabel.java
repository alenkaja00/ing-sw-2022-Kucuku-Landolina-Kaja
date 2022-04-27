package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.*;

public class studentLabel extends JLabel implements MouseListener
{
    ImageIcon redDisk;
    int entranceX = 40;
    int entranceX2 = 115;
    int entranceY = 65;
    int dashboardX = 235;
    int deltaX = 60;
    int deltaY = 90;
    int circleRadius = 30;
    graphicalSpot[] entrance;
    graphicalSpot[] redline;

    public studentLabel()
    {
        redDisk = new ImageIcon(this.getClass().getResource("/redDisk.png"));
        entrance = new graphicalSpot[9];
        entrance[0] = new graphicalSpot(entranceX2, entranceY, false);
        for (int i = 0; i < 4; i++) {
            entrance[(2 * i) + 1] = new graphicalSpot(entranceX, entranceY + ((i + 1) * deltaY), false);
            entrance[(2 * i) + 2] = new graphicalSpot(entranceX2, entranceY + ((i + 1) * deltaY), false);
        }


        redline = new graphicalSpot[10];
        for (int i = 0; i < 10; i++) {
            redline[i] = new graphicalSpot(dashboardX + (i * deltaX), entranceY + deltaY, true);
        }

        entrance[0].isThereDisk = true;
        entrance[1].isThereDisk = true;
        entrance[2].isThereDisk = true;

        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(graphicalSpot spot : entrance)
        {
            if(spot.isThereDisk)
            {
                redDisk.paintIcon(this,g, spot.x, spot.y);
            }

        }

        for(graphicalSpot spot : redline)
        {
            if(spot.isThereDisk)
            {
                redDisk.paintIcon(this,g, spot.x, spot.y);
            }

        }
        repaint();

    }

    public void mouseClicked(MouseEvent e)
    {
        int i = 0;
        int chosenInd  = 0;
        boolean isInsideEntrance = false;
        boolean isInsideDashboard = false;
        for(graphicalSpot spot : entrance)
        {
            if(spot.isThereDisk && distance(spot.x+circleRadius,e.getX(), spot.y+circleRadius, e.getY())<circleRadius)
            {
                chosenInd = i;
                isInsideEntrance = true;
            }
            i++;
        }

        if(isInsideEntrance)
        {
            entrance[chosenInd].isThereDisk = false;
            for(graphicalSpot spot : redline)
            {
                if(!spot.isThereDisk)
                {
                    spot.isThereDisk = true ;
                    return;
                }
            }

        }
        i = 0;
        for(graphicalSpot spot : redline)
        {
            if(spot.isThereDisk && distance(spot.x+circleRadius,e.getX(), spot.y+circleRadius, e.getY())<circleRadius && !redline[i+1].isThereDisk)
            {
              chosenInd = i;
              isInsideDashboard = true;
            }
            i++;
        }

        if(isInsideDashboard)
        {
            redline[chosenInd].isThereDisk = false;
            for(graphicalSpot spot : entrance)
            {
                if(!spot.isThereDisk)
                {
                    spot.isThereDisk = true;
                    return;
                }
            }

        }


    }

    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int distance(int x1, int x2, int y1, int y2)
    {
        return (int)Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
    }


}
