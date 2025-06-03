/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Adhis
 * 
 * Previously this class printed two remaining option to the console
 * it has now been updated to work with the GUI
 * it returns two indices (int []) of the two options to keep 
 *  - one correct 
 *  - one random incorrect 
 * 
 * the GUI uses this to disable the other two buttons and dim them visually. 
 */
public class FiftyFifty extends Lifeline {
    
    public void use(Question question)
    {
        // not used in GUI version. we use the below method instead. 
        // old logic deleted to match GUI requirement
        // satisfies abstract class requirement 
    }

    /*
    This method returns the indicies of the two options to keep visible:
    -  the correct answer
    - one random chosen incorrect answer
    */
    public int [] useFiftyFifty(Question question)
    {
        
        if(used)
        {
            return null;  // life already used 
        }
        used  = true;  // mark this lifeline as used 
        
        String [] options = question.getOptions(); // get answer opions 
        String correctLetter = question.getCorrectAnswer();  // correct answer
        int correctIndex = correctLetter.charAt(0) - 'A'; // convert to 0 -3
        
        List <Integer> wrongIndices = new ArrayList<>(); 
        
        // gather all incorrect indicies 
        
        for(int i = 0; i < options.length; i++)
        {
            if(i != correctIndex)
            {
                wrongIndices.add(i); 
            }
        }
        
        Collections.shuffle(wrongIndices); // randomize the wrong answer
        int randomWrong = wrongIndices.get(0); // keep one wrong answer 
        
        return new int [] {correctIndex, randomWrong}; 
    }
       
}