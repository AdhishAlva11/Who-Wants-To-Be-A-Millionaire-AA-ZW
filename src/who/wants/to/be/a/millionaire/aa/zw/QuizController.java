/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.util.List;

/**
 * This class controls the quiz logic(game flow) it manages questions, checks
 * answers, tracks scores and player progress seperates logic from GUI
 *
 * @author Adhis
 */
public class QuizController {

    private List<Question> questions; // list of all questions 
    private int currentQuestionIndex; //tracks question number 
    private Player player; // tracks player name and score 

    public QuizController(String playerName) {

        this.player = new Player(playerName);  // send name to player class 
        QuestionBank bank = new QuestionBank("easy.txt", "medium.txt", "hard.txt");  // load in questions files
        this.questions = bank.getMixedDifficultyQuestions();
        this.currentQuestionIndex = 0;
    }

    // returns the current question the user is on. 
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    // checks if selected answer index is correct 
    //we compare the index (0-3) to the correct answer ("A","B","C","D") 
    public boolean isAnswerCorrect(int selectedIndex) {
        String correctAnswer = getCurrentQuestion().getCorrectAnswer();
        int correctIndex = correctAnswer.charAt(0) - 'A'; // convert 'A' to 0 etc
        return selectedIndex == correctIndex;

    }

    /*
    returns the index of the correct answer (used to highilight correct button) 
     */

    public int getCorrectAnswerIndex() {
        String correctAnswer = getCurrentQuestion().getCorrectAnswer();
        return correctAnswer.charAt(0) - 'A';
    }

    /*
    update player score using the prize level for the current question 
    prize loader is stored in UIConstantsGUI; 
     */
    public void updateScore() {
        player.updateScore(UIConstantsGUI.PRIZE_LEVELS[currentQuestionIndex]);
    }

    /*
    check if there are more question left
     */
    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    /*
    move to next question by incresing index
     */
    public void moveToNextQuestion() {
        currentQuestionIndex++;
    }

    public Player getPlayer() {
        return player;
    }

    /*
    returns the current question index (used for prize ladder highlighting) 
     */
    public int getCurrentIndex() {
        return currentQuestionIndex;
    }
    
    /*
    the below method updates current question in place without affecting the rest of the quizz
    used by game panel gui
    */

    public void replaceCurrentQuestion(Question newQuestion) 
    {
        if (currentQuestionIndex < questions.size()) {
            questions.set(currentQuestionIndex, newQuestion);
        }
    }
}
