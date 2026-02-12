package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity {
    GamePanel gp;
    int value= 2;
    public OBJ_Boots(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_boots;
        name = "Botas";
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nte hace mas veloz";
        price = 10;
    }
    public void use_boots(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Tu velocidad aumenta en "+value;
        entity.speed += value;
        gp.playSE(2);
    }
}
