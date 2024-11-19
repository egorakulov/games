import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_HEIGHT = 800;
    static final int SCREEN_WIDTH = 1200;
    static final int GRID_SIZE = 15;
    static final int NUM_GRIDS_X = SCREEN_WIDTH / GRID_SIZE;
    static final int NUM_GRIDS_Y = SCREEN_HEIGHT / GRID_SIZE;
    static final int DELAY = 100;
    boolean[][] grid = new boolean[NUM_GRIDS_X + 1][NUM_GRIDS_Y + 1];
    boolean running = false;
    boolean intro = true;
    boolean controls = false;
    boolean hasTimer = false;
    Timer timer;
    Image image;
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addMouseListener(new MyMouseAdapter());
        this.addKeyListener(new MyKeyAdapter());
        defaultGrid();
    }

    private void defaultGrid() {
        if (!hasTimer) {
            timer = new Timer(DELAY, this);
            timer.start();
            hasTimer = true;
        }
        zeroGrid();
        makeDefaultShape();
    }

    private void zeroGrid() {
        for (int i = 0; i < NUM_GRIDS_X + 1; i++) {
            for (int j = 0; j < NUM_GRIDS_Y + 1; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void makeDefaultShape() {
        int i = NUM_GRIDS_X / 2;
        int j = NUM_GRIDS_Y / 2;
        grid[i][j] = true;
        grid[i+2][j] = true;
        grid[i][j+1] = true;
        grid[i+1][j+1] = true;
        grid[i+1][j+2] = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (intro) {
            drawIntro(g);
        } else if (controls) {
            drawControls(g);
        }else {
            drawGrid(g);
            drawShapes(g);
        }
    }

    private void drawIntro(Graphics g) {
        g.setColor(Color.blue);
        g.setFont(new Font("Rockwell", Font.BOLD, 75));
        FontMetrics metrics_title = getFontMetrics(g.getFont());
        g.drawString("Conway's Game of Life",
                (SCREEN_WIDTH - metrics_title.stringWidth("Conway's Game of Life")) / 2,
                100);
        drawRules(g);
    }

    private void drawRules(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Rockwell", Font.BOLD, 30));
        g.drawString("Rules:", 30, 175);
        Font header = new Font("Rockwell", Font.BOLD, 20);
        Font text = new Font("Rockwell", Font.ITALIC, 20);
        g.setFont(header);
        g.drawString("For a space that is populated:", 45, 215);
        g.drawString("Examples", 850, 215);
        g.drawString("For a space that is empty or unpopulated:", 45, 555);
        g.setFont(text);
        g.drawString("Each cell with one or no neighbors dies, as if by solitude.", 60, 255);
        g.drawString("Each cell with four or more neighbors dies, as if by overpopulation.", 60, 355);
        g.drawString("Each cell with two or three neighbors survives.", 60, 455);
        g.drawString("Each cell with three neighbors becomes populated.", 60, 595);
        g.setColor(Color.blue);
        g.setFont(new Font("Rockwell", Font.ITALIC, 50));
        String s = "Press the space bar to see the controls.";
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(s, (SCREEN_WIDTH - metrics.stringWidth(s)) / 2, 700);
    }
    private void drawControls(Graphics g) {
        g.setFont(new Font("Rockwell", Font.BOLD, 100));
        g.setColor(Color.blue);
        g.drawString("CONTROLS", 30, 100);
        String in = "Once in the simulation, ";
        String i = "Press I to go back to the intro screen.";
        String c = "Press C to go back to the control screen.";
        String space = "Press space to start the simulation.";
        String r = "Press R to reset the simulation.";
        String s = "Press S to stop the simulation.";
        String start = "Press space to go to simulation.";
        g.setColor(Color.black);
        g.setFont(new Font("Rockwell", Font.BOLD, 50));
        g.drawString(in, 30, 175);
        g.drawString(start, 30, 625);
        g.setFont(new Font("Rockwell", Font.ITALIC, 25));
        g.drawString(i, 60, 250);
        g.drawString(c, 60, 325);
        g.drawString(space, 60, 400);
        g.drawString(r, 60, 475);
        g.drawString(s, 60, 550);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 1; i < NUM_GRIDS_X + 1; i++) {
            g.drawLine(i * GRID_SIZE, 0, i * GRID_SIZE, SCREEN_HEIGHT);
        }
        for (int j = 1; j < NUM_GRIDS_Y + 1; j++) {
            g.drawLine(0, j * GRID_SIZE, SCREEN_WIDTH, j * GRID_SIZE);
        }
    }

    private void drawShapes(Graphics g) {
        g.setColor(Color.blue);
        for (int i = 0; i < NUM_GRIDS_X + 1; i++) {
            for (int j = 0; j < NUM_GRIDS_Y + 1; j++) {
                if (grid[i][j]) {
                    g.fillRect(i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE, GRID_SIZE);
                }
            }
        }
    }

    private void handleClick(int x, int y) {
        int whichGridX = x / GRID_SIZE;
        int whichGridY = y / GRID_SIZE;
        grid[whichGridX][whichGridY] = !grid[whichGridX][whichGridY];
    }
    // main logic
    private void move() {
        boolean[][] newGrid = new boolean[NUM_GRIDS_X + 1][NUM_GRIDS_Y + 1];
        // have to have case where in first row or first col
        // or case where in last row or last col
        for (int row = 1; row < NUM_GRIDS_X; row++) {
            for (int col = 1; col < NUM_GRIDS_Y; col++) {
                int neighbors = numNeighbors(row, col);
                if (grid[row][col]) {
                    // current square exists
                    if (neighbors <= 1) {
                        newGrid[row][col] = false;
                    } else if (neighbors <= 3) {
                        newGrid[row][col] = true;
                    } else {
                        newGrid[row][col] = false;
                    }
                } else {
                    // current square does not exist
                    if (neighbors == 3) {
                        newGrid[row][col] = true;
                    } else {
                        newGrid[row][col] = false;
                    }
                }
            }
        }
        grid = newGrid;
    }

    private int numNeighbors(int row, int col) {
        int result = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    try {
                        if (grid[row + i][col + j]) {
                            result++;
                        }
                    } catch (IndexOutOfBoundsException ex) {

                    }
                }
            }
        }
        return result;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }

    public class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent me) {
            int x = me.getX();
            int y = me.getY();
            handleClick(x, y);
        }
    }

    // when you press space bar start running the program
    // when you press s stop running the program
    // when you press r reset the program
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent k) {
            // intro space bar
            if (intro) {
                if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                    intro = false;
                    controls = true;
                }
            // controls space bar
            } else if (controls) {
                if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                    controls = false;
                }
            // simulation space bar
            } else {
                if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                    running = true;
                }
            }
            if (k.getKeyCode() == KeyEvent.VK_I) {
                controls = false;
                running = false;
                intro = true;
            }
            if (k.getKeyCode() == KeyEvent.VK_R) {
                running = false;
                defaultGrid();
            }
            if (k.getKeyCode() == KeyEvent.VK_S) {
                running = false;
            }
            if (k.getKeyCode() == KeyEvent.VK_C) {
                running = false;
                controls = true;
            }
        }
    }
}
