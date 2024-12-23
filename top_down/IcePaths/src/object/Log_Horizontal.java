/*
|--------------------------------|
|   COPYRIGHT 2024 EGOR AKULOV   |
|   Project IcePaths             |
|--------------------------------|

Current File: Log_Horizontal
  -> Represents a log placed horizontally
 */
package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Log_Horizontal extends Object{

    public Log_Horizontal() {
        name = "log";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/log_horizontal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
