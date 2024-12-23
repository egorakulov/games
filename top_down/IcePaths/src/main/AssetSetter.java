/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: AssetSetter
  -> Places logs on the map
 */

package main;

import object.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        // placing horizontal logs
        gp.obj[0] = new Log_Horizontal();
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 15 * gp.tileSize;

        gp.obj[1] = new Log_Horizontal();
        gp.obj[1].worldX = 44 * gp.tileSize;
        gp.obj[1].worldY = 16 * gp.tileSize;

        gp.obj[2] = new Log_Horizontal();
        gp.obj[2].worldX = 44 * gp.tileSize;
        gp.obj[2].worldY = 13 * gp.tileSize;

        gp.obj[3] = new Log_Horizontal();
        gp.obj[3].worldX = 48 * gp.tileSize;
        gp.obj[3].worldY = 23 * gp.tileSize;

        gp.obj[4] = new Log_Horizontal();
        gp.obj[4].worldX = 15 * gp.tileSize;
        gp.obj[4].worldY = 24 * gp.tileSize;

        gp.obj[5] = new Log_Horizontal();
        gp.obj[5].worldX = 20 * gp.tileSize;
        gp.obj[5].worldY = 54 * gp.tileSize;

        gp.obj[6] = new Log_Horizontal();
        gp.obj[6].worldX = 30 * gp.tileSize;
        gp.obj[6].worldY = 33 * gp.tileSize;

        gp.obj[7] = new Log_Horizontal();
        gp.obj[7].worldX = 32 * gp.tileSize;
        gp.obj[7].worldY = 41 * gp.tileSize;

        gp.obj[8] = new Log_Horizontal();
        gp.obj[8].worldX = 41 * gp.tileSize;
        gp.obj[8].worldY = 30 * gp.tileSize;

        gp.obj[9] = new Log_Horizontal();
        gp.obj[9].worldX = 35 * gp.tileSize;
        gp.obj[9].worldY = 40 * gp.tileSize;

        gp.obj[10] = new Log_Horizontal();
        gp.obj[10].worldX = 43 * gp.tileSize;
        gp.obj[10].worldY = 35 * gp.tileSize;

        gp.obj[11] = new Log_Horizontal();
        gp.obj[11].worldX = 35 * gp.tileSize;
        gp.obj[11].worldY = 50 * gp.tileSize;

        gp.obj[12] = new Log_Horizontal();
        gp.obj[12].worldX = 38 * gp.tileSize;
        gp.obj[12].worldY = 53 * gp.tileSize;

        gp.obj[13] = new Log_Horizontal();
        gp.obj[13].worldX = 68 * gp.tileSize;
        gp.obj[13].worldY = 58 * gp.tileSize;

        gp.obj[14] = new Log_Horizontal();
        gp.obj[14].worldX = 50 * gp.tileSize;
        gp.obj[14].worldY = 52 * gp.tileSize;

        Random random = new Random();
        setBoots(random);
        setHat(random);
        setMittens(random);
        gp.obj[18] = new Obj_Coin();
        gp.obj[18].worldX = 10 * gp.tileSize;
        gp.obj[18].worldY = 38 * gp.tileSize;
    }

    private void setBoots(Random random) {
        int[][] possibleValues = { {22, 14}, {58, 18}, {47, 21}, {13, 55}, {30, 53}};
        int randomNumber = random.nextInt(5);
        gp.obj[15] = new Obj_Boots();
        gp.obj[15].worldX = possibleValues[randomNumber][0] * gp.tileSize;
        gp.obj[15].worldY = possibleValues[randomNumber][1] * gp.tileSize;
    }

    private void setHat(Random random) {
        int[][] possibleValues = { {52, 48}, {50, 54}, {36, 57}, {57, 16}, {40, 14}};
        int randomNumber = random.nextInt(5);
        gp.obj[16] = new Obj_Hat();
        gp.obj[16].worldX = possibleValues[randomNumber][0] * gp.tileSize;
        gp.obj[16].worldY = possibleValues[randomNumber][1] * gp.tileSize;
    }

    private void setMittens(Random random) {
        int[][] possibleValues = { {14, 21}, {17, 54}, {35, 55}, {46, 55}, {45, 12}};
        int randomNumber = random.nextInt(5);
        gp.obj[17] = new Obj_Mittens();
        gp.obj[17].worldX = possibleValues[randomNumber][0] * gp.tileSize;
        gp.obj[17].worldY = possibleValues[randomNumber][1] * gp.tileSize;
    }

}
