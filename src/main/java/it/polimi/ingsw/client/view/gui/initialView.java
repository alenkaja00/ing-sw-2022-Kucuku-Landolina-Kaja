package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class initialView extends JFrame implements ActionListener
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
    JButton submitNicknameButton;
    JTextField IPTextField;
    JTextField PortTextField;
    JTextField NicknameTextField;

    JPanel connectionPanel;
    JPanel registrationPanel;

    String state ;

    public initialView()
    {
        // Part related to background and the three initial buttons
        state = "Initial";
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


        submitNicknameButton = new JButton();
        submitNicknameButton.addActionListener(this);
        submitNicknameButton.setBounds(600,250,100,50);
        submitNicknameButton.setOpaque(true);
        submitNicknameButton.setText("Submit");
        submitNicknameButton.setFont(new Font("Arial",Font.PLAIN,20));




        IPTextField = new JTextField();
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

        FocusListener portListener = new FocusListener() {

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
        };
        PortTextField.addFocusListener(portListener);
        PortTextField.addActionListener(this);



        NicknameTextField = new JTextField();

        NicknameTextField.setFont(new Font("Arial",Font.PLAIN,20));
        NicknameTextField.setText("Nickname");
        NicknameTextField.setForeground(Color.GRAY);
        // code to implement a placeholder :

        NicknameTextField.addFocusListener(new FocusListener() {

                                           @Override
                                           public void focusGained(FocusEvent e) {

                                               if(e.getSource()!=NicknameTextField)
                                               {
                                                   return;
                                               }

                                               if(NicknameTextField.getText().equals("Nickname"))
                                               {
                                                   NicknameTextField.setForeground(Color.BLACK);
                                                   NicknameTextField.setText("");
                                               }
                                           }

                                           @Override
                                           public void focusLost(FocusEvent e) {

                                               if(e.getSource()!=NicknameTextField)
                                               {
                                                   return;
                                               }

                                               if(NicknameTextField.getText().isEmpty() || NicknameTextField.getText().equals(""))
                                               {
                                                   NicknameTextField.setForeground(Color.GRAY);
                                                   NicknameTextField.setText("Nickname");

                                               }
                                           }
                                       }
        );


        GridBagLayout gbl = new GridBagLayout();

        GridBagConstraints gbc = new GridBagConstraints();


        connectionPanel = new JPanel();
        connectionPanel.setOpaque(false);
        connectionPanel.setBounds(300,100,700,300);
        connectionPanel.setVisible(true);
        connectionPanel.setLayout(gbl);

        registrationPanel = new JPanel();
        registrationPanel.setOpaque(false);
        registrationPanel.setBounds(300,100,700,300);
        registrationPanel.setVisible(true);
        registrationPanel.setLayout(gbl);




        gbc.gridx = 0;
        gbc.gridy = 0;
        connectionPanel.add(IPTextField,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(7,7,7,7);
        connectionPanel.add(PortTextField,gbc);

        gbc.gridx = 0;
        gbc.gridy  = 1;
        connectionPanel.add(submitButton,gbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        registrationPanel.add(NicknameTextField,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registrationPanel.add(submitNicknameButton,gbc);



        this.setSize(1280, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);






        layeredPane.setBounds(0,0,1280,850);
        layeredPane.setOpaque(true);
        layeredPane.add(bglabel,Integer.valueOf(0));
        layeredPane.add(Playbutton,Integer.valueOf(1));
        layeredPane.add(Quitbutton,Integer.valueOf(1));
        layeredPane.add(Connectionbutton,Integer.valueOf(1));

        connectionPanel.setVisible(false);
        registrationPanel.setVisible(false);

        layeredPane.add(connectionPanel,Integer.valueOf(2));

        layeredPane.add(registrationPanel,Integer.valueOf(1));

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

                IPTextField.setForeground(Color.GRAY);
                IPTextField.setText("IP Address");
                PortTextField.setForeground(Color.GRAY);
                PortTextField.setText("Port");
                NicknameTextField.setForeground(Color.GRAY);
                NicknameTextField.setText("Nickname");



            }
        });


        this.setVisible(true);

    }

    public static void main(String[] args)
    {
        new initialView();
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
            if(state== "Initial")
            {
                Playbutton.setVisible(false);
                Quitbutton.setVisible(false);
                Connectionbutton.setVisible(false);
                connectionPanel.setVisible(true);
                registrationPanel.setVisible(false);
                state = "Connection";
            }

        }

        else if(e.getSource()==submitButton) {
            if(state == "Connection")
            {
                connectionPanel.setVisible(false);
                registrationPanel.setVisible(true);
                state = "Registration";
            }


        }

        else if(e.getSource() == submitNicknameButton)
        {
            registrationPanel.setVisible(false);
            Playbutton.setVisible(true);
            Quitbutton.setVisible(true);
            Connectionbutton.setVisible(true);
            state = "Initial";

        }



    }



}
