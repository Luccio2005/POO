package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    int value = 5;
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Pocion de vida";
        down1 = setup("/objects/potion_red",gp.tileSize, gp.tileSize);
        description = "["+name+"]\nrecuperas tu vida en\n" +value+ ".";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Bebes la pocion, tu vida se \nrecupera en "+value;
        entity.life += value;
        if(gp.player.life > gp.player.maxlife){
            gp.player.life = gp.player.maxlife;
        }
        gp.playSE(2);
    }
}
