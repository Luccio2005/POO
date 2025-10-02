package entidad;
import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;
import Main.Teclado;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class jugador extends entidad{
    Teclado keyH;

    public final int pantallax;
    public final int pantallay;
    //public int tenerllave =0;

    public jugador(Panel_de_Juego gp, Teclado keyH){
        super(gp);
        this.keyH = keyH;

        pantallax = gp.anchoPantalla/2 - (gp.tileSize/2);
        pantallay = gp.altoPantalla/2 - (gp.tileSize/2);

        areadecolision = new Rectangle();
        areadecolision.x = 20;
        areadecolision.y = 20;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
        areadecolision.width=8;
        areadecolision.height=8;

        valorespredeterminados();
        getPlayerImage();

    }
    public void valorespredeterminados(){
        mundox= gp.tileSize * 23;
        mundoy= gp.tileSize * 21;
        velocidad= 4;
        direccion= "down";
        //estado jugador
        vidamax =6;
        vida = vidamax;
    }
    public void getPlayerImage(){

        up1= setup("/jugador/Arriba1-1");
        up2= setup("/jugador/Arriba1-2");
        down1= setup("/jugador/Abajo1-1");
        down2= setup("/jugador/Abajo1-2");
        left1= setup("/jugador/Izquierda1-1");
        left2= setup("/jugador/Izquierda1-2");
        right1= setup("/jugador/Derecha1-1");
        right2= setup("/jugador/Derecha1-2");
    }

    public void actualizar(){
        if(keyH.arribap == true || keyH.abajop ==true
                || keyH.izquierdap == true || keyH.derechap == true){
            if(keyH.arribap == true){
                direccion= "up";
            }
            else if(keyH.abajop == true){
                direccion="down";
            }
            else if(keyH.izquierdap == true){
                direccion="left";
            }
            else if(keyH.derechap == true){
                direccion="right";
            }
            colision = false;
            gp.comprobar.comprobarsuelo(this);

            int objindice= gp.comprobar.comprobarobjeto(this,true);
            recogerobjeto(objindice);

            // comprobar la colision del npc
            int indicenpc = gp.comprobar.comprobarentidad(this, gp.npc);
            interactuarnpc(indicenpc);
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
    }
    public void recogerobjeto (int i){
        if(i != 999){
        }
    }
    public void interactuarnpc(int i){
        if(i != 999){
            if(gp.keyH.enterp == true){
                gp.estadodeljuego = gp.dialogo;
                gp.npc[i].hablar();
            }
        }
        gp.keyH.enterp = false;
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
        g2.drawImage(imagen,pantallax,pantallay, null);
        //g2.setColor(Color.red);
        //g2.drawRect(pantallax+ areadecolision.x, pantallay+areadecolision.y, areadecolision.width,areadecolision.height);
    }
}
