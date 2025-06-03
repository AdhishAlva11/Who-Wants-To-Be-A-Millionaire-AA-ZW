/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.awt.*;

/**
 *stores all fonts, colours and prize values in one place 
 * @author Adhis
 */
public class UIConstantsGUI {

    public static final Color BUTTON_COLOUR = new Color(0, 51, 102);  // Dark blue for answer buttons
    public static final Font QUESTION_FONT = new Font("Arial", Font.BOLD, 20); // question font
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 16); // button font 
    public static final Font PRIZE_FONT = new Font("Arial", Font.BOLD, 14); // prize font 

    public static final int[] PRIZE_LEVELS =  // prize levels/ ladder 
    {
        100, 500, 1000, 5000, 10000,
        25000, 50000, 100000, 250000, 1000000
    };

}
