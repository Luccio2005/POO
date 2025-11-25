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

    public iluminacion(Panel_de_Juego gp){
        this.gp = gp;
        setlightsource();
    }
    public void setlightsource(){
        // create a buffered image
        filtrodeoscuridad = new BufferedImage(gp.anchoPantalla, gp.altoPantalla, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) filtrodeoscuridad.getGraphics();

        if(gp.jugador.actualluz == null){
            g2.setColor(new Color(0,0,0,0.98f));
        }else{
            // consigue el centro x y y del ciculode luz
            int centrox = gp.jugador.pantallax + (gp.tileSize)/2;
            int centroy = gp.jugador.pantallay + (gp.tileSize)/2;
            // crear una degradaccion de colores
            Color color[] = new Color[12];
            float fraccion[] = new float[12];

            color[0] = new Color(0,0,0,0.1f);
            color[1] = new Color(0,0,0,0.42f);
            color[2] = new Color(0,0,0,0.52f);
            color[3] = new Color(0,0,0,0.61f);
            color[4] = new Color(0,0,0,0.69f);
            color[5] = new Color(0,0,0,0.76f);
            color[6] = new Color(0,0,0,0.82f);
            color[7] = new Color(0,0,0,0.87f);
            color[8] = new Color(0,0,0,0.91f);
            color[9] = new Color(0,0,0,0.94f);
            color[10] = new Color(0,0,0,0.96f);
            color[11] = new Color(0,0,0,0.98f);

            fraccion[0] = 0f;
            fraccion[1] = 0.4f;
            fraccion[2] = 0.5f;
            fraccion[3] = 0.6f;
            fraccion[4] = 0.65f;
            fraccion[5] = 0.7f;
            fraccion[6] = 0.75f;
            fraccion[7] = 0.8f;
            fraccion[8] = 0.85f;
            fraccion[9] = 0.9f;
            fraccion[10] = 0.95f;
            fraccion[11] = 1f;

            RadialGradientPaint gpaint = new RadialGradientPaint(centrox, centroy, gp.jugador.actualluz.radiosdeluz, fraccion, color);
            g2.setPaint(gpaint);
        }
        g2.fillRect(0, 0, gp.anchoPantalla, gp.altoPantalla);
        g2.dispose();
    }
    public void actualizar(){
        if(gp.jugador.luzactualizada == true){
            setlightsource();
            gp.jugador.luzactualizada = false;
        }
    }
    public void dibujar(Graphics2D g2){
        g2.drawImage(filtrodeoscuridad, 0, 0, null);
    }
}
