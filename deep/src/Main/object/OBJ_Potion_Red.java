package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    public static final String objName = "Pocion de vida";
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("/objects/potion_red",gp.tileSize, gp.tileSize);
        description = "["+name+"]\nrecuperas tu vida en\n" +value+ ".";
        price = 5;
        stackable = true;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "Bebes la pocion, tu vida se \nrecupera en "+value;
    }
    public boolean use(Entity entity){
        startDialogue(this,0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
