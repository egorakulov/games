import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    Timer timer;
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addMouseListener(new MyMouseAdapter());
        this.addKeyListener(new MyKeyAdapter());
        defaultGrid();
    }

    private void defaultGrid() {
        timer = new Timer(DELAY, this);
        timer.start();
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
        } else {
            drawGrid(g);
            drawShapes(g);
        }
    }

    private void drawIntro(Graphics g) {

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
            if (intro) {
                if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                    intro = false;
                }
            } else {
                if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                    running = true;
                }
                if (k.getKeyCode() == KeyEvent.VK_R) {
                    running = false;
                    defaultGrid();
                }
            }
        }
    }
}
