/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This class creates the GUI game screen it shows questions, answer options and
 * a sidebar with prize levels
 *
 * @author Adhis
 */
public class GamePanel extends JFrame {

    private JLabel questionLabel;  // top question display 
    private JButton[] optionsButtons;  // Buttons A,B,C,D
    private JLabel[] prizeLabels; // sidebar to display the amount of money they are on
    private final int[] prizeLevel = UIConstantsGUI.PRIZE_LEVELS; // prize levels 
    private QuizController controller;  // handles game logic and player data 
    private JButton audienceButton, fiftyFiftyButton, switchQuestionButton; // button for lifelines

    private AskTheAudience audienceLifeline = new AskTheAudience();
    private FiftyFifty fiftyFiftyLifeline = new FiftyFifty();
    private SwitchQuestion switchLifeline = new SwitchQuestion();

    public GamePanel(String playerName) // we send the name from main GUI here
    {
        this.controller = new QuizController(playerName); // this sends name to controller

        // ---GUI Window  set up----
        setTitle("Who Wants to Be a Millionaire");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centre on screen
        setLayout(new BorderLayout(15, 15)); // add spacing between major areas

        // --Question Panel to display questions at top---
        questionLabel = new JLabel("Question will go here", SwingConstants.CENTER);
        questionLabel.setFont(UIConstantsGUI.QUESTION_FONT);
        questionLabel.setPreferredSize(new Dimension(0, 80)); // this sets prefered height of questions label, and allows width to epxand automatically

        //-- Question panel to wrap question in padding
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // top, left, bottom, right padding
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH);

