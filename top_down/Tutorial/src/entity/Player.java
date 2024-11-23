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
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
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
        if (kh.upPressed) {
            direction = "up";
            y -= speed;
        }
        if (kh.downPressed) {
            direction = "down";
            y += speed;
        }
        if (kh.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (kh.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
    }
}
