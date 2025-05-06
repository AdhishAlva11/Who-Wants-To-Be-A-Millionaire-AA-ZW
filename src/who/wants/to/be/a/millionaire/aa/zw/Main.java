/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import java.util.Scanner;

/**
 *
 * @author Adhis
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String easyFile = "easy.txt";
        String mediumFile = "medium.txt";
        String hardFile = "hard.txt";

        Game game = new Game(name, easyFile, mediumFile, hardFile);
        game.play();
    }
}

