package entidad;
import Main.Panel_de_Juego;
import Main.Teclado;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class jugador extends entidad{
    Panel_de_Juego gp;
    Teclado keyH;

    public jugador(Panel_de_Juego gp, Teclado keyH){
        this.gp =gp;
        this.keyH = keyH;

        valorespredeterminados();
        getPlayerImage();

    }
    public void valorespredeterminados(){
        x= 100;
        y=100;
        velocidad= 4;
        direccion= "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/jugador/Arriba1-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/jugador/Arriba1-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/jugador/Abajo1-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/jugador/Abajo1-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/jugador/Izquierda1-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/jugador/Izquierda1-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/jugador/Derecha1-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/jugador/Derecha1-2.png"));


        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void actualizar(){
        if(keyH.arribap == true){
            direccion= "up";
            y -= velocidad;
        }
        else if(keyH.abajop == true){
            direccion="down";
            y += velocidad;
        }
        else if(keyH.izquierdap == true){
            direccion="left";
            x -= velocidad;
        }
        else if(keyH.derechap == true){
            direccion="right";
            x += velocidad;
        }
    }
    public void dibujar(Graphics2D g2){
       // g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage imagen=null;
        switch (direccion){
            case "up":
                imagen = up1; break;
            case "down":
                imagen = down1; break;
            case "left":
                imagen = left1; break;
            case "right":
                imagen = right1; break;
        }
    }
}
