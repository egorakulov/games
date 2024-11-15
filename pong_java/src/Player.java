public class Player {
    private int xPosition;
    private int yPosition;
    private int score;
    private int paddleSpeed;
    private char direction;
    Player(int x, int y, int speed) {
        this.xPosition = x;
        this.yPosition = y;
        this.score = 0;
        this.paddleSpeed = speed;
        this.direction = 'N';
    }

    public int getxPosition() {
        return xPosition;
    }
    public int getyPosition() {
        return yPosition;
    }

    public int getScore() {
        return score;
    }

    public int getDirection() {
        return direction;
    }


    public void movePaddleUp() {
        this.yPosition -= paddleSpeed;
    }
    public void movePaddleDown() {
        this.yPosition += paddleSpeed;
    }
    public void addScore() {
        this.score++;
    }

    public void setDirection(char s) {
        if (s == 'U') {
            this.direction = 'U';
        } else if (s == 'D') {
            this.direction = 'D';
        } else if (s == 'N') {
            this.direction = 'N';
        }
    }
}
