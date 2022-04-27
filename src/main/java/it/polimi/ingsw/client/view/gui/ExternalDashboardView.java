package it.polimi.ingsw.client.view.gui;

import javax.swing.*;

public class ExternalDashboardView extends JFrame
{
    static String testJson;
    ExternalDashboardLabel label;

    ImageIcon image ;
    public ExternalDashboardView() {
        testJson = "{\"maxTowers\":8,\"entranceSpots\":[\"BLUE\",\"PINK\",\"YELLOW\",\"PINK\",\"PINK\",\"RED\",\"BLUE\"],\"tables\":{\"BLUE\":2,\"PINK\":3,\"YELLOW\":4,\"GREEN\":3,\"RED\":3},\"towerNumber\":0,\"maxEntrance\":7,\"professorSpots\":[]}";
        image = new ImageIcon(this.getClass().getResource("/dashboard_resized.png"));
        label = new ExternalDashboardLabel(testJson);
        label.setOpaque(true);
        label.setBounds(0, 0, 1280, 555);
        label.setIcon(image);


        this.setSize(1280, 555);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.add(label);

    }

    public static void main(String args[]) {
        ExternalDashboardView view =new ExternalDashboardView();
        view.label.update(testJson);
    }

}
