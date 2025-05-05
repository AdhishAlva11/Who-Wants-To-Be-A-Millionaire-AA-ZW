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

public class Game {

    private final Player player;
    private final QuestionBank questionBank;
    private final ManageHighScore scoreManager;
    private final List<Question> gameQuestions;
    private final int[] prizeLevels = {
        100, 500, 1000, 5000, 10000, 25000, 50000, 100000, 250000, 1000000
    };

    private final Lifeline fiftyFifty;
    private final Lifeline askAudience;
    private final SwitchQuestion switchQuestion;

    public Game(String playerName, String easyFile, String mediumFile, String hardFile) {
        this.player = new Player(playerName);
        this.questionBank = new QuestionBank(easyFile, mediumFile, hardFile);
        this.scoreManager = new ManageHighScore();
        this.fiftyFifty = new FiftyFifty();
        this.askAudience = new AskTheAudience();
        this.gameQuestions = questionBank.getMixedDifficultyQuestions();
        this.switchQuestion = new SwitchQuestion();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String FFLifeline = " 1) Fifty-Fifty";
            String AtALifeline = " 2) Ask The Audience";
            String SQLifeline = " 3) Switch Question";
            Question q = gameQuestions.get(i);

            System.out.println("Question " + (i + 1) + ": " + q.getQuestionText());
            for (String opt : q.getOptions()) {
                System.out.println(opt);
            }

            String input = "";
            boolean validInput = false;

            // Main input loop for answer/lifeline/quit
            while (!validInput) {
                System.out.println("Enter your answer (A, B, C, D), 'L' for lifeline, or 'X' to quit:");
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D")
                        || input.equals("L") || input.equals("X")) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter A, B, C, D, L, or X.");
                }
            }

            if (input.equals("X")) {
                // Quit game and show score
                scoreManager.updateScore(player.getName(), player.getScore());
                scoreManager.saveScores();
                System.out.println("Game over. You won $" + player.getScore());
                scoreManager.printScores();
                return;
            }

            if (input.equals("L")) {
                boolean lifeLineUsed = false;

                while (!lifeLineUsed) {
                    if (fiftyFifty.isUsed()) {
                        FFLifeline = "";
                    }
                    if (askAudience.isUsed()) {
                        AtALifeline = "";
                    }
                    if (switchQuestion.isUsed()) {
                        SQLifeline = "";
                    }

                    if (fiftyFifty.isUsed() && askAudience.isUsed() && switchQuestion.isUsed()) {
                        //  When all lifelines are used, allow user to quit or answer
                        System.out.println("All Lifelines are used!");
                        System.out.println("Now enter your answer (A, B, C, or D), or 'X' to quit:");

                        while (true) {
                            input = scanner.nextLine().trim().toUpperCase();

                            if (input.equals("X")) {
                                // Quit game and show score
                                scoreManager.updateScore(player.getName(), player.getScore());
                                scoreManager.saveScores();
                                System.out.println("Game over. You won $" + player.getScore());
                                scoreManager.printScores();
                                return;
                            } else if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D")) {
                                break; // Continue with answer
                            } else {
                                System.out.println("Invalid input! Please enter A, B, C, D, or X.");
                            }
                        }

                        break;
                    } else {
                        System.out.println("Choose a lifeline:" + FFLifeline + AtALifeline + SQLifeline);
                        String choice = scanner.nextLine().trim();

                        try {
                            int lifeLine = Integer.parseInt(choice);

                            switch (lifeLine) {
                                case 1:
                                    if (!fiftyFifty.isUsed()) {
                                        fiftyFifty.use(q);
                                        lifeLineUsed = true;
                                    } else {
                                        System.out.println("You've already used Fifty-Fifty.");
                                    }
                                    break;
                                case 2:
                                    if (!askAudience.isUsed()) {
                                        askAudience.use(q);
                                        lifeLineUsed = true;
                                    } else {
                                        System.out.println("You've already used Ask The Audience.");
                                    }
                                    break;
                                case 3:
                                    if (!switchQuestion.isUsed()) {
                                        Question newQ = switchQuestion.switchUsed();

                                        if (newQ != null) {
                                            gameQuestions.set(i, newQ);
                                            q = newQ;
                                            System.out.println("Switched! Here's your new question:");
                                            System.out.println(q.getQuestionText());
                                            for (String opt : newQ.getOptions()) {
                                                System.out.println(opt);
                                            }
                                        }
                                        lifeLineUsed = true;
                                    } else {
                                        System.out.println("You've already used Switch Question.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid! Please choose 1, 2 or 3.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter 1, 2 or 3.");
                        }
                    }
                }

                // Ask again for valid answer after lifeline
                boolean validAnswer = false;
                while (!validAnswer) {
                    System.out.println("Now enter your answer (A, B, C, or D), or 'X' to quit:");
                    input = scanner.nextLine().trim().toUpperCase();
                    if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D")) {
                        validAnswer = true;
                    } else if (input.equals("X")) {
                        // Quit game and show score
                        scoreManager.updateScore(player.getName(), player.getScore());
                        scoreManager.saveScores();
                        System.out.println("Game over. You won $" + player.getScore());
                        scoreManager.printScores();
                        return;
                    } else {
                        System.out.println("Invalid answer! Please enter A, B, C, D, or X.");
                    }
                }
            }

            if (input.equals("X")) {
                // Quit game and show score
                scoreManager.updateScore(player.getName(), player.getScore());
                scoreManager.saveScores();
                System.out.println("Game over. You won $" + player.getScore());
                scoreManager.printScores();
                return;
            }

            if (input.equalsIgnoreCase(q.getCorrectAnswer())) {
                player.updateScore(prizeLevels[i]);
                System.out.println("Correct! You've won $" + prizeLevels[i]);
            } else {
                System.out.println("Wrong answer. The correct answer was: " + q.getCorrectAnswer());
                break;
            }

            System.out.println("-------------------------------\n");
        }

        // End of game - score display
        scoreManager.updateScore(player.getName(), player.getScore());
        scoreManager.saveScores();
        System.out.println("Game over. You won $" + player.getScore());
        scoreManager.printScores();
    }
}
