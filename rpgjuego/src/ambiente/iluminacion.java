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
    int contadordia;
    float filtroalfa = 0f;

    final int dia = 0;
    final int anochecer = 1;
    final int noche = 2;
    final int amanecer = 3;
    int estadodia = dia;

    public iluminacion(Panel_de_Juego gp){
        this.gp = gp;
        setlightsource();
    }
    public void setlightsource(){
        // create a buffered image
        filtrodeoscuridad = new BufferedImage(gp.anchoPantalla, gp.altoPantalla, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) filtrodeoscuridad.getGraphics();

        if(gp.jugador.actualluz == null){
            g2.setColor(new Color(0,0,0.1f,0.98f));
        }else{
            // consigue el centro x y y del ciculode luz
            int centrox = gp.jugador.pantallax + (gp.tileSize)/2;
            int centroy = gp.jugador.pantallay + (gp.tileSize)/2;
            // crear una degradaccion de colores
            Color color[] = new Color[12];
            float fraccion[] = new float[12];

            color[0] = new Color(0,0,0.1f,0.1f);
            color[1] = new Color(0,0,0.1f,0.42f);
            color[2] = new Color(0,0,0.1f,0.52f);
            color[3] = new Color(0,0,0.1f,0.61f);
            color[4] = new Color(0,0,0.1f,0.69f);
            color[5] = new Color(0,0,0.1f,0.76f);
            color[6] = new Color(0,0,0.1f,0.82f);
            color[7] = new Color(0,0,0.1f,0.87f);
            color[8] = new Color(0,0,0.1f,0.91f);
            color[9] = new Color(0,0,0.1f,0.94f);
            color[10] = new Color(0,0,0.1f,0.96f);
            color[11] = new Color(0,0,0.1f,0.98f);

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
        // comprobar el estado del dia
        if(estadodia == dia){
            contadordia++;
            if(contadordia > 3600){
                estadodia = anochecer;
                contadordia = 0;
            }
        }
        if(estadodia == anochecer){
            filtroalfa += 0.001f;
            if(filtroalfa > 1f){
                filtroalfa = 1f;
                estadodia = noche;
            }
        }
        if(estadodia == noche){
            contadordia++;
            if(contadordia > 3600){
                estadodia = amanecer;
                contadordia = 0;
            }
        }
        if(estadodia == amanecer){
            filtroalfa -= 0.001f;
            if(filtroalfa < 0f){
                filtroalfa = 0;
                estadodia = dia;
            }
        }
    }
    public void dibujar(Graphics2D g2){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filtroalfa));
        g2.drawImage(filtrodeoscuridad, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //debug
        String situacion = "";
        switch (estadodia){
            case dia: situacion = "Dia"; break;
            case anochecer: situacion = "Dusk"; break;
            case noche: situacion = "Noche"; break;
            case amanecer: situacion = "Dawn"; break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situacion, 800, 500);
    }
}
