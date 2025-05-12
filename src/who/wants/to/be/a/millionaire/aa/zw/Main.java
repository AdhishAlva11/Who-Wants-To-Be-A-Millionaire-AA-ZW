/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        JLabel titleLabel = new JLabel("Enter Your Name to Start: ", SwingConstants.CENTER); 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String name = scanner.nextLine();

        String easyFile = "easy.txt";
        String mediumFile = "medium.txt";
        String hardFile = "hard.txt";

        Game game = new Game(name, easyFile, mediumFile, hardFile);
        game.play();
    }
}

