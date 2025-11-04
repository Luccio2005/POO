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
    public boolean vivo = true;
    public boolean muriendo = false;
    boolean barrahpon = false;
    //CONTADOR
    public int contadorSprite=0;
    public int bloqueodeaccion =0;
    public int contadorinvencible = 0;
    public int contadordisparodisponible = 0;
    int contadormuerte = 0;
    int contadorbarrahp = 0;
    // ATRIBUTOS DE LOS PERSONAJES
    public String nombre;
    public int velocidad;
    public int vidamax;
    public int vida;
    public int manamaximo;
    public int mana;
    public int municion;
    public int lvl;
    public int str;
    public int dex;
    public int atq;
    public int def;
    public int exp;
    public int nextlvlexp;
    public int coin;
    public entidad actualarma;
    public entidad actualescudo;
    public proyectiles proyectiles;
    //atributos de los items
    public int valor;
    public int valordeatq;
    public int valordedef;
    public String descripcion = "";
    public int usecost;
    //tipo
    public int tipo;
    public final int tipo_jugador = 0;
    public final int tipo_npc = 1;
    public final int tipo_enemigos = 2;
    public final int tipo_espada = 3;
    public final int tipo_hacha = 4;
    public final int tipo_escudo = 5;
    public final int tipo_consumible = 6;
    public final int tipo_agarrarsolo = 7;

    public entidad(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setaction(){
    }
    public void reaccionaldamage(){

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
    public void usar(entidad entidad){
    }
    public void checkdrop(){
    }
    public void dropitem(entidad droppeditem){
        for(int i = 0; i < gp.obj.length;i++){
            if(gp.obj[i] == null){
                gp.obj[i] = droppeditem;
                gp.obj[i].mundox = mundox;
                gp.obj[i].mundoy = mundoy;
                break;
            }
        }
    }
    public Color getparticulacolor(){
        Color color = null;
        return color;
    }
    public int getparticulatamano(){
        int size = 0; //6pixeles
        return size;
    }
    public int getpaticulavelocidad(){
        int velocidad = 0;
        return velocidad;
    }
    public int getparticulavidamax(){
        int vidamax = 0;
        return vidamax;
    }
    public void generadorparticula(entidad generador, entidad target){
        Color color = generador.getparticulacolor();
        int size = generador.getparticulatamano();
        int velocidad = generador.getpaticulavelocidad();
        int vidamax = generador.getparticulavidamax();

        particula p1 = new particula(gp, target, color, size, velocidad, vidamax, -2, -1);
        particula p2 = new particula(gp, target, color, size, velocidad, vidamax, 2, -1);
        particula p3 = new particula(gp, target, color, size, velocidad, vidamax, -2, 1);
        particula p4 = new particula(gp, target, color, size, velocidad, vidamax, 2, 1);
        gp.listaparticula.add(p1);
        gp.listaparticula.add(p2);
        gp.listaparticula.add(p3);
        gp.listaparticula.add(p4);
    }
    public void actualizar(){
        setaction();
        colision = false;
        gp.comprobar.comprobarsuelo(this);
        gp.comprobar.comprobarobjeto(this, false);
        gp.comprobar.comprobarentidad(this, gp.npc);
        gp.comprobar.comprobarentidad(this, gp.enemigos);
        gp.comprobar.comprobarentidad(this, gp.itile);
        boolean contactojugador = gp.comprobar.comprobarjugador(this);

        if(this.tipo == tipo_enemigos && contactojugador == true){
            damageplayer(atq);
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
        if(contadordisparodisponible < 30){
            contadordisparodisponible++;
        }
    }
    public void damageplayer(int atq){
        if(gp.jugador.invencible == false){
            gp.playSE(6);
            int damage = atq - gp.jugador.def;
            if(damage<0){
                damage = 0;
            }
            gp.jugador.vida -= damage;
            gp.jugador.invencible = true;
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
            // barra de vida de enemigos
            if(tipo == 2 && barrahpon == true){
                double onescale = (double)gp.tileSize/vidamax;
                double valorbarrahp = onescale*vida;
                g2.setColor(new Color(35,35,35));
                g2.fillRect(pantallax-1,pantallay-16, gp.tileSize+2, 12);
                g2.setColor(new Color(255,0,30));
                g2.fillRect(pantallax, pantallay -15, (int)valorbarrahp, 10);
                contadorbarrahp++;

                if(contadorbarrahp > 600){
                    contadorbarrahp = 0;
                    barrahpon = false;
                }
            }
            if(invencible == true){
                barrahpon = true;
                contadorbarrahp = 0;
                changealpha(g2,0.4F);
            }
            if(muriendo == true){
                animacionmuerte(g2);
            }
            g2.drawImage(imagen, pantallax, pantallay,null);

            changealpha(g2,1F);
        }
    }
    public void animacionmuerte(Graphics2D g2){
        contadormuerte++;
        int i =5;
        if(contadormuerte <= i){
            changealpha(g2, 0f);
        }
        if(contadormuerte > i && contadormuerte <= i*2){
            changealpha(g2, 1f);
        }
        if(contadormuerte > i*2 && contadormuerte <= i*3){
            changealpha(g2, 0f);
        }
        if(contadormuerte > i*3 && contadormuerte <= i*4){
            changealpha(g2, 1f);
        }
        if(contadormuerte > i*4 && contadormuerte <= i*5){
            changealpha(g2, 0f);
        }
        if(contadormuerte > i*5 && contadormuerte <= i*6){
            changealpha(g2, 1f);
        }
        if(contadormuerte > i*6 && contadormuerte <= i*7){
            changealpha(g2, 0f);
        }
        if(contadormuerte > i*7 && contadormuerte <= i*8){
            changealpha(g2, 1f);
        }
        if(contadormuerte > i*8){
            vivo= false;
        }
    }
    public void changealpha(Graphics2D g2, float alphavalue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphavalue));
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
