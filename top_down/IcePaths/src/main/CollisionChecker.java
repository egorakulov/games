/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: CollisionChecker
  -> checks for collisions between entities and tiles and player and objects
 */
package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // coordinates of hit box for this entity
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // which column the hit box is in
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                // try to predict where player will go
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                // potential bug
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    // TODO: MAKE SURE YOU'RE NOT A LOG
                    entity.collisionOn = true;
                }
                break;
            case "down":
                // try to predict where player will go
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                // potential bug
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                // try to predict where player will go
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                // potential bug
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                // try to predict where player will go
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                // potential bug
                tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // picks up log if on ice. if on water can walk on log
    public boolean checkLog() {
        // TODO: write checkLog
        return false;
    }
}
