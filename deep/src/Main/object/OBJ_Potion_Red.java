package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Pocion de vida";
        value = 5;
        down1 = setup("/objects/potion_red",gp.tileSize, gp.tileSize);
        description = "["+name+"]\nrecuperas tu vida en\n" +value+ ".";
        price = 5;
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Bebes la pocion, tu vida se \nrecupera en "+value;
        entity.life += value;
        gp.playSE(2);
    }
}
