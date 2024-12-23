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
import object.Object;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
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
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    public TileManager tm = new TileManager(this);
    public CollisionChecker cc = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public NextTile nextTile = new NextTile(this, kh);
    public UI ui = new UI(this);


    // ENTITIES
    public Player player = new Player(this, kh);

    // OBJECTS
    public Object obj[] = new Object[19];

    public GamePanel() {
        // DEFAULT SETTINGS
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // improve rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    // CALLED IN MAIN
    public void setUpGame() {
        aSetter.setObjects();
    }

    // CALLED IN MAIN
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
        drawObjects(g2);
        player.draw(g2);
        nextTile.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }

    private void drawObjects(Graphics2D g2) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
    }

}
