package objeto;
import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_shield_blue extends entidad{
    public Obj_shield_blue(Panel_de_Juego gp){
        super(gp);
        tipo = tipo_escudo;
        nombre = "escudo azul";
        down1 = setup("/objetos/shield_blue", gp.tileSize, gp.tileSize);
        valordedef =2;
        descripcion = "[" +nombre+"]\nun brillante escudo \nazul";
    }
}
