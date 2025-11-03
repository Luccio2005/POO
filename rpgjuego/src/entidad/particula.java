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
}
