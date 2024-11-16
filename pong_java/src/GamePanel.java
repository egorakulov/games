import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int MOVEMENT_SPEED = 5;
    static final int PADDLE_HEIGHT = 75;
    static final int PADDLE_WIDTH = 10;
    static final int FPS = 1000/60;
    private static Player p1;
    private static Player p2;
    private static Ball b;
    static final int BALL_SIZE = 25;
    static final int BALL_SPEED = 8;
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
        b = new Ball((SCREEN_WIDTH - BALL_SIZE) / 2, (SCREEN_HEIGHT - BALL_SIZE) / 2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // drawing players
            g.setColor(Color.white);
            g.fillRect(p1.getxPosition(), p1.getyPosition(),
                    PADDLE_WIDTH, PADDLE_HEIGHT);
            g.fillRect(p2.getxPosition(), p2.getyPosition(),
                    PADDLE_WIDTH, PADDLE_HEIGHT);

            // drawing middle line
            g.drawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);

            // drawing score board
            g.setFont(new Font("Helvetica", Font.PLAIN, 65));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString(p1.getScore() + "     " + p2.getScore(),
                    (SCREEN_WIDTH - metrics.stringWidth(p1.getScore() + "     " + p2.getScore())) / 2,
                    g.getFont().getSize());

            // drawing ball
            g.setColor(Color.red);
            g.fillOval(b.x, b.y, BALL_SIZE, BALL_SIZE);
        } else {
            gameOver(g);
        }
    }

    public static void move() {
        // moving players
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

        // moving ball
        moveBall();
    }

    public static void moveBall() {
        // off wall
        if (b.y <= 0) {
            b.yDir = 'D';
        } else if (b.y >= SCREEN_HEIGHT - BALL_SIZE) {
            b.yDir = 'U';
        }

        // checking L->R movement
        if (b.xDir == 'L') {
            b.x -= BALL_SPEED;
        } else if (b.xDir == 'R') {
            b.x += BALL_SPEED;
        }

        // checking U->D movement
        if (b.yDir == 'U') {
            b.y -= BALL_SPEED;
        } else if (b.yDir == 'D') {
            b.y += BALL_SPEED;
        }
    }

    public void checkGoal() throws InterruptedException {
        // bounce off left side
        if (b.x < PADDLE_WIDTH && b.x > 0
                && p1.getyPosition() < b.y + (BALL_SIZE / 2)
                && b.y + (BALL_SIZE / 2) < p1.getyPosition() + PADDLE_HEIGHT) {
            b.xDir = 'R';
        } else if ( b.x < 0) {
            Thread.sleep(500);
            p2.addScore();
            resetBall('L');
        }

        // bounce off right side
        if (b.x > SCREEN_WIDTH - BALL_SIZE - PADDLE_WIDTH &&
                b.x < SCREEN_WIDTH - BALL_SIZE &&
                p2.getyPosition() < b.y + (BALL_SIZE / 2)
                && b.y + (BALL_SIZE / 2) < p2.getyPosition() + PADDLE_HEIGHT) {
            b.xDir = 'L';
        } else if ( b.x > SCREEN_WIDTH - BALL_SIZE) {
            Thread.sleep(500);
            p1.addScore();
            resetBall('R');
        }
    }

    public void resetBall(char loser) {
        b.x = (SCREEN_WIDTH - BALL_SIZE) / 2;
        b.y = (SCREEN_HEIGHT - BALL_SIZE) / 2;
        b.xDir = loser;
        b.yDir = 'U';
    }

    public void checkWin() {
        if (p1.getScore() == 11 || p2.getScore() == 11) {
            running = false;
        }
    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            try {
                checkGoal();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            checkWin();
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
