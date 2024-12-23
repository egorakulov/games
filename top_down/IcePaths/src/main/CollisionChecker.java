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

                if (entityLeftCol == entityRightCol) {
                    // lined up on a single tile
                    if (tileNum1 == 2) {
                        // on water
                        // MAKE SURE YOU'RE ON A LOG
                        if (nextIsLog(entityLeftCol, entityTopRow)) {
                            entity.collisionOn = false;
                            break;
                        }
                    }
                }
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                // try to predict where player will go
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                // potential bug
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                if (entityLeftCol == entityRightCol) {
                    // lined up on a single tile
                    if (tileNum1 == 2) {
                        // on water
                        // MAKE SURE YOU'RE ON A LOG
                        if (nextIsLog(entityLeftCol, entityBottomRow)) {
                            entity.collisionOn = false;
                            break;
                        }
                    }
                }
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
                if (entityTopRow == entityBottomRow) {
                    // lined up on a single tile
                    if (tileNum1 == 2) {
                        // on water
                        // MAKE SURE YOU'RE ON A LOG
                        if (nextIsLog(entityLeftCol, entityTopRow)) {
                            entity.collisionOn = false;
                            break;
                        }
                    }
                }
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
                if (entityTopRow == entityBottomRow) {
                    // lined up on a single tile
                    if (tileNum1 == 2) {
                        // on water
                        // MAKE SURE YOU'RE ON A LOG
                        if (nextIsLog(entityRightCol, entityTopRow)) {
                            entity.collisionOn = false;
                            break;
                        }
                    }
                }
                if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public boolean nextIsLog(int col, int row) {
        for (int i = 0; i < gp.obj.length; i++) {
            int objCol = gp.obj[i].worldX / gp.tileSize;
            int objRow = gp.obj[i].worldY / gp.tileSize;
            if (col == objCol && row == objRow) {
                return true;
            }
        }
        return false;
    }

    // picks up log if on ice. if on water can walk on log
    public int checkLog(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {

                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        // automatically checks whether two rectangles are touching
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                // only players can pick up objects
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        // automatically checks whether two rectangles are touching
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                // only players can pick up objects
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        // automatically checks whether two rectangles are touching
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                // only players can pick up objects
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        // automatically checks whether two rectangles are touching
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                // only players can pick up objects
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
                        }
}
