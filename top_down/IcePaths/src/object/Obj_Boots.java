/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Obj_Boots
  -> Represents the boots
 */

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends Object {
    public Obj_Boots() {
        name = "boots";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
