package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    // what gamePanel this player appears in
    GamePanel gp;
    // the keyHandler associated with this player
    KeyHandler kh;

    // where we draw the player on the screen
    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        setDefaultValues();
        getPlayerImage();

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY= gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-up-0.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-up-1.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-left-0.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-left-1.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-right-0.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-right-1.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-down-0.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/penguin-down-1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            spriteCounter++;
        }
        if (kh.upPressed) {
            direction = "up";
            worldY -= speed;
        }
        if (kh.downPressed) {
            direction = "down";
            worldY += speed;
        }
        if (kh.leftPressed) {
            direction = "left";
            worldX -= speed;
        }
        if (kh.rightPressed) {
            direction = "right";
            worldX += speed;
        }

        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
    }
}
