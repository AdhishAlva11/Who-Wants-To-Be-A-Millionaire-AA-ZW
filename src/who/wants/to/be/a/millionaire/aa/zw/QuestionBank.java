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

public class QuestionBank {
    // lists to save easy, medium and hard questions
    private final List<Question> easyQuestions;
    private final List<Question> mediumQuestions;
    private final List<Question> hardQuestions;

    public QuestionBank(String easyFile, String mediumFile, String hardFile)
    {
        easyQuestions = new ArrayList<>();
        mediumQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();

        loadQuestionsFromFile(easyFile, easyQuestions); // file and corresponding list sent to loadquestionfromfile method
        loadQuestionsFromFile(mediumFile, mediumQuestions);
        loadQuestionsFromFile(hardFile, hardQuestions);
    }

    private void loadQuestionsFromFile(String filename, List<Question> questionList) //reads in each file and saves it to easy, medium and hard lists
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String questionText = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = reader.readLine();
                }
                String correctAnswer = reader.readLine();
                questionList.add(new Question(questionText, options, correctAnswer));
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + filename + ": " + e.getMessage());
        }
    }

    public List<Question> getMixedDifficultyQuestions() // shufffle lists and add 3 from easy, 4 from medium and 3 from hard to mixedQuestion list. 
    {
        List<Question> mixed = new ArrayList<>();

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);

        mixed.addAll(easyQuestions.subList(0, Math.min(3, easyQuestions.size())));
        mixed.addAll(mediumQuestions.subList(0, Math.min(4, mediumQuestions.size())));
        mixed.addAll(hardQuestions.subList(0, Math.min(3, hardQuestions.size())));

        return mixed;
    }
}


