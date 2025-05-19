/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.*;
import javax.swing.*;
import java.sql.*; 
/**
 *
 * @author Adhis
 * 
 * This class creates a window that shows the leader board 
 * it pulls the score from the Derby embedded database 
 * and displays all player score in descending order. 
 */
public class LeaderBoardWindow extends JFrame {
    
    public LeaderBoardWindow ()
    {
        setTitle("Leaderboard"); // window title  
        setSize(400, 400);  // set size 
        setLocationRelativeTo(null); // center the window on screen 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window when x is clicked
        setLayout(new BorderLayout()); // use border layout 
        
        //----Title at the top of the leader board --- 
        
        JLabel titleLabel = new JLabel("High Scores", SwingConstants.CENTER); // center label 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));// font style 
        add(titleLabel, BorderLayout.NORTH); //add label to the top 
        
        //---- Text area to show scores ---- 
        
        JTextArea scoreArea = new JTextArea(); // plain text display area
        scoreArea.setEditable(false); // makes it read only  
        scoreArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        JScrollPane scrollPane = new JScrollPane(scoreArea);  // add scroll bar 
        add(scrollPane, BorderLayout.CENTER); // put score display in the center 
        
        
        //----- Load scores from DB and display them --- 
        loadScores(scoreArea); 
        setVisible(true); // show leaderboard window 
        
        
        
    }
    
    private void loadScores(JTextArea area)
    {
       try (Connection conn = DBManager.getConnection(); 
               Statement stmt = conn.createStatement(); 
               ResultSet rs = stmt.executeQuery("SELECT * FROM HIGHSCORES ORDER BY SCORE DESC"))
       {
           while(rs.next())
           {
               String name = rs.getString("NAME"); // get player name 
               int score = rs.getInt("SCORE"); 
               //format display left align name and right align score 
               area.append(String.format("%-20s $%d\n", name, score));
           }
       }
       catch(SQLException e)
       {
           area.setText("Error loading leaderboard");
           e.printStackTrace();
       }
    }
    
}
