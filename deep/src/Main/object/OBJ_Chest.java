package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Chest extends Entity {
    GamePanel gp;
    public static final String objName = "Cofre";

    public OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
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
    public void setLoot(Entity loot){
        this.loot = loot;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "Abriste el cofre y encontraste una "+loot.name+"!" + "\n...Pero no puedes llevar nada mas!";
        dialogues[1][0] = "Abriste el cofre y encontraste una "+loot.name+"!" + "\nObtienes una "+loot.name+"!";
        dialogues[2][0] = "Esta vacio";
    }
    public void interact(){
        if(opened == false){
            gp.playSE(3);
            if(gp.player.canObtainItem(loot) == false){
                startDialogue(this,0);
            }else{
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }else{
            startDialogue(this,2);
        }
    }
}
