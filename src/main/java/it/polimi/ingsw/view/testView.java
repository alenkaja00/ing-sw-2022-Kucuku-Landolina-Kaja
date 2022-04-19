
package it.polimi.ingsw.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testView extends Frame implements ActionListener
{

    JButton Simplebutton;
    JButton Expertbutton;


    public testView()
    {
        JFrame f = new JFrame();

        ImageIcon image = new ImageIcon(this.getClass().getResource("/eriantys_mode.jpg"));

        JLabel label = new JLabel();
        label.setOpaque(true);

        label.setBounds(0,0,1280,850);

        label.setIcon(image);


        Simplebutton = new JButton();
        Simplebutton.addActionListener(this);
        Simplebutton.setBounds(370,650,300,75);
        Simplebutton.setOpaque(false);
        Simplebutton.setContentAreaFilled(false);
        Simplebutton.setBorderPainted(false);

        Expertbutton = new JButton();
        Expertbutton.addActionListener(this);
        Expertbutton.setBounds(736,650,300,75);
        Expertbutton.setOpaque(false);
        Expertbutton.setContentAreaFilled(false);
        Expertbutton.setBorderPainted(false);

        f.setSize(1280,850);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.add(label);
        f.add(Simplebutton);
        f.add(Expertbutton);
    }

    public void actionPerformed(ActionEvent e)
    {
        String chosen;
        if(e.getSource()==Simplebutton )
        {
            chosen = "semplice";
        }

        else if (e.getSource()==Expertbutton)
        {
            chosen = "esperta";
        }
        else chosen = "";
        System.out.println("Scelta Variante : "+ chosen);
    }


    public static void main(String[] args)
    {
        new testView();

    }

}
