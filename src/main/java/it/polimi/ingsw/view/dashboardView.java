package it.polimi.ingsw.view;
import javax.swing.*;
import java.awt.*;


public class dashboardView extends JFrame {


    ImageIcon image ;
    public dashboardView() {
        image = new ImageIcon(this.getClass().getResource("/dashboard_resized.png"));
        studentLabel label = new studentLabel();
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
        new dashboardView();

    }




}

