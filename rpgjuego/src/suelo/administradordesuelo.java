package suelo;

import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;

public class administradordesuelo {
    Panel_de_Juego gp;
    suelo[] suelo;

    public administradordesuelo(Panel_de_Juego gp){
        this.gp = gp;

        suelo = new suelo[10];
        getTileImage();

    }
    public void getTileImage(){
        try{
            suelo[0] = new suelo();
            suelo[0].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/grass00.png"));

            suelo[1] = new suelo();
            suelo[1].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/wall.png"));

            suelo[2] = new suelo();
            suelo[2].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/water01.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dibujar(Graphics2D g2){
        int columna=0;
        int fila=0;
        int x=0;
        int y=0;
        while(columna < gp.tamanoColumna  && fila < gp.tamanoFila){
            g2.drawImage(suelo[0].imagen, x, y, gp.tileSize, gp.tileSize,null);
            columna++;
            x+= gp.tileSize;
            if(columna == gp.tamanoColumna){
                columna=0;
                x=0;
                fila++;
                y+= gp.tileSize;
            }
        }


    }

}
