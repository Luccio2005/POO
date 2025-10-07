package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_chest extends entidad{

    public Obj_chest(Panel_de_Juego gp) {
        super(gp);
        nombre="cofre";
        down1 = setup("/objetos/chest");
    }
}