        /*
        ----- Centrel panel to display options (A to D )
         */
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 15, 15)); //2x2 grid 
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //padding around the grid 
        optionsButtons = new JButton[4]; // array to hold 4 buttons 
        for (int i = 0; i < 4; i++) {

            optionsButtons[i] = new JButton((char) ('A' + i) + ": Option " + (i + 1));
            optionsButtons[i].setFont(UIConstantsGUI.BUTTON_FONT);
            optionsButtons[i].setForeground(Color.WHITE); // Text color white 
            optionsButtons[i].setBackground(UIConstantsGUI.BUTTON_COLOUR); // using rbg colour model to set to dark blue 
            optionsButtons[i].setPreferredSize(new Dimension(180, 60)); // set consistent size
            optionsButtons[i].setFocusPainted(false); // clean lock
            optionsButtons[i].setBorderPainted(false);
            optionsButtons[i].setOpaque(true);

            //check if answer is correct
            final int index = i; // need final for inner class 

            optionsButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(index); // pass which button was clicked 
                }
            });

            optionsPanel.add(optionsButtons[i]); // add gird to panel 

        }
        add(optionsPanel, BorderLayout.CENTER);

        // ---Right Panel -  to display prize level----- 
        JPanel prizePanel = new JPanel(new GridLayout(10, 1, 5, 5)); // 10 rows for 10 prizes 
        prizeLabels = new JLabel[10];

        for (int i = 9; i >= 0; i--) {
            prizeLabels[i] = new JLabel("$" + prizeLevel[i], SwingConstants.RIGHT);
            prizeLabels[i].setFont(UIConstantsGUI.PRIZE_FONT);
            prizePanel.add(prizeLabels[i]);
        }
        //---wrap prize panel in padding --- 
        JPanel prizeContainer = new JPanel(new BorderLayout());
        prizeContainer.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 20));
        prizeContainer.add(prizePanel, BorderLayout.CENTER);
        add(prizeContainer, BorderLayout.EAST);

        displayQuestion(); // show first question 
        setVisible(true); // display full window 

        /*
         -------Life line panel -------
        
         */
        JPanel lifelinePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        audienceButton = new JButton("Ask the Audience");
        fiftyFiftyButton = new JButton("50:50");
        switchQuestionButton = new JButton("Switch Question");

        // make them consistent
        audienceButton.setFocusable(false);
        fiftyFiftyButton.setFocusable(false);
        switchQuestionButton.setFocusable(false);

        audienceButton.setPreferredSize(new Dimension(150, 40));
        fiftyFiftyButton.setPreferredSize(new Dimension(150, 40));
        switchQuestionButton.setPreferredSize(new Dimension(150, 40));

        // add to panel
        lifelinePanel.add(audienceButton);
        lifelinePanel.add(fiftyFiftyButton);
        lifelinePanel.add(switchQuestionButton);

        // add panel to layout
        add(lifelinePanel, BorderLayout.SOUTH);

        // ---- actions listner for life line buttons-----
        audienceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get vote distribution from AskTheAudience lifeline
                int[] votes = audienceLifeline.getAudienceVote(controller.getCurrentQuestion());

                // Check if the lifeline was already used
                if (votes == null) {
                    JOptionPane.showMessageDialog(GamePanel.this, "Ask the Audience already used!");
                    return;
                }

                // build a message string to show the vote percentages
                StringBuilder resultMessage = new StringBuilder("Audience vote results:\n\n");
                String[] labels = {"A", "B", "C", "D"};
                String[] options = controller.getCurrentQuestion().getOptions();

                for (int i = 0; i < votes.length; i++) {
                    resultMessage.append(labels[i])
                            .append(") ")
                            .append(options[i].substring(3)) // Skip "A) ", "B) ", etc.
                            .append(" - ")
                            .append(votes[i])
                            .append("%\n");
                }

                // Show the vote distribution in a dialog box
                JOptionPane.showMessageDialog(GamePanel.this, resultMessage.toString());

                // Disable the lifeline button after use
                audienceButton.setEnabled(false);
            }
        });

        // --- 50:50 action listner button
        fiftyFiftyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // call lifeline logic to get two indicies we want to keep
                // one correct option and one random incorrect option
                int[] indicesToKeep = fiftyFiftyLifeline.useFiftyFifty(controller.getCurrentQuestion());
                // check if life lines hasnt already been used

                if (indicesToKeep != null) {
                    for (int i = 0; i < optionsButtons.length; i++) // loop  through all options buttons (A,B,C,D)
                    {
                        /*
                        if correct index is not one of the two we want to keep
                        then disable button and visually dim it
                         */
                        if (i != indicesToKeep[0] && i != indicesToKeep[1]) {
                            optionsButtons[i].setEnabled(false); // disable the other two
                            optionsButtons[i].setBackground(Color.DARK_GRAY); // visually dim
                        }
                    }
                    fiftyFiftyButton.setEnabled(false); // disable the button after use
                } else // if life line was already used, show a warning pop up 
                {
                    JOptionPane.showMessageDialog(GamePanel.this, "50:50 already used!");
                }
            }
        });

        // --- switch question action listner 
        /*
        works with our current switch question class to load in new question from file read into 
        the switch question class. allows quix to flow naturally
         */
        switchQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Question newQ = switchLifeline.switchUsed();
                if (newQ != null) // check if we got a new question (check if life line was used before) 
                {
                    controller.replaceCurrentQuestion(newQ); // replace current question with new one(same position in list) 
                    displayQuestion(); // refresh screen to display new loaded question
                    switchQuestionButton.setEnabled(false); // disable after use
                } else // if used show warning pop up
                {
                    JOptionPane.showMessageDialog(GamePanel.this, "Switch Question already used!");
                }
            }
        });

    }

    /*
    This  method shows the current question and options on the screen
    it resets button states and highlights the current prize level
     */
    private void displayQuestion() {
        Question q = controller.getCurrentQuestion(); // get current question 
        questionLabel.setText("Q" + (controller.getCurrentIndex() + 1) + ": " + q.getQuestionText()); // set question 

        String[] options = q.getOptions(); // get 4 answer options 
        for (int i = 0; i < 4; i++) {
            optionsButtons[i].setText(options[i]);
            optionsButtons[i].setBackground(UIConstantsGUI.BUTTON_COLOUR); // Reset colour
            optionsButtons[i].setEnabled(true); // enable for new click 
        }
        highlightCurrentPrize(); // update ladder 
    }

    /*
    This method highlights the current question level on the prize ladder
    Orange is used to indicate the active question 
     */
    private void highlightCurrentPrize() {
        for (int i = 0; i < prizeLabels.length; i++) {
            if (i == controller.getCurrentIndex()) {
                prizeLabels[i].setForeground(Color.ORANGE); // current question prize level 
            } else {
                prizeLabels[i].setForeground(Color.BLACK); // other options 
            }
        }

    }

    /*
    ------Check Answer------- 
    This method checks if the selected answer is correct or wrong 
    it changes the colour button and updates the score 
     */
    private void checkAnswer(int selectedIndex) {

        int correctIndex = controller.getCorrectAnswerIndex();
        boolean isCorrect = controller.isAnswerCorrect(selectedIndex); // store correct answer

        //disable all buttons after selection
        for (JButton btn : optionsButtons) {
            btn.setEnabled(false);
        }

        // Colour feedback
        if (isCorrect) {
            optionsButtons[selectedIndex].setBackground(Color.GREEN);
            controller.updateScore(); // update player score
        } else {
            optionsButtons[selectedIndex].setBackground(Color.RED);
            optionsButtons[correctIndex].setBackground(Color.GREEN); // show correct answer
        }

        // Delay before next question or game over
        Timer delay = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (isCorrect && controller.hasNextQuestion()) {
                    controller.moveToNextQuestion();
                    displayQuestion();
                } else {
                    showGameOver(); // end game
                }
            }
        });

        delay.setRepeats(false); // run only once
        delay.start();

    }

    /*
    --------Game Over------ 
     This method shows a custom pop up when the game ends 
     it also updated high the high score file and allows players to open leader board 
     */
    private void showGameOver() {

        ManageHighScore manager = new ManageHighScore();

        manager.updateScore(controller.getPlayer().getName(), controller.getPlayer().getScore());

        new GameOverDialog(this, controller.getPlayer().getName(), controller.getPlayer().getScore());

    }

}
