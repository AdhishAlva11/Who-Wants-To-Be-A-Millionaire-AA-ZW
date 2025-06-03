/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Adhis
 * This class has  been updated for GUI version
 * 
 * This class implements the Ask the Audience lifeline for the quiz game
 * 
 * had to be updated for GUI (updates) 
 * - originally this life line printed results to the console. 
 * now it returns an int [] array with vote percentage for each option (A,B,C,D)
 * this makes is easy for the GUI to display results in a pop up.
 * 
 * it gets returned in the below formate 
 *  - votes[0] = percentage for A
 *  - votes[1] = percentage for B
 *  - votes[2] = percentage for C
 *  - votes[3] = percentage for D
 * 
 * the correct answer gets  a higher vote remaining votes randomly distributed among the incorrect option
 */
public class AskTheAudience extends Lifeline {
    
    private final Random random = new Random();  // used to generate random values

    @Override
    public void use(Question question) {
        // to statisfy abstract class 
        
    }
    /*
    this method returns an array of audience vote percentage for each option
    */
    public int [] getAudienceVote(Question question)
    {
        if(used)
        {
            return null; 
        }
        used = true; 
        
        int [] votes = new int[4];  // array to store the 4 percentages
        
        int correctIndex = question.getCorrectAnswer().charAt(0) - 'A';  // get index for correct answer
        
        // assign 50% -70% to the correct answer to simulate higher vote
        votes[correctIndex] = 50 + random.nextInt(21); // 50 to 70 inclusive
        
        int remaining = 100 - votes[correctIndex];  //remaining votes to distribute
        
        int count = 0; // track how many incorrect options we have filled
        // loop through all options to assign leftover votes
        for(int i = 0; i < 4; i++)
        {
            if(i == correctIndex) continue;  // skip correct answer
            
            if(count == 2)
            {
                votes[i] = remaining;  // for the last incorrect option give it whatever votes are left
            }
            else
            {
                // randomly assign some of the remaining votes
                votes[i] = random.nextInt(remaining + 1); 
                remaining -= votes[i];  // decrease leftover votes
            }
            count++; 
        }
        
        return votes; 
    }
}
