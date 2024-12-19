package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;
    boolean hasBoots, hasHat, hasMittens;
    boolean prev2;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        this.direction = "down";
        hasBoots = false;
        hasHat = false;
        hasMittens = false;
        prev2 = false;
    }

    private void getPlayerImage() {
        try {
            down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_0.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_2.png"));
            left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_0.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_2.png"));
            right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_0.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_2.png"));
            up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_0.png"));
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // update only if a key is pressed
        if (kh.rightPressed || kh.downPressed || kh.upPressed || kh.leftPressed) {
            if (kh.upPressed) {
                direction = "up";
            } else if (kh.downPressed) {
                direction = "down";
            } else if (kh.leftPressed) {
                direction = "left";
            } else if (kh.rightPressed) {
                direction = "right";
            }

            // MOVING THE CHARACTER
            if (direction.equals("up")) {
                y -= speed;
            } else if (direction.equals("left")) {
                x -= speed;
            } else if (direction.equals("down")) {
                y += speed;
            } else if (direction.equals("right")) {
                x += speed;
            }

            // UPDATING SPRITE COUNTER
            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNumber == 0) {
                    spriteNumber = 1;
                } else if (spriteNumber == 1) {
                    if (prev2) {
                        spriteNumber = 0;
                        prev2 = false;
                    } else {
                        spriteNumber = 2;
                    }
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                    prev2 = true;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "down":
                if (spriteNumber == 0) {
                    g2.drawImage(down0, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(down1, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(down2, x, y, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "left":
                if (spriteNumber == 0) {
                    g2.drawImage(left0, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(left1, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(left2, x, y, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "right":
                if (spriteNumber == 0) {
                    g2.drawImage(right0, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(right1, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(right2, x, y, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "up":
                if (spriteNumber == 0) {
                    g2.drawImage(up0, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(up1, x, y, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(up2, x, y, gp.tileSize, gp.tileSize, null);
                }
                break;
        }

    }
}
