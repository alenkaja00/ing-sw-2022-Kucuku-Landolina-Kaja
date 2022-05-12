package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;

public class CloudView extends JFrame {


    public CloudView()
    {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        CloudLabel cloudLabel = new CloudLabel(2);
        cloudLabel.setOpaque(true);
        cloudLabel.setVisible(true);
        cloudLabel.setBounds(0,0,1000,1000);
        cloudLabel.setBackground(Color.CYAN);


        this.setSize(1000,1000);
        this.add(cloudLabel);
        this.setVisible(true);

    }

    public static void main(String[] args)
    {

        new CloudView();
    }

}
