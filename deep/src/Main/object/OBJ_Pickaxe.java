package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Pickaxe extends Entity {
    public static final String objName = "Pico";

    public OBJ_Pickaxe(GamePanel gp){
        super(gp);

        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+name+"]\npuede triturar la piedra";
        price = 15;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
