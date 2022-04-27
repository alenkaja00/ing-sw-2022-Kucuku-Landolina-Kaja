package it.polimi.ingsw.client.view.gui;

import it.polimi.ingsw.server.model.components.ColoredDisc;

import javax.swing.*;

public class graphicalSpot
{
    int x;
    int y;
    boolean isInDashboard;
    boolean isThereDisk;
    ColoredDisc disc;
    public ImageIcon image ;

    public graphicalSpot(int x, int y, boolean isInDashboard) {
        this.x = x;
        this.y = y;
        this.isInDashboard = isInDashboard;
        isThereDisk = false;
    }

    public graphicalSpot(int x, int y, boolean isInDashboard,ColoredDisc disc) {
        this.x = x;
        this.y = y;
        this.isInDashboard = isInDashboard;
        isThereDisk = false;
        this.disc = disc;
    }


}
