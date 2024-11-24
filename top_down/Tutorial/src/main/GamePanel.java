package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;  // scale for originalTileSize
    // actual tile size
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int WorldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);

    // clock and key handler
    KeyHandler kh = new KeyHandler();

    // thread used to keep track of time in the game
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, kh);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        // improves game's rendering performance
        this.setDoubleBuffered(true);

        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread() {
        // starts a thread for this gamePanel --> starts the clock
        // automatically calls the run method
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // create a game loop in the run method

        // slow down program to 60 fps
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // 1 UPDATE: update info such as character position
            update();

            // 2 DRAW: draw the screen with updated information
            repaint();

            try {
                // make the thread sleep
                // have it wait until it is time to draw again!
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

            // there is also a delta method that is shown on video #2
            // of the YouTube tutorial
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D
        // provides more control over geometry, coordinate transformations,
        // color management, and text layout
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);


        // good practice to save some memory
        g2.dispose();
    }

}
