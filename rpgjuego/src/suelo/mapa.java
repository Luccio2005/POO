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
        //mundomapa = new BufferedImage[gp.maxmap];
        mundomapa = new BufferedImage[gp.maxmap];
        int mapamundoancho = gp.tileSize * gp.maxWorldCol;
        int mapamundoalto = gp.tileSize * gp.maxWorldRow;

        int i = gp.actualmapa;
        mundomapa[i] = new BufferedImage(mapamundoancho, mapamundoalto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = mundomapa[i].createGraphics();
// dibuja SOLO el mapa actual
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int fila = 0; fila < gp.maxWorldRow; fila++) {
                int suelonum = mapaNum[i][col][fila];
                int x = gp.tileSize * col;
                int y = gp.tileSize * fila;
                g2.drawImage(suelo[suelonum].imagen, x, y, null);
            }
        }
        g2.dispose();

        //int mapamundoancho = gp.tileSize * gp.maxWorldCol;
        //int mapamundoalto = gp.tileSize * gp.maxWorldRow;

       // for(int i = 0; i < gp.maxmap; i++){
           // mundomapa[i] = new BufferedImage(mapamundoancho, mapamundoalto, BufferedImage.TYPE_INT_ARGB);
           // Graphics2D g2 = (Graphics2D) mundomapa[i].createGraphics();
           // int col = 0;
           // int fila = 0;
          //  while (col < gp.maxWorldCol && fila < gp.maxWorldRow){
             //   int suelonum = mapaNum[i][col][fila];
            //    int x = gp.tileSize * col;
            //    int y = gp.tileSize * fila;
             //   g2.drawImage(suelo[suelonum].imagen, x, y, null);
             //   col++;
              //  if(col == gp.maxWorldCol){
             //       col = 0;
              //      fila++;
              //  }
         //   }
         //   g2.dispose();

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
        // dibujar jugador
        //double escala = (double) (gp.tileSize + gp.maxWorldCol)/ancho;
        int mundoAncho = gp.maxWorldCol * gp.tileSize;
        int mundoAlto  = gp.maxWorldRow * gp.tileSize;

        double escalaX = (double) mundoAncho / ancho;
        double escalaY = (double) mundoAlto  / alto;

// en mapas cuadrados son iguales, pero lo dejamos correcto por si acaso
        double escala = Math.max(escalaX, escalaY);

        int jugadorx = (int) (x + gp.jugador.mundox/escala);
        int jugadory = (int) (y + gp.jugador.mundoy/escala);
        int jugadortamano = (int) (gp.tileSize/3);
        g2.drawImage(gp.jugador.down1, jugadorx, jugadory, jugadortamano, jugadortamano, null);
        // hint
        g2.setFont(gp.ui.arial_80B.deriveFont(32f));
        g2.setColor(Color.white);
        g2.drawString("Presiona M", 750,  550);
    }
    public void dibujarminimapa(Graphics2D g2){
        if(minimapaon == true){

            // dimensiones en pantalla del minimapa
            int ancho = 200;
            int alto = 200;
            int x = gp.anchoPantalla - ancho - 50;
            int y = 50;

            // usar el minimapa precargado del mundo actual
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(mundomapa[gp.actualmapa], x, y, ancho, alto, null);

            // ---------------------------------------------
            // OBTENER TAMAÑO REAL DEL MAPA ACTUAL DINÁMICO
            // ---------------------------------------------
            int cols = gp.sueloM.mapaNum[gp.actualmapa].length;
            int rows = gp.sueloM.mapaNum[gp.actualmapa][0].length;

            int mundoAncho = cols * gp.tileSize;
            int mundoAlto  = rows * gp.tileSize;

            // ---------------------------------------------
            // CÁLCULO DE ESCALA
            // ---------------------------------------------
            double escalaX = (double) mundoAncho / ancho;
            double escalaY = (double) mundoAlto  / alto;
            double escala = Math.max(escalaX, escalaY);

            // ---------------------------------------------
            // POSICIÓN DEL JUGADOR EN EL MINIMAPA
            // ---------------------------------------------
            int jugadorx = (int) (x + gp.jugador.mundox / escala);
            int jugadory = (int) (y + gp.jugador.mundoy / escala);

            int jugadortamano = (int) (gp.tileSize / escala * 3); // ajustado para verse bien

            g2.drawImage(gp.jugador.down1, jugadorx - 6, jugadory - 6, jugadortamano, jugadortamano, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

}
