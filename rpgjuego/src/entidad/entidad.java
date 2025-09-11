package entidad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entidad {
    public int mundox,mundoy;
    public int velocidad;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direccion;
    public int contadorSprite=0;
    public int numeroSprite=1;

    public Rectangle areadecolision;
    public int areadecolisionx, areadecolisiony;
    public boolean colision = false;
}
