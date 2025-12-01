package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_sword_normal extends entidad{
    public Obj_sword_normal(Panel_de_Juego gp){
        super(gp);
        tipo = tipo_espada;
        nombre = "espada normal";
        down1 = setup("/objetos/sword_normal", gp.tileSize, gp.tileSize);
        valordeatq =1;
        areadeataque.width = 20;
        areadeataque.height = 20;
        descripcion = "[" +nombre+"]\nuna vieja espada";
        precio = 20;
        knockbackpower = 2;
        motion1_duracion = 5;
        motion2_duracion = 25;
    }
}
