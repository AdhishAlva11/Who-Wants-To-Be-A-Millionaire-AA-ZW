/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

/**
 *
 * @author Adhis
 */
import java.util.*;

public class FiftyFifty extends Lifeline {

    @Override
    public void use(Question question) {
        if (used) {
            System.out.println("Fifty-Fifty lifeline already used.");
            return;
        }

        used = true;

        String[] options = question.getOptions();
        String correctAnswerLetter = question.getCorrectAnswer();
        int correctIndex = correctAnswerLetter.charAt(0) - 'A'; // Convert A/B/C/D to 0/1/2/3

        // Collect indices of incorrect options
        List<Integer> incorrectIndices = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            if (i != correctIndex) {
                incorrectIndices.add(i);
            }
        }

        // Pick one random incorrect answer to keep
        Collections.shuffle(incorrectIndices);
        int otherIndex = incorrectIndices.get(0);

        System.out.println("Remaining options:");
        System.out.println(options[correctIndex]);
        System.out.println(options[otherIndex]);
    }
}
