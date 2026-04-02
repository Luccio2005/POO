/*package object;

import Main.GamePanel;
import entity.Entity;
import tile_interactive.IT_Torch;

public class OBJ_Torch extends Entity {

    GamePanel gp;
    public static final String objName = "Antorcha";

    public OBJ_Torch(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable; // puedes usarla
        name = objName;
        stackable = true;
        down1 = setup("/objects/torch1", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIlumina una zona\nal colocarla.";
        price = 3;
    }

    @Override
    public boolean use(Entity entity) {

        int col = gp.player.getCol();
        int row = gp.player.getRow();

        switch (gp.player.direction) {
            case "up": row--; break;
            case "down": row++; break;
            case "left": col--; break;
            case "right": col++; break;
        }

        // colocar antorcha en la primera posición libre
        for (int i = 0; i < gp.iTile[1].length; i++) {
            if (gp.iTile[gp.currentMap][i] == null) {
                gp.iTile[gp.currentMap][i] = new IT_Torch(gp, col, row);
                gp.playSE(2);
                return true; // consume 1 unidad
            }
        }

        return false;
    }
}*/