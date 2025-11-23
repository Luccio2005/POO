package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_heart extends entidad{
    Panel_de_Juego gp;

    public Obj_heart(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;

        tipo = tipo_agarrarsolo;
        nombre="corazon";
        valor = 2;
        down1 = setup("/objetos/heart_full", gp.tileSize, gp.tileSize);
        imagen = setup("/objetos/heart_full", gp.tileSize, gp.tileSize);
        imagen2 = setup("/objetos/heart_half", gp.tileSize, gp.tileSize);
        imagen3 = setup("/objetos/heart_blank", gp.tileSize, gp.tileSize);
    }
    public boolean usar(entidad entidad){
        gp.playSE(2);
        gp.ui.anadirmensaje("vida +"+ valor);
        entidad.vida += valor;
        return true;
    }
}
