package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class prova extends JFrame implements ActionListener
{
    JButton Playbutton;
    JButton Quitbutton;
    JButton Connectionbutton;
    ImageIcon icon ;
    Image resized ;
    JLayeredPane layeredPane;
    JLabel buttonlabel;
    JLabel bglabel;

    JButton submitButton;
    JTextField IPTextField;
    JTextField PortTextField;

    public prova()
    {
        // Part related to background and the three initial buttons
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


        // Part related to the Connection
        submitButton = new JButton();
        submitButton.addActionListener(this);
        submitButton.setBounds(600,250,100,50);
        submitButton.setOpaque(true);
        submitButton.setText("Submit");
        submitButton.setFont(new Font("Arial",Font.PLAIN,20));

        IPTextField = new JTextField();
        IPTextField.setBounds(450,190,200,50);
        IPTextField.setOpaque(true);
        IPTextField.setVisible(true);
        IPTextField.setFont(new Font("Arial",Font.PLAIN,20));
        IPTextField.setForeground(Color.BLACK);
        IPTextField.setText("IP Address");
        // code to implement a placeholder :

        IPTextField.addFocusListener(new FocusListener() {

                                         @Override
                                         public void focusGained(FocusEvent e) {
                                             if(e.getSource()!=IPTextField)
                                             {
                                                 return;
                                             }
                                             if(IPTextField.getText().equals("IP Address"))
                                             {
                                                 IPTextField.setForeground(Color.BLACK);
                                                 IPTextField.setText("");
                                             }
                                         }

                                         @Override
                                         public void focusLost(FocusEvent e) {

                                             if(e.getSource()!=IPTextField)
                                             {
                                                 return;
                                             }

                                             if(IPTextField.getText().isEmpty() || IPTextField.getText().equals(""))
                                             {
                                                 IPTextField.setForeground(Color.GRAY);
                                                 IPTextField.setText("IP Address");

                                             }
                                         }
                                     }
        );

        PortTextField = new JTextField();
        PortTextField.setBounds(650,190,200,50);
        PortTextField.setFont(new Font("Arial",Font.PLAIN,20));
        PortTextField.setText("Port");
        PortTextField.setForeground(Color.GRAY);
        // code to implement a placeholder :

        PortTextField.addFocusListener(new FocusListener() {

                                           @Override
                                           public void focusGained(FocusEvent e) {

                                               if(e.getSource()!=PortTextField)
                                               {
                                                   return;
                                               }

                                               if(PortTextField.getText().equals("Port"))
                                               {
                                                   PortTextField.setForeground(Color.BLACK);
                                                   PortTextField.setText("");
                                               }
                                           }

                                           @Override
                                           public void focusLost(FocusEvent e) {

                                               if(e.getSource()!=PortTextField)
                                               {
                                                   return;
                                               }

                                               if(PortTextField.getText().isEmpty() || PortTextField.getText().equals(""))
                                               {
                                                   PortTextField.setForeground(Color.GRAY);
                                                   PortTextField.setText("Port");

                                               }
                                           }
                                       }
        );



        this.setSize(1280, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);






        layeredPane.setBounds(0,0,1280,850);
        layeredPane.setOpaque(true);
        layeredPane.add(bglabel,Integer.valueOf(0));
        layeredPane.add(Playbutton,Integer.valueOf(1));
        layeredPane.add(Quitbutton,Integer.valueOf(1));
        layeredPane.add(Connectionbutton,Integer.valueOf(1));

        layeredPane.add(submitButton,Integer.valueOf(1));
        layeredPane.add(IPTextField,Integer.valueOf(2));
        layeredPane.add(PortTextField,Integer.valueOf(2));

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
        new prova();
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
