/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Main
  -> Starts up the GamePanel_
 */

package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // DEFAULT SET UP
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dress the Penguin");
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // STARTING GAME
        gp.startGameThread();
        gp.setUpGame();
    }
}