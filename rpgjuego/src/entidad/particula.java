package entidad;

import Main.Panel_de_Juego;
import java.awt.*;

public class particula extends entidad{
    entidad generador;
    Color color;
    int size;
    int xd;
    int yd;

    public particula(Panel_de_Juego gp, entidad generador, Color color, int size, int velocidad, int vidamax, int xd, int yd){
        super(gp);

        this.generador = generador;
        this.color = color;
        this.size = size;
        this.velocidad = velocidad;
        this.vidamax = vidamax;
        this.xd = xd;
        this.yd = yd;

        vida = vidamax;
        mundox = generador.mundox;
        mundoy = generador.mundoy;
    }
    public void actualizar(){
        vida--;

        mundox += xd*velocidad;
        mundoy += yd*velocidad;

        if(vida == 0){
            vivo = false;
        }
    }
    public void dibujar(Graphics2D g2){
        int pantallax = mundox - gp.jugador.mundox + gp.jugador.pantallax;
        int pantallay = mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

        g2.setColor(color);
        g2.fillRect(pantallax, pantallay, size, size);
    }
}
