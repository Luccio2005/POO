package suelo;

import Main.Panel_de_Juego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class mapa extends administradordesuelo{
    Panel_de_Juego gp;
    BufferedImage mundomapa[];
    public boolean minimapaon = false;

    public mapa(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        crearmapamundo();
    }
    public void crearmapamundo(){
        mundomapa = new BufferedImage[gp.maxmap];
        int mapamundoancho = gp.tileSize * gp.maxWorldCol;
        int mapamundoalto = gp.tileSize * gp.maxWorldRow;

        for(int i = 0; i < gp.maxmap; i++){
            mundomapa[i] = new BufferedImage(mapamundoancho, mapamundoalto, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) mundomapa[i].createGraphics();
            int col = 0;
            int fila = 0;
            while (col < gp.maxWorldCol && fila < gp.maxWorldRow){
                int suelonum = mapaNum[i][col][fila];
                int x = gp.tileSize * col;
                int y = gp.tileSize * fila;
                g2.drawImage(suelo[suelonum].imagen, x, y, null);
                col++;
                if(col == gp.maxWorldCol){
                    col = 0;
                    fila++;
                }
            }
        }
    }
    public void dibujarfullpantallademapa(Graphics2D g2){
        // background color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.anchoPantalla, gp.altoPantalla);
        // dibujar mapa
        int ancho = 500;
        int alto = 500;
        int x = gp.anchoPantalla/2 - ancho/2;
        int y = gp.altoPantalla/2 - alto/2;
        g2.drawImage(mundomapa[gp.actualmapa], x, y, ancho, alto, null);
    }
}
