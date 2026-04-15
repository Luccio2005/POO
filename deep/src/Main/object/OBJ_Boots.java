package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity {
    GamePanel gp;
    int value= 2;
    public static final String objName = "Botas";

    public OBJ_Boots(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_boots;
        name = objName;
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nte hace mas veloz";
        price = 10;
    }
    public void setDialogue(){
        dialogues[0][0] = "Tu velocidad aumenta en "+value;
    }
    public void use_boots(Entity entity){
        startDialogue(this, 0);
        entity.speed += value;
        gp.playSE(2);
    }
}
