package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp){
        super(gp);

        type = type_axe;
        name = "Hacha de madera";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+name+"]\npuede cortar \nalgunos arboles";
        price = 10;
        knockBackPower = 10;
    }
}
