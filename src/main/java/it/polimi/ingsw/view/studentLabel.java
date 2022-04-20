package it.polimi.ingsw.view;

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
    int circleRadius = 60;
    graphicalSpot[] entrance;
    graphicalSpot[] redline;

    public studentLabel()
    {
        redDisk = new ImageIcon(this.getClass().getResource("/redDisk22.png"));
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
        System.out.println("mouse clicked");
        int i = 0;
        int chosenInd  = 0;
        boolean flag = false;
        for(graphicalSpot spot : entrance)
        {
            if(spot.isThereDisk && distance(spot.x+circleRadius/2,e.getX(), spot.y+circleRadius/2, e.getY())<60)
            {
                chosenInd = i;
                flag = true;
            }
            i++;
        }
        entrance[chosenInd].isThereDisk = false;
        if(flag)
        {
            for(graphicalSpot spot : redline)
            {
                if(spot.isThereDisk == false)
                {
                    spot.isThereDisk = true ;
                    break;
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
