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

public class ManageHighScore {

    private final Map<String, Integer> scores; // store name and score
    private final String filename = "highscores.txt"; // save to this file

    public ManageHighScore() {
        scores = new HashMap<>();
        loadScores();
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                scores.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("No previous scores found.");
        }
    }

    public void saveScores() // save scores map to file
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save scores.");
        }
    }

    public void updateScore(String name, int score) // load score to map
    {
        scores.put(name, Math.max(score, scores.getOrDefault(name, 0))); // look for exsisting score if none set to zero
    }

    public void printScores() {
        System.out.println("High Scores:");

        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());//Convert the map entries to a list

        
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() //Sort the list by values in descending order
        {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue()); // descending
            }
        });
         
        for (Map.Entry<String, Integer> entry : list) //Print the sorted scores
        {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

}
