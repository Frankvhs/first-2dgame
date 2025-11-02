package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;
  public final int screenX;
  public final int screenY;
  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    screenX = gp.screenWidth/2 - (gp.tileSize/2);
    screenY = gp.screenHeight/2 - (gp.tileSize/2);
    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage() {
    readImage("up1", "/player_sprites/Link_move_up.png");
    readImage("up2", "/player_sprites/Link_move_up2.png");
    readImage("down1", "/player_sprites/Link_move_down.png");
    readImage("down2", "/player_sprites/Link_move_down2.png");
    readImage("left1", "/player_sprites/Link_move_left.png");
    readImage("left2", "/player_sprites/Link_rest_left.png");
    readImage("right1", "/player_sprites/Link_move_right.png");
    readImage("right2", "/player_sprites/Link_rest_right.png");
  }

  public void update() {
    if (keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.upPressed) {
      if (keyH.upPressed) {
      direction = "up";
      worldY -= speed;
    } else if (keyH.downPressed) {
      direction = "down";
      worldY += speed;
    } else if (keyH.leftPressed) {
      direction = "left";
      worldX -= speed;
    } else if (keyH.rightPressed) {
      direction = "right";
      worldX += speed;
    }

    frameCounter++;
    if(frameCounter > animationSpeed){
      frameId = frameId == "1" ? "2" : "1";
      frameCounter = 0;
    }
    }
   
  }

  public void draw(Graphics2D g2) {
    // g2.setColor(Color.white);
    // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    BufferedImage image = frames.get(direction + frameId);
    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }
}
