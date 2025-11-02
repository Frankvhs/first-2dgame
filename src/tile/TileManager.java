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
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        setupTiles();
        loadMap("/maps/map.txt"); 
    }

    public void setupTiles() {
        // Crea un array con suficiente espacio para tus tiles
        tiles = new Tile[30];
        
        // Índice 0: grass (estático, sin colisión)
        tiles[0] = new Tile("/tiles_sprites/grass.png", false, false);
        
        // Índice 1: yellow_flower (animado, sin colisión)
        tiles[1] = new Tile("/tiles_sprites/yellow_flower", false, true);
        
        // Índice 2: water (animado, con colisión)
        tiles[2] = new Tile("/tiles_sprites/water", true, true);
        
        // Índice 3: stone_brick (estático, con colisión)
        tiles[3] = new Tile("/tiles_sprites/stone_brick.png", true, false);

        tiles[4] = new Tile("/tiles_sprites/forest_grass.png", false, false);
        tiles[5] = new Tile("/tiles_sprites/forest_flower", false, true);
        tiles[6] = new Tile("/tiles_sprites/desert.png", false, false);
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

            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) break; // Si no hay más líneas, salir
                
                String[] numbers = line.trim().split("\\s+"); // trim() quita espacios y \\s+ divide por cualquier espacio
                
                col = 0;
                for (int i = 0; i < numbers.length && col < gp.maxWorldCol; i++) {
                    // Salta strings vacíos
                    if (numbers[i].isEmpty()) {
                        continue;
                    }
                    try {
                        int num = Integer.parseInt(numbers[i]);
                        mapTileNumber[col][row] = num;
                        col++;
                    } catch (NumberFormatException e) {
                        System.err.println("Error parseando número en fila " + row + ", columna " + col + ": '" + numbers[i] + "'");
                    }
                }
                row++;
            }
            br.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
       int worldCol = 0;
       int worldRow = 0;
       int tilesDrawn = 0;

       while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
              int tileNum = mapTileNumber[worldCol][worldRow];
              int worldX = worldCol * gp.tileSize;
              int worldY = worldRow * gp.tileSize;
              int screenX = worldX - gp.player.worldX + gp.player.screenX;
              int screenY = worldY - gp.player.worldY + gp.player.screenY;
              
              // Solo dibuja tiles que están en pantalla
              if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                  worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                  worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                  worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                  
                  // Obtiene el tile del array
                  if (tileNum >= 0 && tileNum < tiles.length && tiles[tileNum] != null) {
                      BufferedImage tileImage = tiles[tileNum].getImage(currentFrame);                  
                      // Solo dibuja si la imagen no es null
                      if (tileImage != null) {
                          g2.drawImage(tileImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
                          tilesDrawn++;
                      }
                  }
              }
              
              worldCol++;
    
              if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
              }
         }
         
    }
}
