/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: GamePanel
  -> Essentially the main class of the whole game
  -> Handles the game loop and calls upon everyone's draw and update methods
 */

package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    int originalTileSize = 16;
    int scale = 3;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    int FPS = 60;

    // WORLD SETTINGS: number of rows and columns in the world map
    public final int maxWorldCol = 70;
    public final int maxWorldRow = 70;

    /* OTHER CLASSES AND STUFF
    ----------------------------------------------------------------------
     */
    // KEY HANDLER
    KeyHandler kh = new KeyHandler();
    // THREAD - keep track of time in game
    Thread gameThread;
    // TILE MANAGER
    TileManager tm = new TileManager(this);


    // ENTITIES
    public Player player = new Player(this, kh);

    public GamePanel() {
        // DEFAULT SETTINGS
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // improve rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread() {
        // starts a thread for this gamePanel = starts the clock
        // automatically calls run()
        gameThread = new Thread(this);
        gameThread.start();
    }

    // THE GAME LOOP
    public void run() {
        // getting 60 FPS
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // 1. UPDATE: update character info such as position
            update();
            // 2. DRAW: draw the screen with updated character information
            repaint();
            // 3. FPS: slow down the thread to achieve 60 FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                // thread is awake! sleep time is over
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // UPDATE PLAYER INFO
    public void update() {
        player.update();
    }

    // DRAW EVERYTHING ONTO SCREEN
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D: provides more control over geometry,
        // coordinate transformation, color management, and text layout
        Graphics2D g2 = (Graphics2D) g;
        tm.draw(g2);
        player.draw(g2);
    }
}
