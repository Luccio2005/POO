package suelo.interactivo;
import entidad.entidad;
import Main.Panel_de_Juego;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    public void dibujar(Graphics2D g2){
        int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
        int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

        if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
                mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
                mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
                mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){

            g2.drawImage(down1, pantallax, pantallay,null);
        }
    }
}
