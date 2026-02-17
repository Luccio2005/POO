package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Key extends Entity{
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Llave";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nabre una puerta";
        price = 100;
    }
    public boolean use(Entity entity){
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "Puerta");

        if(objIndex != 999){
            gp.ui.currentDialogue = "Usas la " + name + " y abres la puerta";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex]= null;
            return true;
        } else {
            gp.ui.currentDialogue = "Que haces?";
            return false;
        }
    }
}
