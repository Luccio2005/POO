package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Lantern extends Entity {
    public static final String objName = "Lampara";

    public OBJ_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nilumina tus \nalrededores";
        price = 200;
        lightRadius = 250;
    }
}
