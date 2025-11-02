package tile;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public boolean animated = false;
    public BufferedImage[] animationFrames;
    

    
    public Tile(String imagePath, boolean collision, boolean animated) {
        this.collision = collision;
        this.animated = animated;
        loadTilesetImages(imagePath, collision, animated);
    }
    public void loadTilesetImages(String imagePath, boolean collision, boolean animated) {
        if (animated) {
            // Si es animado, carga los 4 frames
            animationFrames = new BufferedImage[4];
            for (int i = 1; i <= 4; i++) {
                String path = imagePath + i + ".png";
                animationFrames[i-1] = loadImage(path);
            }
        } else {
            // Si no es animado, solo carga una imagen
            image = loadImage(imagePath);
        }
    }
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public BufferedImage getImage(int frameIndex) {
        if (animated && animationFrames != null) {
            return animationFrames[frameIndex];
        }
        return image;
    }
}
