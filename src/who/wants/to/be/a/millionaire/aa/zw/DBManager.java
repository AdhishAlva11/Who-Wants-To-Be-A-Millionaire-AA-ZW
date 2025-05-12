/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.sql.*;

/**
 *
 * @author Adhis
 */
public class DBManager {

    private static final String DB_URL = "jdbc:derby:database/HighScoresDB;create=true"; // JDBC URL for our embeded Derby Database

    /*
    this method opens a connection to the Derby database 
    other classes use thi when the want to query or update database
    */
    public static Connection getConnection() throws SQLException  
    {
        return DriverManager.getConnection(DB_URL);
    }

    /*
    this method creates the HIGHSCORE TABLE if it does not already exist
    sets up 2 attributes Name (text used as primary key so each player is unque) 
    score an integer representing the players best score
    */
    public static void createTableIfNotExists() 
    {
        String sql = "CREATE TABLE HIGHSCORES ("
                + "NAME VARCHAR(50) PRIMARY KEY, "
                + "SCORE INT)";
        //try to opn a database connection and create the table
        try (Connection conn = getConnection(); 
                Statement stmt = conn.createStatement()) 
        {

            stmt.executeUpdate(sql); // this will try to create the table
        } 
        catch (SQLException e) 
        {
            //if the table already exists, DERBY will throw SQL state XOY32
            if (e.getSQLState().equals("X0Y32")) 
            {
               
                System.out.println("Table already exists."); // this was added after error after first run. this solves issue. 
            } else {
                // if some other SQL exception happens then print this for debugging 
                e.printStackTrace();
            }

        }
    }
}
