package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    public int currentFrame = 0;
    public int frameCounter = 0;
    public int animationSpeed = 10;
    int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
        
        setupTiles();
        loadMap("/maps/map.txt"); 
    }

    public void setupTiles() {
        // Crea un array con suficiente espacio para tus tiles
        tiles = new Tile[10];
        
        // Índice 0: grass (estático, sin colisión)
        tiles[0] = new Tile("/tiles_sprites/grass.png", false, false);
        
        // Índice 1: yellow_flower (animado, sin colisión)
        tiles[1] = new Tile("/tiles_sprites/yellow_flower", false, true);
        
        // Índice 2: water (animado, con colisión)
        tiles[2] = new Tile("/tiles_sprites/water", true, true);
        
        // Índice 3: stone_brick (estático, con colisión)
        tiles[3] = new Tile("/tiles_sprites/stone_brick.png", true, false);
    }

    public void update() {
        frameCounter++;
        if (frameCounter > animationSpeed) {
            currentFrame++;
            if (currentFrame > 3) {
                currentFrame = 0;
            }
            frameCounter = 0;
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0; 

            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                
                col = 0;
                while (col < gp.maxScreenCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;

       while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
              int tileNum = mapTileNumber[col][row];
              
              // Obtiene el tile del array
              if (tileNum >= 0 && tileNum < tiles.length && tiles[tileNum] != null) {
                  BufferedImage tileImage = tiles[tileNum].getImage(currentFrame);                  
                  // Solo dibuja si la imagen no es null
                  if (tileImage != null) {
                      g2.drawImage(tileImage, x, y, gp.tileSize, gp.tileSize, null);
                  }
              }
              
              col++;
              x += gp.tileSize;
    
              if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
              }
         }
    }
}
