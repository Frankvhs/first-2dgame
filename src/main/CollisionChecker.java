package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity, String direction){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; 
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBotWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; 
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBotRow = entityBotWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBotRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityLeftCol][entityBotRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBotRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }

    }

}
