package objeto;
import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_potion extends entidad{
    Panel_de_Juego gp;
    public Obj_potion(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        tipo = tipo_consumible;
        nombre = "pocion roja";
        valor = 5;
        down1 = setup("/objetos/potion_red",gp.tileSize, gp.tileSize);
        descripcion = "[Pocion Roja]\n Cura tu vida por "+valor+".";
        precio = 25;
    }
    public boolean usar(entidad entidad){
        gp.estadodeljuego = gp.dialogo;
        gp.ui.dialogoactual = "Tomas la "+nombre+"!\n"+"tu vida es recuperada en "+valor+".";
        entidad.vida += valor;
        gp.playSE(2);
        return true;
    }
}
