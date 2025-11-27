package objeto;
import entidad.entidad;
import Main.Panel_de_Juego;

public class Obj_tienda extends entidad{
    Panel_de_Juego gp;

    public Obj_tienda(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;

        tipo = tipo_consumible;
        nombre = "tienda";
        down1 = setup("/objetos/tent",gp.tileSize, gp.tileSize);
        descripcion = "[Tienda]\nPuedes dormir hasta \nel dia siguiente";
        precio = 300;
        stackable = true;
    }
    public boolean usar(entidad entidad){
        gp.estadodeljuego = gp.estadodormir;
        gp.playSE(14);
        gp.jugador.vida = gp.jugador.vidamax;
        gp.jugador.mana = gp.jugador.manamaximo;
        return true;
    }
}
