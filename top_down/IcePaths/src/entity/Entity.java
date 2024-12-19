/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Entity
  -> Class from which all entities (players, NPCs, monsters) are derived from
  -> Stores information about entity's x, y, images for them, direction
        they are moving
 */

package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

}
