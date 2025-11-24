package ambiente;

import Main.Panel_de_Juego;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class iluminacion {
    Panel_de_Juego gp;
    BufferedImage filtrodeoscuridad;

    public iluminacion(Panel_de_Juego gp, int tamanocirculo){
        // create a buffered image
        filtrodeoscuridad = new BufferedImage(gp.anchoPantalla, gp.altoPantalla, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) filtrodeoscuridad.getGraphics();
        // create a screenseized rectangle area
        Area pantallaarea = new Area(new Rectangle2D.Double(0,0,gp.anchoPantalla, gp.altoPantalla));
        // consigue el centro x y y del ciculode luz
        int centrox = gp.jugador.pantallax + (gp.tileSize)/2;
        int centroy = gp.jugador.pantallay + (gp.tileSize)/2;
        // consigue el top izq x y  y del ciculo
        double x = centrox - (tamanocirculo/2);
        double y = centroy - (tamanocirculo/2);
        // crear la forma de ciruclo para ilumnicacion
        Shape formacirculo = new Ellipse2D.Double(x, y, tamanocirculo, tamanocirculo);
        // crear el area del circulo
        Area areadelaluz = new Area(formacirculo);
        // subtract the light circle from the screen rectangle
        pantallaarea.subtract(areadelaluz);
        // set a color to draw to rectangle
        g2.setColor(new Color(0,0,0,0.95f));
        // draw the screen rectangle whitout the light circle re
        g2.fill(pantallaarea);
        g2.dispose();
    }
    public void dibujar(Graphics2D g2){
        g2.drawImage(filtrodeoscuridad, 0, 0, null);
    }
}
