package entidad;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class entidad {
    Panel_de_Juego gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage atqarriba1, atqarriba2, atqabajo1, atqabajo2, atqizq1,atqizq2, atqder1, atqder2;
    public BufferedImage imagen, imagen2, imagen3;
    public Rectangle areadecolision = new Rectangle(0,0,48,48);
    public Rectangle areadeataque = new Rectangle(0,0,0,0);
    public int areadecolisionx, areadecolisiony;
    public boolean colision = false;
    String dialogos[]= new String[20];
    //STATE
    public int mundox,mundoy;
    public String direccion = "down";
    public int numeroSprite=1;
    int indicededialogos = 0;

    public boolean invencible = false;
    boolean atacando = false;
    //CONTADOR
    public int contadorSprite=0;
    public int bloqueodeaccion =0;
    public int contadorinvencible = 0;
    // ATRIBUTOS DE LOS PERSONAJES
    public int tipo;
    public String nombre;
    public int velocidad;
    public int vidamax;
    public int vida;

    public entidad(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setaction(){
    }
    public void hablar(){
        if(dialogos[indicededialogos] == null){
            indicededialogos = 0;
        }
        gp.ui.dialogoactual = dialogos[indicededialogos];
        indicededialogos++;
        switch (gp.jugador.direccion){
            case "up":
                direccion = "down"; break;
            case "down":
                direccion = "up"; break;
            case "left":
                direccion = "right"; break;
            case "right":
                direccion = "left"; break;
        }
    }
    public void actualizar(){
        setaction();
        colision = false;
        gp.comprobar.comprobarsuelo(this);
        gp.comprobar.comprobarobjeto(this, false);
        gp.comprobar.comprobarentidad(this, gp.npc);
        gp.comprobar.comprobarentidad(this, gp.enemigos);
        boolean contactojugador = gp.comprobar.comprobarjugador(this);

        if(this.tipo == 2 && contactojugador == true){
            if(gp.jugador.invencible == false){
                gp.jugador.vida -= 1;
                gp.jugador.invencible = true;
            }
        }


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
        if(invencible == true){
            contadorinvencible ++;
            if(contadorinvencible > 40){
                invencible = false;
                contadorinvencible = 0;
            }
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
            if(invencible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            g2.drawImage(imagen, pantallax, pantallay, gp.tileSize, gp.tileSize,null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public BufferedImage setup(String nombreimagen, int ancho, int alto){
        Herramientasdeutilidad Herramienta = new Herramientasdeutilidad();
        BufferedImage imagen = null;
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreimagen+".png"));
            imagen = Herramienta.Imagenescala(imagen, ancho, alto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}
