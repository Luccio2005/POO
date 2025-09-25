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
    public  int bloqueodeaccion =0;

    public entidad(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setaction(){

    }
    public void actualizar(){
        setaction();
        colision = false;
        gp.comprobar.comprobarsuelo(this);
        gp.comprobar.comprobarobjeto(this, false);
        gp.comprobar.comprobarjugador(this);

        if(colision == false){
            switch (direccion){
                case "up":
                    mundoy -= velocidad;
                    break;
                case "down":
                    mundoy += velocidad;
                    break;
                case "left":
                    mundox -= velocidad;
                    break;
                case "right":
                    mundox += velocidad;
                    break;
            }
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
    public void dibujar(Graphics2D g2){
        BufferedImage imagen=null;
        int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
        int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

        if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
                mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
                mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
                mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){
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
            g2.drawImage(imagen, pantallax, pantallay, gp.tileSize, gp.tileSize,null);
        }
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
