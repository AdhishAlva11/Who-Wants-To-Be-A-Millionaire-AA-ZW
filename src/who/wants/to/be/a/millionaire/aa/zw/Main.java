/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.BorderLayout;
import static java.awt.EventQueue.invokeLater;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *This class is the starting point of the program 
 * it shows a simple GUI window where the user can input their name
 * and click start game button to start game
 * @author Adhis
 */
public class Main  extends JFrame {
    
    // constructor sets up the GUI window 
    public Main () 
    {
        setTitle("Who Wants to Be a Millionaire"); //window title
        setSize(400, 250); // set window size (width x height) 
        setLocationRelativeTo(null); // centre window on screen 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());  // use BorderLayout for arranging compoenets
        
        // in the north we have the heading label 
        JLabel titleLabel = new JLabel("Enter Your Name to Start: ", SwingConstants.CENTER); // centered texts
        titleLabel.setFont(new Font ("Arial", Font.BOLD, 18)); // Make text bigger and bold
        add(titleLabel, BorderLayout.NORTH); // add to top of layout
        
        // in the centre we will allow user to input name 
        JPanel centerPanel  = new JPanel(); // creates a new panel to hold input component 
        centerPanel.setLayout(new GridLayout(2,1,10,10)); // create a grid layout 2 number of rows, 1 num of columns, 10 horizontal gap, 10 vertical gap 
        JTextField nameField = new JTextField();  // input field for username
        centerPanel.add(new JLabel("Your Name:", SwingConstants.CENTER)); // label above text feild 
        centerPanel.add(nameField); // add text feild to input name
        add(centerPanel, BorderLayout.CENTER); // add to centre of window 
        
        // this will position start button south 
        
        JButton startButton = new JButton("Start Game"); // this creates the buton
        add(startButton, BorderLayout.SOUTH); // adds button to bottom of window. 
        
        //private method when user clicks start button 
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String name = nameField.getText().trim(); // get the entered name
                if(!name.isEmpty())
                {
                    dispose(); 
                    new GamePanel(name); // open gamePanel GUI. 
                }
                else 
                {
                    JOptionPane.showMessageDialog(Main.this, "Please enter your name. ");
                }
            }
                    
        });
        
        setVisible(true); 
        
        
    }
    public static void main(String[] args) {
        // run the GUI code safely on the event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main(); // Show the main menu window
            }
        });
        
    }
}

