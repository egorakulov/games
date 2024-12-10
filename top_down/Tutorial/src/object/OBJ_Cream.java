package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cream extends SuperObject {
    public OBJ_Cream() {
        name = "Cream";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/ice_cream.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
