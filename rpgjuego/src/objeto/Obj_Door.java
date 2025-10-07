package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_Door extends entidad{

    public Obj_Door(Panel_de_Juego gp) {
        super(gp);
        nombre="puerta";
        down1= setup("/objetos/door");

        colision = true;
        areadecolision.x = 0;
        areadecolision.y = 16;
        areadecolision.width = 48;
        areadecolision.height = 32;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
    }
}
