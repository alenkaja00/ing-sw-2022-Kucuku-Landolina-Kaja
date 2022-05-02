package it.polimi.ingsw.client.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ConnectionFrame extends JFrame implements ActionListener
{
    JButton button;
    JTextField IPTextField;
    JTextField PortTextField;


    public ConnectionFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Submit");
        button.addActionListener(this);

        IPTextField = new JTextField();
        IPTextField.setPreferredSize(new Dimension(250,40));
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
        PortTextField.setPreferredSize(new Dimension(250,40));
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




        this.add(IPTextField);
        this.add(PortTextField);
        this.add(button);
        this.setSize(500,500);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            IPTextField.setText("");
            PortTextField.setText("");
        }

    }


    public static  void main(String[] args)
    {
        new ConnectionFrame();

    }
}
