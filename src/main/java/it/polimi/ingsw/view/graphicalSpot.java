package it.polimi.ingsw.view;

public class graphicalSpot
{
    int x;
    int y;
    boolean isInDashboard;
    boolean isThereDisk;

    public graphicalSpot(int x, int y, boolean isInDashboard) {
        this.x = x;
        this.y = y;
        this.isInDashboard = isInDashboard;
        isThereDisk = false;
    }
}
