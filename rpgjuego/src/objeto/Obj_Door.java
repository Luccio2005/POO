package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_Door extends entidad{

    Panel_de_Juego gp;

    public Obj_Door(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;

        tipo = tipo_obstaculo;
        nombre="puerta";
        down1= setup("/objetos/door", gp.tileSize, gp.tileSize);
        colision = true;

        areadecolision.x = 0;
        areadecolision.y = 16;
        areadecolision.width = 48;
        areadecolision.height = 32;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
    }
    public void interactuar(){
        gp.estadodeljuego = gp.dialogo;
        gp.ui.dialogoactual = "Necesitas una llave para abrir la puerta";
    }
}
