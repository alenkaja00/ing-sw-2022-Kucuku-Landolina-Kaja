package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class startView extends JFrame implements ActionListener
{
    JButton Playbutton;
    JButton Quitbutton;
    JButton Connectionbutton;
    ImageIcon icon ;
    Image resized ;
    JLayeredPane layeredPane;
    JLabel buttonlabel;
    JLabel bglabel;
    public startView()
    {
        bglabel = new JLabel();
        buttonlabel = new JLabel();
        layeredPane = new JLayeredPane();
        icon = new ImageIcon(this.getClass().getResource("/eriantys.jpg"));
        bglabel.setIcon(icon);
        resized = icon.getImage();


        Playbutton = new JButton();
        Playbutton.addActionListener(this);
        Playbutton.setBounds(500, 350, 300, 75);
        Playbutton.setOpaque(true);
        Playbutton.setText("Play");
        Playbutton.setFont(new Font("Arial",Font.PLAIN,40));


        Quitbutton = new JButton();
        Quitbutton.addActionListener(this);
        Quitbutton.setBounds(500, 500, 300, 75);
        Quitbutton.setOpaque(true);
        Quitbutton.setText("Quit");
        Quitbutton.setFont(new Font("Arial",Font.PLAIN,40));

        Connectionbutton = new JButton();
        Connectionbutton.addActionListener(this);
        Connectionbutton.setBounds(500, 650, 300, 75);
        Connectionbutton.setOpaque(true);
        Connectionbutton.setText("Connection");
        Connectionbutton.setFont(new Font("Arial",Font.PLAIN,40));



        this.setSize(1280, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
/*
        this.add(bglabel);
        this.add(Playbutton);
        this.add(Quitbutton);
        this.add(Connectionbutton);

 */
        layeredPane.setBounds(0,0,1280,850);
        layeredPane.setOpaque(true);
        layeredPane.add(bglabel,Integer.valueOf(0));
        layeredPane.add(Playbutton,Integer.valueOf(1));
        layeredPane.add(Quitbutton,Integer.valueOf(1));
        layeredPane.add(Connectionbutton,Integer.valueOf(1));
        this.add(layeredPane);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int JFrameWidth = e.getComponent().getWidth();
                int JFrameHeight = e.getComponent().getHeight();
                layeredPane.setBounds(0,0,JFrameWidth,JFrameHeight);
                bglabel.setBounds(0,0,JFrameWidth,JFrameHeight);
                resized = icon.getImage().getScaledInstance(bglabel.getWidth(), bglabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(resized);
                bglabel.setIcon(newIcon);

            }
        });
        this.setVisible(true);

    }

    public static void main(String[] args)
    {
        new startView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String chosen = "";
        if(e.getSource() == Playbutton)
        {
            System.out.println("let's play!");
        }

        else if (e.getSource()== Quitbutton)
        {

        }

        else if(e.getSource()==Connectionbutton)
        {

        }
    }



}
