package objeto;

import entidad.entidad;
import Main.Panel_de_Juego;

public class Obj_lampara extends entidad{
    public Obj_lampara(Panel_de_Juego gp){
        super(gp);

        tipo = tipo_luz;
        nombre = "lampara";
        down1 = setup("/objetos/lantern",gp.tileSize,gp.tileSize);
        descripcion = "[Lampara]\n Ilumina tus \nalrededores";
        precio = 200;
        radiosdeluz = 250;
    }
}
