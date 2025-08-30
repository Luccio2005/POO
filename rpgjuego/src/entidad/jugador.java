package entidad;
import Main.Panel_de_Juego;
import Main.Teclado;
import Main.Panel_de_Juego;

import java.awt.*;

public class jugador extends entidad{
    Panel_de_Juego gp;
    Teclado keyH;

    public jugador(Panel_de_Juego gp, Teclado keyH){
        this.gp =gp;
        this.keyH = keyH;

        valorespredeterminados();

    }
    public void valorespredeterminados(){
        x= 100;
        y=100;
        velocidad= 4;
    }
    public void actualizar(){
        if(keyH.arribap == true){
            y -= velocidad;
        }
        else if(keyH.abajop == true){
            y += velocidad;
        }
        else if(keyH.izquierdap == true){
            x -= velocidad;
        }
        else if(keyH.derechap == true){
            x += velocidad;
        }
    }
    public void dibujar(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
