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
        // crear una degradaccion de colores
        Color color[] = new Color[5];
        float fraccion[] = new float[5];

        color[0] = new Color(0,0,0,0f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.75f);
        color[4] = new Color(0,0,0,0.98f);

        fraccion[0] = 0f;
        fraccion[1] = 0.25f;
        fraccion[2] = 0.5f;
        fraccion[3] = 0.75f;
        fraccion[4] = 1f;

        RadialGradientPaint gpaint = new RadialGradientPaint(centrox, centroy, (tamanocirculo/2), fraccion, color);
        g2.setPaint(gpaint);
        g2.fill(areadelaluz);

        // set a color to draw to rectangle
        //g2.setColor(new Color(0,0,0,0.95f));
        // draw the screen rectangle whitout the light circle re
        g2.fill(pantallaarea);
        g2.dispose();
    }
    public void dibujar(Graphics2D g2){
        g2.drawImage(filtrodeoscuridad, 0, 0, null);
    }
}
