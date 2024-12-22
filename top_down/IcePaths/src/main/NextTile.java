/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: NextTile
  -> highlights the next tile if the user presses J
 */

package main;

import java.awt.*;

public class NextTile {
    GamePanel gp;
    KeyHandler kh;

    public NextTile(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
    }

    // returns a {worldCol, worldRow} that the player is about to hit
    public int[] nextTile() {
        String direction = gp.player.direction;
        int worldCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int worldRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

        switch (direction) {
            case "down":
                worldRow++;
                break;
            case "up":
                worldRow--;
                break;
            case "left":
                worldCol--;
                break;
            case "right":
                worldCol++;
                break;
        }
        int[] res = {worldCol, worldRow};
        return res;
    }

    // draws an outline of what is the next tile, when j is pressed
    public void draw(Graphics2D g2) {
        if (kh.jPressed) {
            int[] res = nextTile();
            int worldCol = res[0];
            int worldRow = res[1];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            int alpha = 127; // 50% transparent
            Color denim = new Color(111, 143, 175, alpha);
            g2.setColor(denim);
            g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        }
    }
}
