package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Blue extends Entity {
    public static final String objName = "Escudo azul";

    public OBJ_Shield_Blue(GamePanel gp){
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "["+name+"]\nhecho de materiales\nmas resistentes";
        price = 15;
    }
}
