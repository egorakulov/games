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
    int hasKey = 0;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;


        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(12, 15, 24, 27);
        solidAreaDefaultX = 12;
        solidAreaDefaultY = 15;

        setDefaultValues();
        getPlayerImage();
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
            if (kh.upPressed) {
                direction = "up";
            } else if (kh.downPressed) {
                direction = "down";
            } else if (kh.leftPressed) {
                direction = "left";
            } else if (kh.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF collisionOn = FALSE, PLAYER CAN MOVE
            if (!collisionOn) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch (objectName) {
                case "key":
                    hasKey++;
                    gp.obj[index] = null;
                    break;
                case "door":
                    if (hasKey > 0) {
                        gp.obj[index] = null;
                        hasKey--;
                    }
                    break;
                case "Cream":
                    speed += 2;
                    gp.obj[index] = null;
                    break;
            }
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
