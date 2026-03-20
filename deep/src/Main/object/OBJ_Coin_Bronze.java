package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Coin_Bronze extends Entity{
    GamePanel gp;
    public static final String objName = "Moneda de Bronce";

    public OBJ_Coin_Bronze(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity){
        gp.playSE(1);
        gp.ui.addMessage("Moneda +" + value);
        gp.player.coin += value;
        return true;
    }
}
