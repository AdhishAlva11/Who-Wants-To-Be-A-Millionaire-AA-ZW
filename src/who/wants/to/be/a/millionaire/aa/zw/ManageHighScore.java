/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

/**
 *
 * @author Adhis
 */
import java.io.*;
import java.util.*;
import java.sql.*;


public class ManageHighScore {

    /*
    constructor: automatically cheks and creates  the DB table if it does not exist. 
     */
    public ManageHighScore() {
        DBManager.createTableIfNotExists();
    }

    /*
    the below method updates the players score in the data base
     */

    public void updateScore(String name, int score) {
        try (Connection conn = DBManager.getConnection()) 
        {
            //check if this player already has a score stored
            String checkQuery = "SELECT SCORE FROM HIGHSCORES WHERE NAME = ?";

            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, name);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) 
            {
                int existingScore = rs.getInt("SCORE");
                if (score > existingScore) // we only save the score if its greater than one previosuly saved
                {
                    String updateSQL = "UPDATE HIGHSCORES SET SCORE = ? WHERE NAME = ?";  // question mark is place holder which will replace with actual name 
                    // here we set the parametres replace the ? 
                    PreparedStatement updateStmt = conn.prepareStatement(updateSQL);

                    updateStmt.setInt(1, score);// fist ? becomes the new score
                    updateStmt.setString(2, name);  // second ? becomes the players name
                    updateStmt.executeUpdate(); // send update to the DB
                }
            } 
            else 
            {// this is actioned if player does not exist in DB - insert new row
                String insertSQL = "INSERT INTO HIGHSCORES (NAME, SCORE) VALUES(?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
                insertStmt.setString(1, name);
                insertStmt.setInt(2, score);
                insertStmt.executeUpdate();

            }

        } catch (SQLException e) {
            // print detailed error if database access fails 
            e.printStackTrace();
        }
    }
    
    public void saveScores() {
        // Intentionally left empty — no action needed
    }
    //this method prints all score in the database in decending order
    public void printScores()
    {
        System.out.println("High Scores");
        
        try(Connection conn = DBManager.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM HIGHSCORES ORDER BY SCOORE DESC"))
        {
            while(rs.next())
            {
                String name = rs.getString("NAME"); 
                int score = rs.getInt("SCORE"); 
                System.out.println(name +": $" + score);
            }
             
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }

}
