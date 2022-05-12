package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;

public class CloudLabel extends JLabel {

    int playerNumber;
    public CloudLabel(int n)
    {
        playerNumber = n;
        this.setSize(1000,1000);
    }

    @Override
    public void paintComponent(Graphics g) {
        //Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g);
        if(playerNumber == 2)
        {
           g.setColor(Color.WHITE);
           g.fillOval(200,300,250,250);
           g.fillOval(600,300,250,250);
        }
        repaint();
    }
}
