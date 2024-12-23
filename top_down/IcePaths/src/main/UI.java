/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: UI
  -> Handles and draws all extra UI components (how many logs you have,
     what clothes you have, the time, any messages, etc.)
 */

package main;

import object.Log_Horizontal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial40, arial80B;
    Color info;
    BufferedImage logImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    // DISPLAY TIME SPENT PLAYING
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80B = new Font("Arial", Font.BOLD, 80);
        info = new Color(255, 191, 0);
        Log_Horizontal log = new Log_Horizontal();
        logImage = log.image;
        playTime = 0;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(info);
        if (gameFinished) {
            String text;
            int textLength;
            int x;
            int y;
            g2.setColor(Color.darkGray);
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

            g2.setColor(new Color(255, 127, 80));
            text = "You got the coin!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth - textLength) / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Your Time is: " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth - textLength) / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial80B);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth - textLength) / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            // draw the penguin again
            gp.player.draw(g2);

            // STOP THE GAME
            gp.gameThread = null;
        } else {
            // draw number of keys player has
            g2.drawImage(logImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" x " + gp.player.hasLogs, 74, 65);

            // TIME
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 4);
                messageCounter++;

                // stop displaying message after 2 seconds
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
