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
        mapaNum = new int[gp.tamanoColumna][gp.tamanoFila];
        getTileImage();
        cargarMapa("/mapas/mapa01.txt");

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
            while (columna< gp.tamanoColumna && fila < gp.tamanoFila){
                String linea = br.readLine();
                while (columna < gp.tamanoColumna){
                    String numeros[] = linea.split(" ");
                    int num = Integer.parseInt(numeros[columna]);
                    mapaNum[columna][fila]=num;
                    columna++;
                }
                if(columna == gp.tamanoColumna){
                    columna=0;
                    fila++;
                }
            }
            br.close();;
        }catch (Exception e){

        }
    }
    public void dibujar(Graphics2D g2){
        int columna=0;
        int fila=0;
        int x=0;
        int y=0;
        while(columna < gp.tamanoColumna  && fila < gp.tamanoFila){

            int tileNum = mapaNum[columna][fila];
            g2.drawImage(suelo[tileNum].imagen, x, y, gp.tileSize, gp.tileSize,null);
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
