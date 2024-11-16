import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 600;
    static final int MOVEMENT_SPEED = 3;
    static final int PADDLE_HEIGHT = 75;
    static final int PADDLE_WIDTH = 5;
    static final int FPS = 1000/60;
    private static Player p1;
    private static Player p2;
    boolean running = false;
    Timer timer;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(FPS, this);
        timer.start();
        p1 = new Player(0, (SCREEN_HEIGHT - PADDLE_HEIGHT) / 2,
                MOVEMENT_SPEED);
        p2 = new Player(SCREEN_WIDTH - PADDLE_WIDTH,
                (SCREEN_HEIGHT - PADDLE_HEIGHT) / 2, MOVEMENT_SPEED);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.white);
            g.fillRect(p1.getxPosition(), p1.getyPosition(),
                    PADDLE_WIDTH, PADDLE_HEIGHT);
            g.fillRect(p2.getxPosition(), p2.getyPosition(),
                    PADDLE_WIDTH, PADDLE_HEIGHT);
        }
    }

    public static void move() {
        if (p1.direction == 'U' && p1.getyPosition() > 0) {
            p1.movePaddleUp();
        } else if (p1.direction == 'D' && p1.getyPosition() < SCREEN_HEIGHT - PADDLE_HEIGHT) {
            p1.movePaddleDown();
        }

        if (p2.direction == 'U' && p2.getyPosition() > 0) {
            p2.movePaddleUp();
        } else if (p2.direction == 'D' && p2.getyPosition() < SCREEN_HEIGHT - PADDLE_HEIGHT) {
            p2.movePaddleDown();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // checking for p1 input
            if (e.getKeyCode() == KeyEvent.VK_W) {
                p1.setDirection('U');
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                p1.setDirection('D');
            }

            // checking for p2 input
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                p2.setDirection('D');
            }  else if (e.getKeyCode() == KeyEvent.VK_UP) {
                p2.setDirection('U');
            }
        }

        // stopping player movement
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_S
                    || e.getKeyCode() == KeyEvent.VK_W) {
                p1.setDirection('N');
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN
            || e.getKeyCode() == KeyEvent.VK_UP) {
                p2.setDirection('N');
            }
        }
    }
}
