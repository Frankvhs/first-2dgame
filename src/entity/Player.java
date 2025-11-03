package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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

    // Caja de colisión más pequeña: centrada horizontalmente, solo los pies
    solidArea = new Rectangle(12, 24, 24, 24);

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = 100;
    worldY = 100;
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
      
      // Procesa cada dirección independientemente (sin else if)
      if (keyH.upPressed) {
        direction = "up";
        collisionOn = false;
        gp.coChecker.checkTile(this, "up");
        if(!collisionOn){
          worldY -= speed;
        }
      }
      if (keyH.downPressed) {
        direction = "down";
        collisionOn = false;
        gp.coChecker.checkTile(this, "down");
        if(!collisionOn){
          worldY += speed;
        }
      }
      if (keyH.leftPressed) {
        direction = "left";
        collisionOn = false;
        gp.coChecker.checkTile(this, "left");
        if(!collisionOn){
          worldX -= speed;
        }
      }
      if (keyH.rightPressed) {
        direction = "right";
        collisionOn = false;
        gp.coChecker.checkTile(this, "right");
        if(!collisionOn){
          worldX += speed;
        }
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
