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

    public GamePanel(String playerName) // we send the name from main GUI here
    {
        this.controller = new QuizController(playerName);

        // ---GUI Window  set up----
        setTitle("Who Wants to Be a Millionaire");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centre on screen
        setLayout(new BorderLayout(15, 15)); // add spacing between major areas

        // --Question Panel to display questions at top---
        questionLabel = new JLabel("Question will go here", SwingConstants.CENTER);
        questionLabel.setFont(UIConstantsGUI.QUESTION_FONT);
        questionLabel.setPreferredSize(new Dimension(0, 80)); // this sets prefered height if questions label, and allows width to epxand automatically

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
            final int index = i; // need final for innder class 

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
