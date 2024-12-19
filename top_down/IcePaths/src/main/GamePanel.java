package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    int originalTileSize = 16;
    int scale = 3;
    int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    int FPS = 60;

    // KEY HANDLER
    KeyHandler kh = new KeyHandler();

    // THREAD - keep track of time in game
    Thread gameThread;

    /* FOR TESTING PURPOSES ONLY
    MAKE SURE TO DELETE WHEN CONTINUING
     */
    int x = 100;
    int y = 100;

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
        if (kh.upPressed) {
            y--;
        } else if (kh.leftPressed) {
            x--;
        } else if (kh.downPressed) {
            y++;
        } else if (kh.rightPressed) {
            x++;
        }
    }

    // DRAW EVERYTHING ONTO SCREEN
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D: provides more control over geometry,
        // coordinate transformation, color management, and text layout
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);

        g2.drawRect(x, y, tileSize, tileSize);
    }
}
