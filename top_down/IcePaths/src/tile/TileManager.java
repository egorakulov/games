/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: TileManager
  -> draws the map
 */

package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTiles();
        loadMap("maps/icePaths_map.txt");
    }

    private void loadTiles() {
        try {
            // cloud
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/cloud.png"));
            tile[0].collision = true;
            tile[0].name = "cloud";

            // ice
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/ice.png"));
            tile[1].name = "ice";

            // water
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));
            tile[2].collision = true;
            tile[2].name = "water";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String path) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow= 0;

        // iterates through every tile on the map
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldRow][worldCol];
            int worldX = worldRow * gp.tileSize;
            int worldY = worldCol * gp.tileSize;

            // only draw tiles that appear on screen
            if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                    worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                    worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                    worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
