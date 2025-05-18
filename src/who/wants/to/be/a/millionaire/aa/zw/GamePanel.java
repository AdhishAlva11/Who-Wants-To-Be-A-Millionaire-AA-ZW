/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    private final int[] prizeLevel = {
        100, 500, 1000, 5000, 10000, 25000, 50000,
        100000, 250000, 1000000
    };

    private Player player; // Tracks players score

    public GamePanel(String playerName) // we send the name from main GUI here
    {
        this.player = new Player(playerName);
        QuestionBank bank = new QuestionBank("easy.txt", "medium.txt", "hard.txt"); // load text files with questions 
        this.questions = bank.getMixedDifficultyQuestions();  // setting up mixed questions for gamme

        // ---Frame set up----
        setTitle("Who Wants to Be a Millionaire");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centre on screen
        setLayout(new BorderLayout(15, 15)); // add spacing between major areas

        // --Question Label to display questions at top---
        questionLabel = new JLabel("Question will go here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setPreferredSize(new Dimension(0, 80)); // this sets prefered height if questions label, and allows width to epxand automatically

        //-- Question panel to wrap question in padding
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // top, left, bottom, right
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH);

        // -- Answer options buttons ---- 
        /*
        / this create the panel to hold 4 answer buttons arranged in a 2x2 grid 
         */
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //padding around the grid 
        optionsButtons = new JButton[4]; // array to hold 4 buttons 
        for (int i = 0; i < 4; i++) {
            
            optionsButtons[i] = new JButton((char) ('A' + i) + ": Option " + (i + 1));
            optionsButtons[i].setFont(new Font("Arail", Font.PLAIN, 16));
            optionsButtons[i].setForeground(Color.WHITE); // Text color
            optionsButtons[i].setBackground(new Color(0, 51, 102)); // using rbg colour model to set to light blue
            optionsButtons[i].setPreferredSize(new Dimension(180, 60)); // set consistent size
            optionsButtons[i].setFocusPainted(false); // clean lock
            optionsButtons[i].setBorderPainted(false);
            optionsButtons[i].setOpaque(true);
            
            //check if option correct
            final int index = i; 
            optionsButtons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    checkAnswer(index); 
                }
            });
            
            optionsPanel.add(optionsButtons[i]);

        }
        add(optionsPanel, BorderLayout.CENTER);

        // ---SideBar to display prize level (on right) ----- 
        /*
        display prize money in a ladder style
         */
        JPanel prizePanel = new JPanel(new GridLayout(10, 1, 5, 5));
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

        displayQuestion();
        setVisible(true);

    }

    private void displayQuestion() {
        Question q = questions.get(currentQuestionIndex);
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText());

        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionsButtons[i].setText(options[i]);
        }
        highlightCurrentPrize();
    }

    private void highlightCurrentPrize() {
        for (int i = 0; i < prizeLabels.length; i++) {
            if (i == currentQuestionIndex) {
                prizeLabels[i].setForeground(Color.ORANGE);
            } else {
                prizeLabels[i].setForeground(Color.BLACK);
            }
        }
        
    }
    //to do tomorrow
     private void checkAnswer(int selectedIndex) {
        // Logic to be added next
        System.out.println("Option " + (char) ('A' + selectedIndex) + " clicked.");
    }

}
