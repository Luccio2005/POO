package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
public class Obj_manacrystal extends entidad{
    Panel_de_Juego gp;
    public Obj_manacrystal(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;

        tipo = tipo_agarrarsolo;
        nombre = "Crystal de mana";
        valor = 1;
        down1 = setup("/objetos/manacrystal_full", gp.tileSize, gp.tileSize);
        imagen = setup("/objetos/manacrystal_full", gp.tileSize, gp.tileSize);
        imagen2 = setup("/objetos/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
    public boolean usar(entidad entidad){
        gp.playSE(2);
        gp.ui.anadirmensaje("mana +"+ valor);
        entidad.mana += valor;
        return true;
    }
}
