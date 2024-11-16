import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_HEIGHT = 800;
    static final int SCREEN_WIDTH = 1200;
    static final int GRID_SIZE = 10;
    static final int NUM_GRIDS_X = SCREEN_WIDTH / GRID_SIZE;
    static final int NUM_GRIDS_Y = SCREEN_HEIGHT / GRID_SIZE;
    static final int DELAY = 100;
    boolean[][] grid = new boolean[NUM_GRIDS_X][NUM_GRIDS_Y];
    int mouseX;
    int mouseY;
    boolean running = false;
    Timer timer;
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addMouseListener(new MyMouseAdapter());
        defaultGrid();
    }

    private void defaultGrid() {
        timer = new Timer(DELAY, this);
        timer.start();
        zeroGrid();
        makeDefaultShape();
    }

    private void zeroGrid() {
        for (int i = 0; i < NUM_GRIDS_X; i++) {
            for (int j = 0; j < NUM_GRIDS_Y; j++) {
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
        drawGrid(g);
        drawShapes(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 1; i < NUM_GRIDS_X; i++) {
            g.drawLine(i * GRID_SIZE, 0, i * GRID_SIZE, SCREEN_HEIGHT);
        }
        for (int j = 1; j < NUM_GRIDS_Y; j++) {
            g.drawLine(0, j * GRID_SIZE, SCREEN_WIDTH, j * GRID_SIZE);
        }
    }

    private void drawShapes(Graphics g) {
        g.setColor(Color.blue);
        for (int i = 0; i < NUM_GRIDS_X; i++) {
            for (int j = 0; j < NUM_GRIDS_Y; j++) {
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
    @Override
    public void actionPerformed(ActionEvent e) {
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
}
