package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obj_Coin extends Object {
    BufferedImage coin0, coin1, coin2, coin3, coin4, coin5;
    int spriteCounter = 0;
    int spriteNumber = 0;

    public Obj_Coin() {
        name = "coin";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_0.png"));
            coin0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_0.png"));
            coin1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_1.png"));
            coin2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_2.png"));
            coin3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_3.png"));
            coin4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_4.png"));
            coin5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin_5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        spriteCounter++;
        if (spriteCounter > 6) {
            spriteNumber = (spriteNumber + 1) % 5;
            spriteCounter = 0;
        }

        // only draws the objects that appear on the screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // only draws the tiles that appear on screen
        if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {

            // draws only the tiles that appear on the screen
            switch (spriteNumber) {
                case 0:
                    g2.drawImage(coin0, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case 1:
                    g2.drawImage(coin1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case 2:
                    g2.drawImage(coin2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case 3:
                    g2.drawImage(coin3, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case 4:
                    g2.drawImage(coin4, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
            }
        }
    }
}
