package entidad;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class entidad {
    Panel_de_Juego gp;
    public int mundox,mundoy;
    public int velocidad;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direccion;
    public int contadorSprite=0;
    public int numeroSprite=1;

    public Rectangle areadecolision = new Rectangle(0,0,48,48);
    public int areadecolisionx, areadecolisiony;
    public boolean colision = false;

    public entidad(Panel_de_Juego gp){
        this.gp = gp;
    }

    public BufferedImage setup(String nombreimagen){
        Herramientasdeutilidad Herramienta = new Herramientasdeutilidad();
        BufferedImage imagen = null;
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreimagen+".png"));
            imagen = Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}
