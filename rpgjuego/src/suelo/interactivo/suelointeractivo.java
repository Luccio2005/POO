package suelo.interactivo;
import entidad.entidad;
import Main.Panel_de_Juego;

public class suelointeractivo extends entidad{
    Panel_de_Juego gp;
    public boolean destructible = false;

    public suelointeractivo(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
    }
    public void actualizar(){

    }
}
