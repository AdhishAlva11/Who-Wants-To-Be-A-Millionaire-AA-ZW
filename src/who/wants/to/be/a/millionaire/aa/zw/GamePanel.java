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
    private final int[] prizeLevel = { // prize ladder values 
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
            optionsButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
    ------Check Answer------- 
    This method checks if the selected answer is correct or wrong 
    it changes the colour button and updates the score 
     */
    private void checkAnswer(int selectedIndex) {

        Question current = questions.get(currentQuestionIndex);
        String correctAnswer = current.getCorrectAnswer(); // get the correct answer  
        int correctIndex = correctAnswer.charAt(0) - 'A'; //comvert letter to numbver to compare with selected option 

        //disable all buttons after selection
        for (JButton btn : optionsButtons) {
            btn.setEnabled(false);
        }
        // check if its correct 
        if (selectedIndex == correctIndex) {
            optionsButtons[selectedIndex].setBackground(Color.GREEN); // if option chose is correct it goes green
            player.updateScore(prizeLevel[currentQuestionIndex]); // update player socre 

            // simulate a delay for 1 second then move onto next question 
            Timer delay = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        displayQuestion();// load next question 
                    } else {
                        showGameOver(); // end game; 
                    }
                }
            });

            delay.setRepeats(false); // run only once 
            delay.start(); // start timer

        } else {
            optionsButtons[selectedIndex].setBackground(Color.RED);// colour for wrong option 
            optionsButtons[correctIndex].setBackground(Color.GREEN); // show correct colour in greeen

            showGameOver(); // end the game immediatley
        }

    }

    /*
    --------Game Over------ 
     This method shows a custom pop up when the game ends 
    it also updated high the high score file and allows players to open leader board 
     */
    private void showGameOver() {
        ManageHighScore manager = new ManageHighScore(); // handles score saving

        manager.updateScore(player.getName(), player.getScore()); // update score in highscore class connected to our database 

        // -----  custom game over panel  ----- 
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // stack elements vertically 
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // add paddig to all sides

        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center inside panel 
        panel.add(gameOverLabel); // add to top of panel 

        // --- Label to show player name and score 
        JLabel infoLabel = new JLabel("Player: " + player.getName() + " | Score: $" + player.getScore());
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10)); // vertical spacing
        panel.add(infoLabel); // add below title

        /// Button to open leaderboard
        JButton leaderBoardButton = new JButton("View Leaderboard");
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center it
        panel.add(Box.createVerticalStrut(15)); // spacing before button
        panel.add(leaderBoardButton);

        // Button to restart the game
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10)); // spacing before next button
        panel.add(playAgainButton);

        /*
        sets up pop up dialog window to show panel above 
        this creates a window over the gamepanel window  until closed 
         */
        // ----- dialog window setup ------
        JDialog dialog = new JDialog(this, "Game Over", true); // this refers to game panel. Game Over is dialog box name. true means modal meaning they must close before interacting with game window again
        dialog.getContentPane().add(panel); // put our custom panel inside the dialog
        dialog.setSize(300, 200);           // set the size of the dialog window
        dialog.setLocationRelativeTo(this); // center it on top of GamePanel window

        // ----  add logic to leader board button  ----- 
        leaderBoardButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // close game over window
                new LeaderBoardWindow(); // show leaderboard
            }
        });

        //--- add logic to  play again button --- 
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // close the pop up
                dispose();        // close the current GamePanel window
                new Main();       // reopen the welcome screen (Main class)
            }
        });

        dialog.setVisible(true); // display the dialog (wait until closed) 
        

    }

}
