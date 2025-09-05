package suelo;

import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class administradordesuelo {
    Panel_de_Juego gp;
    suelo[] suelo;
    int mapaNum[][];

    public administradordesuelo(Panel_de_Juego gp){
        this.gp = gp;

        suelo = new suelo[10];
        mapaNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        cargarMapa("/mapas/world01.txt");

    }
    public void getTileImage(){
        try{
            suelo[0] = new suelo();
            suelo[0].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/grass00.png"));

            suelo[1] = new suelo();
            suelo[1].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/wall.png"));

            suelo[2] = new suelo();
            suelo[2].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/water01.png"));

            suelo[3] = new suelo();
            suelo[3].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/earth.png"));

            suelo[4] = new suelo();
            suelo[4].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/tree.png"));

            suelo[5] = new suelo();
            suelo[5].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/road00.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cargarMapa(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int columna=0;
            int fila=0;
            while (columna< gp.maxWorldCol && fila < gp.maxWorldRow){
                String linea = br.readLine();
                while (columna < gp.maxWorldCol){
                    String numeros[] = linea.split(" ");
                    int num = Integer.parseInt(numeros[columna]);
                    mapaNum[columna][fila]=num;
                    columna++;
                }
                if(columna == gp.maxWorldCol){
                    columna=0;
                    fila++;
                }
            }
            br.close();;
        }catch (Exception e){

        }
    }
    public void dibujar(Graphics2D g2){
        int columnammundo=0;
        int filamundo=0;

        while(columnammundo < gp.maxWorldCol  && filamundo < gp.maxWorldRow){
            int tileNum = mapaNum[columnammundo][filamundo];

            int mundox = columnammundo * gp.tileSize;
            int mundoy = filamundo * gp.tileSize;
            int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
            int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

            if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
               mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
               mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
               mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){
                g2.drawImage(suelo[tileNum].imagen, pantallax, pantallay, gp.tileSize, gp.tileSize,null);
            }

            columnammundo++;

            if(columnammundo == gp.maxWorldCol){
                columnammundo=0;
                filamundo++;
            }
        }


    }

}
