
package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modeView extends JFrame implements ActionListener
{

    JButton Simplebutton;
    JButton Expertbutton;


    public modeView()
    {

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

        this.setSize(1280,850);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(label);
        this.add(Simplebutton);
        this.add(Expertbutton);
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
        new modeView();

    }

}
