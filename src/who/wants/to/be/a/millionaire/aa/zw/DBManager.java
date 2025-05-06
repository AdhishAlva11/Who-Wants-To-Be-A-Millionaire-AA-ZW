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
    private static final String DB_URL = "jdbc:derby:database/HighScoresDB;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTableIfNotExists() {
        String sql = "CREATE TABLE HIGHSCORES (" +
                     "NAME VARCHAR(50) PRIMARY KEY, " +
                     "SCORE INT)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) { // Table already exists
                System.out.println("Table already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
}