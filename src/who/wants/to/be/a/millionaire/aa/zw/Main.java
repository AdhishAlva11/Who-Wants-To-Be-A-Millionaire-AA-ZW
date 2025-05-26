/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.EventQueue.invokeLater;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
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
 * This class is the starting point of the program it shows a simple GUI window
 * where the user can input their name and click start game button to start game
 *
 * @author Adhis
 */
public class Main extends JFrame {

    // constructor sets up the GUI window 
    public Main() {
        setTitle("Who Wants to Be a Millionaire"); //window title
        setSize(400, 280); // set window size (width x height) 
        setLocationRelativeTo(null); // centre window on screen 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // use BorderLayout for arranging compoenets

        //---- top panel welcome message -----  
        JPanel topPanel = new JPanel(new GridLayout(2, 1));  // two rows one for "Welcome" and one for game title

        JLabel welcomeLabel = new JLabel("Welcome to", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); // bold largee font for welcome

        //---- game name display 
        JLabel titleLabel = new JLabel("Who Wants to Be a Millionaire", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));     // bigger font for game title
        titleLabel.setForeground(new Color(0, 51, 102));           // Dark blue color

        topPanel.add(welcomeLabel);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);  // add both labels to the top panel and position it at the top of the window
        //----Centre panel for name input -------

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 rows  vertical spacing

        JLabel nameLabel = new JLabel("Enter Your Name:", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Slightly smaller font

        JTextField nameField = new JTextField();
        nameField.setHorizontalAlignment(JTextField.CENTER);           // center text in the field
        nameField.setPreferredSize(new Dimension(100, 25));            // Make the field smaller

        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        add(centerPanel, BorderLayout.CENTER); // Add center panel

        // ---- Bottom start button  ---- 
        JButton startButton = new JButton("Start Game");
        add(startButton, BorderLayout.SOUTH); // Add to bottom of layout

        //private method when user clicks start button 
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim(); // get the entered name
                if (!name.isEmpty()) {
                    dispose();
                    new GamePanel(name); // open gamePanel GUI. 
                } else {
                    //if name is empty, show pop up message  
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
