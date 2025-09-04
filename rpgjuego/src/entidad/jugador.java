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

    public final int pantallax;
    public final int pantallay;

    public jugador(Panel_de_Juego gp, Teclado keyH){
        this.gp =gp;
        this.keyH = keyH;

        pantallax = gp.anchoPantalla/2 - (gp.tileSize/2);
        pantallay = gp.altoPantalla/2 - (gp.tileSize/2);

        valorespredeterminados();
        getPlayerImage();

    }
    public void valorespredeterminados(){
        mundox= gp.tileSize * 23;
        mundoy= gp.tileSize * 21;
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
        if(keyH.arribap == true || keyH.abajop ==true
                || keyH.izquierdap == true || keyH.derechap == true){
            if(keyH.arribap == true){
                direccion= "up";
                mundoy -= velocidad;
            }
            else if(keyH.abajop == true){
                direccion="down";
                mundoy += velocidad;
            }
            else if(keyH.izquierdap == true){
                direccion="left";
                mundox -= velocidad;
            }
            else if(keyH.derechap == true){
                direccion="right";
                mundox += velocidad;
            }
            contadorSprite++;
            if(contadorSprite>12){
                if(numeroSprite ==1){
                    numeroSprite = 2;}
                else if(numeroSprite ==2){
                    numeroSprite=1;
                }
                contadorSprite=0;
        }
        }
    }
    public void dibujar(Graphics2D g2){
       // g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage imagen=null;
        switch (direccion){
            case "up":
                if(numeroSprite ==1){
                    imagen = up1;
                }if(numeroSprite ==2){
                    imagen = up2;
            }
                 break;
            case "down":
                if(numeroSprite ==1){
                    imagen = down1;
                }if(numeroSprite ==2){
                    imagen = down2;
            } break;
            case "left":
                if(numeroSprite ==1){
                    imagen = left1;
                }if(numeroSprite ==2){
                    imagen = left2;
            } break;
            case "right":
                if(numeroSprite ==1){
                    imagen = right1;
                }if(numeroSprite ==2){
                    imagen = right2;
            } break;
        }
        g2.drawImage(imagen,pantallax,pantallay,gp.tileSize, gp.tileSize, null);
    }
}
