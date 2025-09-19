package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Herramientasdeutilidad {
    public BufferedImage  Imagenescala(BufferedImage original, int ancho, int altura){
        BufferedImage imagenescalada = new BufferedImage(ancho,altura,original.getType());
        Graphics2D g2= imagenescalada.createGraphics();
        g2.drawImage(original,0,0,ancho,altura,null);
        g2.dispose();

        return imagenescalada;
    }
}
