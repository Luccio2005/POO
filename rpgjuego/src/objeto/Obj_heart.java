package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_heart extends entidad{

    public Obj_heart(Panel_de_Juego gp) {
        super(gp);
        nombre="corazon";
        imagen = setup("/objetos/heart_full", gp.tileSize, gp.tileSize);
        imagen2 = setup("/objetos/heart_half", gp.tileSize, gp.tileSize);
        imagen3 = setup("/objetos/heart_blank", gp.tileSize, gp.tileSize);

    }
}
