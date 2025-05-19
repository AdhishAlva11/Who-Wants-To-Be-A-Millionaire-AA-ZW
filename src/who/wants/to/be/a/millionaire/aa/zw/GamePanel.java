/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
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

    private List<Question> questions; // shuffled question list
    private int currentQuestionIndex = 0; // what questions are we on
    private final int[] prizeLevel = {    // prize ladder values 
        100, 500, 1000, 5000, 10000, 25000, 50000,
        100000, 250000, 1000000
    };

    private Player player; // Tracks players score and name 

    public GamePanel(String playerName) // we send the name from main GUI here
    {
        this.player = new Player(playerName); // initilize player name 
        QuestionBank bank = new QuestionBank("easy.txt", "medium.txt", "hard.txt"); // load text files with questions 
        this.questions = bank.getMixedDifficultyQuestions();  // setting up mixed questions for gamme

        
        
        // ---GUI Window  set up----
        
        setTitle("Who Wants to Be a Millionaire");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centre on screen
        setLayout(new BorderLayout(15, 15)); // add spacing between major areas

        // --Question Panel to display questions at top---
        questionLabel = new JLabel("Question will go here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setPreferredSize(new Dimension(0, 80)); // this sets prefered height if questions label, and allows width to epxand automatically

        //-- Question panel to wrap question in padding
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // top, left, bottom, right padding
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH);

        
        
        // -- Answer options buttons ---- 
        /*
        / this create the panel to hold 4 answer buttons arranged in a 2x2 grid 
         */
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 15, 15)); //2x2 grid 
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //padding around the grid 
        optionsButtons = new JButton[4]; // array to hold 4 buttons 
        for (int i = 0; i < 4; i++) {
            
            optionsButtons[i] = new JButton((char) ('A' + i) + ": Option " + (i + 1));
            optionsButtons[i].setFont(new Font("Arail", Font.PLAIN, 16));
            optionsButtons[i].setForeground(Color.WHITE); // Text color white 
            optionsButtons[i].setBackground(new Color(0, 51, 102)); // using rbg colour model to set to dark blue 
            optionsButtons[i].setPreferredSize(new Dimension(180, 60)); // set consistent size
            optionsButtons[i].setFocusPainted(false); // clean lock
            optionsButtons[i].setBorderPainted(false);
            optionsButtons[i].setOpaque(true);
            
            //check if answer is correct
            final int index = i; 
            optionsButtons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    checkAnswer(index); 
                }
            });
            
            optionsPanel.add(optionsButtons[i]); // add gird to panel 

        }
        add(optionsPanel, BorderLayout.CENTER);

        // ---SideBar to display prize level (on right) ----- 
        /*
        display prize money in a ladder style
         */
        JPanel prizePanel = new JPanel(new GridLayout(10, 1, 5, 5)); // 10 rows for 10 prizes 
        prizeLabels = new JLabel[10];
        
        for (int i = 9; i >= 0; i--) { 
            prizeLabels[i] = new JLabel("$" + prizeLevel[i], SwingConstants.RIGHT);
            prizeLabels[i].setFont(new Font("Arial", Font.BOLD, 14));
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
        Question q = questions.get(currentQuestionIndex); // get current question 
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText()); // set question 

        String[] options = q.getOptions(); // get 4 answer options 
        for (int i = 0; i < 4; i++) {
            optionsButtons[i].setText(options[i]);
            optionsButtons[i].setBackground(new Color(0, 51, 102)); // Reset colour
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
            if (i == currentQuestionIndex) {
                prizeLabels[i].setForeground(Color.ORANGE); // current question prize level 
            } else {
                prizeLabels[i].setForeground(Color.BLACK); // other options 
            }
        }
        
    }
    /*
    This method checks if the selected answer is correct or wrong 
    it changes the colour button and updates the score 
    */
     private void checkAnswer(int selectedIndex) {
         
         Question current = questions.get(currentQuestionIndex); 
         String correctAnswer = current.getCorrectAnswer(); // get the correct answer  
         int correctIndex = correctAnswer.charAt(0) - 'A'; //comvert letter to numbver to compare with selected option 
         
        //disable all buttons after selection
         for(JButton btn : optionsButtons)
         {
             btn.setEnabled(false);
         }
         // check if its correct 
         if(selectedIndex == correctIndex)
         {
              optionsButtons[selectedIndex].setBackground(Color.GREEN); // if option chose is correct it goes green
              player.updateScore(prizeLevel[currentQuestionIndex]); // update player socre 
         }
         else 
         {
             optionsButtons[selectedIndex].setBackground(Color.RED);// colour for wrong option 
             optionsButtons[correctIndex].setBackground(Color.GREEN); // show correct colour in greeen
         }
         
         // simulate a delay for 1 second then move onto next question 
          Timer delay = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) 
            {
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) 
                {
                    displayQuestion();// load next question 
                } else {
                    showGameOver(); // end game; 
                }
            }
        });
        delay.setRepeats(false); 
        delay.start(); // start timeer
         
        
    }
     /*
     this method shows a pop up with the players final score and ends the game 
     */
     private void showGameOver()
     {
         JOptionPane.showMessageDialog(this, "Game Over! \nYou won: $" + player.getScore());
         dispose(); // close game window
     }

}
