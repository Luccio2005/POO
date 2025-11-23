package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
public class Obj_key extends entidad{

    Panel_de_Juego gp;

    public Obj_key(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;

        tipo = tipo_consumible;
        nombre="llave";
        down1 = setup("/objetos/key", gp.tileSize, gp.tileSize);
        descripcion = "[" +nombre+"]\nabre una puerta";
        precio = 100;
        stackable = true;
    }
    public boolean usar(entidad entidad){
        gp.estadodeljuego = gp.dialogo;
        int indiceobj = getdetected(entidad, gp.obj, "puerta");
        if(indiceobj != 999){
            gp.ui.dialogoactual = "Abriste la puerta";
            gp.playSE(3);
            gp.obj[gp.actualmapa][indiceobj] = null;
            return true;
        }else{
            gp.ui.dialogoactual = "Que haces?";
            return false;
        }
    }
}
