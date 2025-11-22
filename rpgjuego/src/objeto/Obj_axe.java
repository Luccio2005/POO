package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
public class Obj_axe extends entidad{
    public Obj_axe(Panel_de_Juego gp){
        super(gp);
        tipo= tipo_hacha;
        nombre = "hacha";
        down1 = setup("/objetos/axe",gp.tileSize, gp.tileSize);
        valordeatq = 2;
        areadeataque.width = 17;
        areadeataque.height = 17;
        descripcion = "[" +nombre+"]\noxidada pero mas\npotente";
        precio = 75;
        knockbackpower = 10;
    }
}
