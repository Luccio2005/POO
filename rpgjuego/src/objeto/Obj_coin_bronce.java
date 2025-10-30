package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_coin_bronce extends entidad{
    Panel_de_Juego gp;
    public Obj_coin_bronce(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;

        tipo = tipo_agarrarsolo;
        nombre = "moneda de bronce";
        valor = 1;
        down1 = setup("/objetos/coin_bronze", gp.tileSize, gp.tileSize);
    }
    public void usar(entidad entidad){
        gp.playSE(1);
        gp.ui.anadirmensaje("coin +" + valor);
        gp.jugador.coin += valor;
    }
}
