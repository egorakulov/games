/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Log_Vertical
  -> Represents an log placed vertically
 */
package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Log_Vertical extends Object {
    public Log_Vertical() {
        name = "log_vertical";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/log_vertical.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
