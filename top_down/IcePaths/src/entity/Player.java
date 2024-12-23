/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Player
  -> represents the player and everything the player can do
  -> including picking up and putting down logs
 */

package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;
    boolean hasBoots, hasHat, hasMittens;
    boolean prev2;

    // WHERE WE DRAW CHARACTER ON SCREEN
    public final int screenX;
    public final int screenY;
    public int hasLogs = 0;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // HIT BOX
        solidArea = new Rectangle(12, 15, 24, 27);
        solidAreaDefaultX = 12;
        solidAreaDefaultY = 15;

        setDefaultValues();;
        getPlayerImage();
    }

    private void setDefaultValues() {
        worldX = gp.tileSize * 35;
        worldY = gp.tileSize * 35;
        this.speed = 4;
        this.direction = "down";
        hasBoots = false;
        hasHat = false;
        hasMittens = false;
        prev2 = false;
    }

    private void getPlayerImage() {
        if (!hasBoots && !hasHat && !hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/naked/penguin_naked_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (hasBoots && !hasHat && !hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boots/penguin_boots_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!hasBoots && hasHat && !hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hat/penguin_hat_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!hasBoots && !hasHat && hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/mittens/penguin_mittens_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (hasBoots && hasHat && !hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bh/penguin_bh_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (hasBoots && !hasHat && hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_bm_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bm/penguin_nm_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!hasBoots && hasHat && hasMittens) {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/hm/penguin_hm_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                down0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_down_0.png"));
                down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_down_1.png"));
                down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_down_2.png"));
                left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_left_0.png"));
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_left_2.png"));
                right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_right_0.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_right_2.png"));
                up0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_up_0.png"));
                up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_up_1.png"));
                up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bhm/penguin_bhm_up_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // update only if a key is pressed
        if (kh.rightPressed || kh.downPressed || kh.upPressed || kh.leftPressed) {
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
            gp.cc.checkTile(this);

            // CHECK LOG COLLISION
            int objIndex = gp.cc.checkLog(this, true);
            dealWithLog(objIndex);

            // MOVING CHARACTER, IF APPLICABLE
            // if no collision will occur based on where we are trying to go,
            // can move
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

            // UPDATING SPRITE COUNTER
            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNumber == 0) {
                    spriteNumber = 1;
                } else if (spriteNumber == 1) {
                    if (prev2) {
                        spriteNumber = 0;
                        prev2 = false;
                    } else {
                        spriteNumber = 2;
                    }
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                    prev2 = true;
                }
                spriteCounter = 0;
            }
        }
        // PLACING AND PICKING UP LOGS
        if (kh.kPressed) {
            placeLog();
        } else if (kh.lPressed) {
            pickUpLog();
        }

    }

    public void dealWithLog(int index) {
        if (index != 999) {
            String name = gp.obj[index].name;
            switch (name) {
                case "log":
                    // if on ice
                    int col = gp.obj[index].worldX / gp.tileSize;
                    int row = gp.obj[index].worldY / gp.tileSize;
                    if (gp.tm.mapTileNum[col][row] == 1) {
                        hasLogs++;
                        gp.obj[index].worldX = -100;
                        gp.obj[index].worldY = -100;
                    }
                    break;
                case "boots":
                    hasBoots = true;
                    gp.obj[index].worldX = -100;
                    gp.obj[index].worldY = -100;
                    getPlayerImage();
                    gp.ui.showMessage("Boots picked up!");
                    break;
                case "hat":
                    hasHat = true;
                    gp.obj[index].worldX = -100;
                    gp.obj[index].worldY = -100;
                    getPlayerImage();
                    gp.ui.showMessage("Hat picked up!");
                    break;
                case "mittens":
                    hasMittens = true;
                    gp.obj[index].worldX = -100;
                    gp.obj[index].worldY = -100;
                    getPlayerImage();
                    gp.ui.showMessage("Mittens picked up!");
                    break;
                case "coin":
                    if (hasHat && hasMittens && hasBoots) {
                        gp.ui.gameFinished = true;
                    } else {
                        gp.ui.showMessage("Need boots, hat, and mittens");
                    }
                    break;
            }
        }
    }

    public void placeLog() {
        if (hasLogs > 0) {
            int[] next = gp.nextTile.nextTile();
            int worldX = next[0] * gp.tileSize;
            int worldY = next[1] * gp.tileSize;
            boolean logThere = false;
            // make sure there is not already a log there
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i].worldX == worldX && gp.obj[i].worldY == worldY) {
                    if (gp.obj[i].name.equals("log")) {
                        logThere = true;
                    }
                }
            }
            // make sure it is not cloud
            if (gp.tm.mapTileNum[next[0]][next[1]] == 0) {
                logThere = true;
            }
            if (!logThere) {
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[i].worldX == -100 && gp.obj[i].worldY == -100) {
                        gp.obj[i].worldX = worldX;
                        gp.obj[i].worldY = worldY;
                        hasLogs--;
                        break;
                    }
                }
            }
        }
    }

    public void pickUpLog() {
        int[] next = gp.nextTile.nextTile();
        int worldX = next[0] * gp.tileSize;
        int worldY = next[1] * gp.tileSize;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i].worldX == worldX && gp.obj[i].worldY == worldY && gp.obj[i].name.equals("log")) {
                hasLogs++;
                gp.obj[i].worldX = -100;
                gp.obj[i].worldY = -100;
                break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "down":
                if (spriteNumber == 0) {
                    g2.drawImage(down0, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(down1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(down2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "left":
                if (spriteNumber == 0) {
                    g2.drawImage(left0, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(left1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(left2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "right":
                if (spriteNumber == 0) {
                    g2.drawImage(right0, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(right1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(right2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "up":
                if (spriteNumber == 0) {
                    g2.drawImage(up0, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 1) {
                    g2.drawImage(up1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (spriteNumber == 2) {
                    g2.drawImage(up2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                break;
        }

    }
}
