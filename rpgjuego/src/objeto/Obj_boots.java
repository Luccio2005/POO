package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_boots extends entidad{

    public Obj_boots(Panel_de_Juego gp) {
        super(gp);
        nombre="botas";
        down1 = setup("/objetos/boots");
    }
}
