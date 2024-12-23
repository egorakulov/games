/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Obj_Hat
  -> Represents the hat sprite
 */

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Hat extends Object {
    public Obj_Hat() {
        name = "hat";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/hat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
