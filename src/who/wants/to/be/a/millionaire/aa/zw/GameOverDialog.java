/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 *
 * This class displays a customs game over dialog after the player finishes the
 * quiz it shows the player name and final score and gives two options - view
 * leader board - play again
 *
 * @author Adhis
 */
public class GameOverDialog extends JDialog {

    public GameOverDialog(JFrame parent, String playerName, int score) {

        super(parent, "Game Over", true); // calls super class constructor 
        /*
        makes this a modal dialog titles "Game Over" modal = user must 
        close window before interacting with the parent window (GamePanel is parent)
         */

        // ----- Main panel for layout  ---- 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // stack elements vertically 
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // add paddig to all sides

        // Title label - game over 
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center inside panel 
        panel.add(gameOverLabel); // add to top of panel 

        // --- Label to show player name and score 
        JLabel infoLabel = new JLabel("Player: " + playerName + " | Score: $" + score);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10)); // vertical spacing
        panel.add(infoLabel); // add below title

        /// Button to open leaderboard
        JButton leaderBoardButton = new JButton("View Leaderboard");
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center it
        leaderBoardButton.setFocusable(false); // remove dotted focus line 

        // Button to restart the game
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setFocusable(false);

        panel.add(Box.createVerticalStrut(15)); // spacing before button
        panel.add(leaderBoardButton);
        panel.add(Box.createVerticalStrut(10)); // spacing before next button
        panel.add(playAgainButton);

        // ---  Add panel to dialog --- 
        getContentPane().add(panel);

        //---- set dialog properties 
        setSize(300, 200); // fixed size 
        setLocationRelativeTo(parent); // center over GamePanel (parent window)

        // ----  add logic to leader board button  ----- 
        leaderBoardButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                dispose(); // close this game over dialog 
                parent.dispose(); // close GamePanel window 
                new LeaderBoardWindow(); //  open leaderboard window
            }
        });

        //--- add logic to  play again button --- 
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();        // close the game over dialog
                parent.dispose(); // close the gamepanel window 
                new Main();       // restart game from main  (Welcome screen)
            }
        });

        setVisible(true); // display the dialog (wait until closed) 

    }

}
