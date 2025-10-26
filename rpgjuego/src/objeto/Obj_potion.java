package objeto;
import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_potion extends entidad{
    Panel_de_Juego gp;
    int valor = 5;
    public Obj_potion(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        tipo = tipo_consumible;
        nombre = "pocion roja";
        down1 = setup("/objetos/potion_red",gp.tileSize, gp.tileSize);
        descripcion = "[Pocion Roja]\n Cura tu vida por "+valor+".";
    }
    public void usar(entidad entidad){
        gp.estadodeljuego = gp.dialogo;
        gp.ui.dialogoactual = "Tomas la "+nombre+"!\n"+"tu vida es recuperada en "+valor+".";
        entidad.vida += valor;
        if(gp.jugador.vida > gp.jugador.vidamax){
            gp.jugador.vida = gp.jugador.vidamax;
        }
        gp.playSE(2);
    }
}
