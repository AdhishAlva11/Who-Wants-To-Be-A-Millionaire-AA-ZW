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

public class SwitchQuestion extends Lifeline {

    private final List<Question> switchQuestions;

    public SwitchQuestion(){
        switchQuestions = new ArrayList<>();
    }
    
    @Override
    public void use(Question question) // method to satisfy abstract class
    {
        
    }

    private void loadSwitchQuestions(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String questionText = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = reader.readLine();
                }
                String correctAnswer = reader.readLine();
                switchQuestions.add(new Question(questionText, options, correctAnswer));
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + filename + ": " + e.getMessage());
        }
    }
    
    public Question switchUsed(){
        if(used){
            return null;
        }
        loadSwitchQuestions("switch.txt");
        used = true;
        Collections.shuffle(switchQuestions);
        return switchQuestions.remove(0);
    }

}
