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
 */
public class AskTheAudience extends Lifeline {

    @Override
    public void use(Question question) {
        if (used) {
            System.out.println("Ask The Audience lifeline already used.");
            return;
        }

        used = true;

        Random rand = new Random();

        String correct = question.getCorrectAnswer();
        String[] options = question.getOptions();
        Map<String, Integer> voteMap = new LinkedHashMap<>();

        String correctOptionFull = "";
        for (String opt : options) {
            if (opt.charAt(0) == correct.charAt(0)) {
                correctOptionFull = opt;
                break;
            }
        }
        
        // Assign higher chance to the correct option
        int correctVotes = rand.nextInt(21) + 40; // 40-60 for correct answer
        int remainingVotes = 100 - correctVotes;

        // Fill remaining votes with random percentages
        List<String> wrongOptions = new ArrayList<>();
        for (String opt : options) {
            if (opt.charAt(0) != correct.charAt(0)) {
                wrongOptions.add(opt);
            }
        }

        int w1 = rand.nextInt(remainingVotes + 1);
        int w2 = rand.nextInt(remainingVotes - w1 + 1);
        int w3 = remainingVotes - w1 - w2;

        Collections.shuffle(wrongOptions);

        // Store the votes in a map
        voteMap.put(correctOptionFull, correctVotes);
        voteMap.put(wrongOptions.get(0), w1);
        voteMap.put(wrongOptions.get(1), w2);
        voteMap.put(wrongOptions.get(2), w3);

        System.out.println("Audience has voted:");
        for (Map.Entry<String, Integer> entry : voteMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + "%");
        }
    }
}
