package main;

import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

   
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 5 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;

        gp.obj[1] = new OBJ_Chest();
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;
    }
}
