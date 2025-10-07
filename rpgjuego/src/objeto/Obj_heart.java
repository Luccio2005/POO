package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_heart extends entidad{

    public Obj_heart(Panel_de_Juego gp) {
        super(gp);
        nombre="corazon";
        imagen = setup("/objetos/heart_full");
        imagen2 = setup("/objetos/heart_half");
        imagen3 = setup("/objetos/heart_blank");

    }
}
