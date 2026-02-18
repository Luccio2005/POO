package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Chest extends Entity {
    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(GamePanel gp, Entity loot){
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "Cofre";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact(){
        gp.gameState = gp.dialogueState;
        if(opened == false){
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("Abriste el cofre y encontraste una "+loot.name+"!");

            if(gp.player.canObtainItem(loot) == false){
                sb.append("\n...Pero no puedes llevar nada mas!");
            }else{
                sb.append("\nObtienes una "+loot.name+"!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }else{
            gp.ui.currentDialogue = "Esta vacio";
        }
    }
}
