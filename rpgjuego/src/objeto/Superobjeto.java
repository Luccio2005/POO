package objeto;

import Main.Panel_de_Juego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Superobjeto {
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int mundox, mundoy;

    public void dibujar(Graphics2D g2, Panel_de_Juego gp){
        int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
        int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

        if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
                mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
                mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
                mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){
            g2.drawImage(imagen, pantallax, pantallay, gp.tileSize, gp.tileSize,null);
        }

    }

}
