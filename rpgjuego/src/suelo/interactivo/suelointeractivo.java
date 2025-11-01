package suelo.interactivo;
import entidad.entidad;
import Main.Panel_de_Juego;

public class suelointeractivo extends entidad{
    Panel_de_Juego gp;
    public boolean destructible = false;

    public suelointeractivo(Panel_de_Juego gp, int col, int fila){
        super(gp);
        this.gp = gp;
    }
    public boolean itemcorrecto(entidad entidad){
        boolean itemcorrecto = false;
        return itemcorrecto;
    }
    public void playSE(){
    }
    public suelointeractivo getformadestruida(){
        suelointeractivo suelo = null;
        return suelo;
    }
    public void actualizar(){
        if(invencible == true){
            contadorinvencible++;
            if(contadorinvencible > 20){
                invencible = false;
                contadorinvencible = 0;
            }
        }
    }
}
