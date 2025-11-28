package suelo;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class administradordesuelo {
    Panel_de_Juego gp;
    public suelo[] suelo;
    public int mapaNum[][][];
    boolean dibujarcamino = true;
    //ArrayList<String> nombredoc = new ArrayList<>();
    //ArrayList<String> estatusdecolison = new ArrayList<>();

    public administradordesuelo(Panel_de_Juego gp){
        this.gp = gp;
        //leer doc
        //InputStream is = getClass().getResourceAsStream("/mapas/merry_christmas.txt");
        //BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // getting tile names and colision info from the file
        //String linea;
        //try{
            //while ((linea = br.readLine()) != null){
                //nombredoc.add(linea);
                //estatusdecolison.add(br.readLine());
            //}
            //br.close();
        //} catch (IOException e){
            //e.printStackTrace();
        //}
        //suelo = new suelo[nombredoc.size()];
       // getTileImage();

        suelo = new suelo[50];
        mapaNum = new int[gp.maxmap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        cargarMapa("/mapas/worldV3.txt",0);
        cargarMapa("/mapas/interior01.txt",1);
        cargarMapa("/mapas/cyclopolis.txt",2);
        cargarMapa("/mapas/worldV2.txt",3);

    }
    public void getTileImage(){
            setup(0,"grass00",false);
            setup(1,"wall",true);
            setup(2,"water01",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"road00",false);
            setup(6,"fueramundo",true);
            setup(7,"wall05",false );
            setup(8,"suelo05",false);
            setup(9,"Lava",true);

            setup(10,"grass00",false);
            setup(11,"grass01",false);
            setup(12,"water00",true);
            setup(13,"water01",true);
            setup(14,"water02",true);
            setup(15,"water03",true);
            setup(16,"water04",true);
            setup(17,"water05",true);
            setup(18,"water06",true);
            setup(19,"water07",true);
            setup(20,"water08",true);
            setup(21,"water09",true);
            setup(22,"water10",true);
            setup(23,"water11",true);
            setup(24,"water12",true);
            setup(25,"water13",true);
            setup(26,"road00",false);
            setup(27,"road01",false);
            setup(28,"road02",false);
            setup(29,"road03",false);
            setup(30,"road04",false);
            setup(31,"road05",false);
            setup(32,"road06",false);
            setup(33,"road07",false);
            setup(34,"road08",false);
            setup(35,"road09",false);
            setup(36,"road10",false);
            setup(37,"road11",false);
            setup(38,"road12",false);
            setup(39,"earth",false);
            setup(40,"wall",true);
            setup(41,"tree",true);
            setup(42,"hut",false);
            setup(43,"floor01",false);
            setup(44,"table01",true);
    }
    public void setup(int indice, String nombreimagen, boolean colision){
        Herramientasdeutilidad Herramienta = new Herramientasdeutilidad();

        try{
            suelo[indice] = new suelo();
            suelo[indice].imagen = ImageIO.read(getClass().getResourceAsStream("/suelo/"+nombreimagen+".png"));
            suelo[indice].imagen = Herramienta.Imagenescala(suelo[indice].imagen,gp.tileSize, gp.tileSize);
            suelo[indice].colision = colision;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void cargarMapa(String filePath, int map){
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
                    mapaNum[map][columna][fila]=num;
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
            int tileNum = mapaNum[gp.actualmapa][columnammundo][filamundo];

            int mundox = columnammundo * gp.tileSize;
            int mundoy = filamundo * gp.tileSize;
            int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
            int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

            if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
               mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
               mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
               mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){
                g2.drawImage(suelo[tileNum].imagen, pantallax, pantallay,null);
            }

            columnammundo++;

            if(columnammundo == gp.maxWorldCol){
                columnammundo=0;
                filamundo++;
            }
        }
        if(dibujarcamino == true){
            g2.setColor(new Color(255, 0, 0, 70));

            for(int i=0; i< gp.pfinder.caminolista.size(); i++){
                int mundox = gp.pfinder.caminolista.get(i).col * gp.tileSize;
                int mundoy = gp.pfinder.caminolista.get(i).fila * gp.tileSize;
                int pantallax = mundox - gp.jugador.mundox + gp.jugador.pantallax;
                int pantallay = mundoy - gp.jugador.mundoy + gp.jugador.pantallay;
                g2.fillRect(pantallax, pantallay, gp.tileSize, gp.tileSize);
            }
        }
    }
}
