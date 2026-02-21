package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Tent extends Entity {
    GamePanel gp;
    public OBJ_Tent(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Carpa";
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "["+name+"]\npuedes dormir y se\nhara de dia";
        price = 10;
        stackable = true;
    }
    public boolean use(Entity entity){
        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxlife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepImage(down1);
        return true;
    }
}
