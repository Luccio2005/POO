package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Key extends Entity{
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Llave";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nabre una puerta";
    }
}
