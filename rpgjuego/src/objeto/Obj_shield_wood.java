package objeto;
import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_shield_wood extends entidad{
    public Obj_shield_wood(Panel_de_Juego gp){
        super(gp);
        nombre = "escudo de madera";
        down1 = setup("/objetos/shield_wood", gp.tileSize, gp.tileSize);
        valordedef =1;
    }
}
