package entity;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Entity {

    public int x, y;
    public int speed;
    public HashMap<String, BufferedImage> frames =  new HashMap<>();
    public String direction = "left";
    public String frameId = "1";
    public int frameCounter = 0;
    public int animationSpeed = 10;

    public BufferedImage readImage (String name, String s) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(s));
            frames.put(name, image);
            return image;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
