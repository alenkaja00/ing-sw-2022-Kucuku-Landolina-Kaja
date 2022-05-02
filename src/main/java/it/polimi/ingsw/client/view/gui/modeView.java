
package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class modeView extends JFrame implements ActionListener {

    JButton Simplebutton;
    JButton Expertbutton;
    int JFrameWidth;
    int JFrameHeight;
    Insets insets;
    ImageIcon icon ;
    Image resized ;
    Image resizedImage;

    public modeView() {


        icon = new ImageIcon(this.getClass().getResource("/eriantys_mode.jpg"));
        resized = icon.getImage();
        resizedImage = icon.getImage();

        JLabel label = new JLabel();
        label.setOpaque(true);

        //label.setBounds(0, 0, 1280, 850);

        //label.setIcon(image);


        Simplebutton = new JButton();
        Simplebutton.addActionListener(this);
        Simplebutton.setBounds(370, 650, 300, 75);
        Simplebutton.setOpaque(false);
        Simplebutton.setContentAreaFilled(false);
        Simplebutton.setBorderPainted(false);

        Expertbutton = new JButton();
        Expertbutton.addActionListener(this);
        Expertbutton.setBounds(736, 650, 300, 75);
        Expertbutton.setOpaque(false);
        Expertbutton.setContentAreaFilled(false);
        Expertbutton.setBorderPainted(false);

        this.setSize(1280, 850);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(label);
        this.add(Simplebutton);
        this.add(Expertbutton);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JFrameWidth = e.getComponent().getWidth();
                JFrameHeight = e.getComponent().getHeight();
                resized = resizedImage.getScaledInstance(JFrameWidth, JFrameHeight, Image.SCALE_SMOOTH);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String chosen;
        if (e.getSource() == Simplebutton) {
            chosen = "semplice";
        } else if (e.getSource() == Expertbutton) {
            chosen = "esperta";
        } else chosen = "";
        System.out.println("Scelta Variante : " + chosen);

    }



    public static void main(String[] args) {
        new modeView();

    }


    public void paint(Graphics g) {
        //super.paint(g);
        if(insets == null)
        {
            insets = getInsets();
        }

        g.drawImage(resized, insets.left, insets.top, this);

    }
}