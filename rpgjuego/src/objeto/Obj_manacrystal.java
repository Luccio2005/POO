package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
public class Obj_manacrystal extends entidad{
    Panel_de_Juego gp;
    public Obj_manacrystal(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        nombre = "Crystal de mana";
        imagen = setup("/objetos/manacrystal_full", gp.tileSize, gp.tileSize);
        imagen2 = setup("/objetos/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
}
