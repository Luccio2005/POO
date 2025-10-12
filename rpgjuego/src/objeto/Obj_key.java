package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
public class Obj_key extends entidad{

    public Obj_key(Panel_de_Juego gp) {
        super(gp);
        nombre="llave";
        down1 = setup("/objetos/key", gp.tileSize, gp.tileSize);

    }

}
