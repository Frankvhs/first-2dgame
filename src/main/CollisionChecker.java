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

        public int checkObject(Entity entity,boolean player){
            int index = 999;

            for(int i = 0; i < gp.obj.length; i++){
                if(gp.obj[i] != null){

                    //Get entity's solid area position
                    entity.solidArea.x += entity.worldX;
                    entity.solidArea.y += entity.worldY;
                    //Get the object's solid area position
                    gp.obj[i].solidArea.x += gp.obj[i].worldX;
                    gp.obj[i].solidArea.y += gp.obj[i].worldY;

                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                //System.out.println("up collision");
                                if (gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }
                                if(player){
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                             if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                //System.out.println("down collision");
                                if (gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }
                                if(player){
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                             if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                //System.out.println("left collision");
                                if (gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }
                                if(player){
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                             if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                //System.out.println("right collision");
                                if (gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }
                                if(player){
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                    gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
                }
            }

            return index;
        }
}
