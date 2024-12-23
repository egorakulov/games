/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Obj_Mittens
  -> Represents the mittens sprite
 */

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Mittens extends Object {
    public Obj_Mittens() {
        name = "mittens";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/mittens.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
