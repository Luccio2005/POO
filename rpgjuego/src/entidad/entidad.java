package entidad;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    public entidad attacker;
    //STATE
    public int mundox,mundoy;
    public String direccion = "down";
    public int numeroSprite=1;
    int indicededialogos = 0;
    public boolean colisioon = false;
    public boolean invencible = false;
    public boolean atacando = false;
    public boolean vivo = true;
    public boolean muriendo = false;
    boolean barrahpon = false;
    public boolean onpath = false;
    public boolean knockback = false;
    public String knockbackdireccion;
    //CONTADOR
    public int contadorSprite=0;
    public int bloqueodeaccion =0;
    public int contadorinvencible = 0;
    public int contadordisparodisponible = 0;
    int contadormuerte = 0;
    int contadorbarrahp = 0;
    int contadorknockback = 0;
    // ATRIBUTOS DE LOS PERSONAJES
    public String nombre;
    public int defaultspeed;
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
    public int motion1_duracion;
    public int motion2_duracion;
    public entidad actualarma;
    public entidad actualescudo;
    public entidad actualluz;
    public proyectiles proyectiles;
    //atributos de los items
    public ArrayList<entidad> inventario = new ArrayList<>();
    public final int maxtamanoinventario = 20;
    public int valor;
    public int valordeatq;
    public int valordedef;
    public String descripcion = "";
    public int usecost;
    public int precio;
    public int knockbackpower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int radiosdeluz;
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
    public final int tipo_obstaculo = 8;
    public final int tipo_luz = 9;

    public entidad(Panel_de_Juego gp){
        this.gp = gp;
    }
    public int getizqx(){
        return mundox + areadecolision.x;
    }
    public int getderx(){
        return mundox + areadecolision.x + areadecolision.width;
    }
    public int gettopy(){
        return mundoy + areadecolision.y;
    }
    public int getbottomy(){
        return mundoy + areadecolision.y + areadecolision.height;
    }
    public int getcol(){
        return (mundox + areadecolision.x)/gp.tileSize;
    }
    public int getfila(){
        return (mundoy + areadecolision.y)/gp.tileSize;
    }
    public int getdistanciax(entidad target){
        int distanciax = Math.abs(mundox - target.mundox);
        return distanciax;
    }
    public int getdistanciay(entidad target){
        int distanciay = Math.abs(mundoy - target.mundoy);
        return distanciay;
    }
    public int gettitulodistancia(entidad target){
        int titulodistancia = (getdistanciax(target) + getdistanciay(target))/gp.tileSize;
        return titulodistancia;
    }
    public int getmetacol(entidad target){
        int metacol = (target.mundox + target.areadecolision.x)/gp.tileSize;
        return metacol;
    }
    public int getmetafila(entidad target){
        int metafila = (target.mundoy + target.areadecolision.y)/gp.tileSize;
        return metafila;
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
    public void interactuar(){
    }
    public boolean usar(entidad entidad){
        return false;
    }
    public void checkdrop(){
    }
    public void dropitem(entidad droppeditem){
        for(int i = 0; i < gp.obj[1].length;i++){
            if(gp.obj[gp.actualmapa][i] == null){
                gp.obj[gp.actualmapa][i] = droppeditem;
                gp.obj[gp.actualmapa][i].mundox = mundox;
                gp.obj[gp.actualmapa][i].mundoy = mundoy;
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
    public void comprobarcolision(){
        colisioon = false;
        gp.comprobar.comprobarsuelo(this);
        gp.comprobar.comprobarobjeto(this, false);
        gp.comprobar.comprobarentidad(this, gp.npc);
        gp.comprobar.comprobarentidad(this, gp.enemigos);
        gp.comprobar.comprobarentidad(this, gp.itile);
        boolean contactojugador = gp.comprobar.comprobarjugador(this);

        if(this.tipo == tipo_enemigos && contactojugador == true){
            damageplayer(atq);
        }
    }
    public void actualizar(){
        if(knockback == true){
            contadorknockback++;
            switch (knockbackdireccion){
                case "up": mundoy -= velocidad; break;
                case "down": mundoy += velocidad; break;
                case "left": mundox -= velocidad; break;
                case "right": mundox += velocidad; break;
            }
            if(colisioon == true || contadorknockback>10){
                contadorknockback = 0;
                knockback = false;
                velocidad = defaultspeed;
            }else if(colisioon == false){

            }
            else if(atacando == true){
                atacando();
            }
        }else{
            setaction();
            comprobarcolision();
            if(colisioon == false){
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
            if(contadorSprite>24){
                if(numeroSprite ==1){
                    numeroSprite = 2;}
                else if(numeroSprite ==2){
                    numeroSprite=1;
                }
                contadorSprite=0;
            }
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
    public void checkattackornot(int tasa, int straight, int horizontal){
        boolean targetenrango = false;
        int disx = getdistanciax(gp.jugador);
        int disy = getdistanciay(gp.jugador);
        switch (direccion){
            case "up":
                if(gp.jugador.mundoy < mundoy && disy < straight && disx < horizontal){
                    targetenrango = true;
                } break;
            case "down":
                if(gp.jugador.mundoy > mundoy && disy < straight && disx < horizontal){
                    targetenrango = true;
                } break;
            case "left":
                if(gp.jugador.mundox < mundox && disx < straight && disy < horizontal){
                    targetenrango = true;
                } break;
            case "right":
                if(gp.jugador.mundox > mundox && disx < straight && disy < horizontal){
                    targetenrango = true;
                } break;
        }
        if(targetenrango == true){
            int i = new Random().nextInt(tasa);
            if(i == 0){
                atacando = true;
                numeroSprite = 1;
                contadorSprite = 0;
                contadordisparodisponible = 0;
            }
        }
    }
    public void checkshootornot(int tasa, int intervalodisparo){
        int i = new Random().nextInt(tasa);
        if(i == 0 && proyectiles.vivo == false && contadordisparodisponible == intervalodisparo){
            proyectiles.set(mundox, mundoy, direccion, true, this);
            //gp.listaproyectil.add(proyectiles);
            for(int ii = 0; ii< gp.proyectiles[1].length; ii++){
                if(gp.proyectiles[gp.actualmapa][ii] == null){
                    gp.proyectiles[gp.actualmapa][ii] = proyectiles;
                    break;
                }
            }
            contadordisparodisponible = 0;
        }
    }
    public void checkstartchasingornot(entidad target, int distancia, int tasa){
        if(gettitulodistancia(target) < distancia){
            int i= new Random().nextInt(tasa);
            if(i == 0){
                onpath = true;
            }
        }
    }
    public void checkstopchasingornot(entidad target, int distancia, int tasa){
        if(gettitulodistancia(target) > distancia){
            int i= new Random().nextInt(tasa);
            if(i == 0){
                onpath = false;
            }
        }
    }
    public void getrandomdireccion(){
        bloqueodeaccion ++;

        if(bloqueodeaccion ==120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i<=25){
                direccion = "up";
            } if(i>25 && i<=50){
                direccion = "down";
            } if(i>50 && i <=75){
                direccion = "left";
            } if(i>75 && i<=100){
                direccion = "right";
            }
            bloqueodeaccion = 0;
        }
    }
    public void atacando(){
        contadorSprite++;
        if(contadorSprite <=motion1_duracion){
            numeroSprite = 1;
        }if(contadorSprite >motion1_duracion && contadorSprite <=motion2_duracion){
            numeroSprite = 2;
            int actualmundox = mundox;
            int actualmundoy = mundoy;
            int areadecolisionancho = areadecolision.width;
            int areadecolisionalto = areadecolision.height;

            switch (direccion){
                case "up": mundoy -= areadeataque.height; break;
                case "down": mundoy += areadeataque.height; break;
                case "left": mundox -= areadeataque.width; break;
                case "right": mundox += areadeataque.width; break;
            }
            areadecolision.width = areadeataque.width;
            areadecolision.height = areadeataque.height;
            if(tipo == tipo_enemigos){
                if(gp.comprobar.comprobarjugador(this) == true){
                    damageplayer(atq);
                }
            }else{
                int indiceenemigo = gp.comprobar.comprobarentidad(this, gp.enemigos);
                gp.jugador.damageenemigo(indiceenemigo, this, atq, actualarma.knockbackpower);

                int indiceitile = gp.comprobar.comprobarentidad(this, gp.itile);
                gp.jugador.damagesuelointeractivo(indiceitile);

                int indiceproyectil = gp.comprobar.comprobarentidad(this, gp.proyectiles);
                gp.jugador.damageproyectil(indiceproyectil);
            }
            mundox = actualmundox;
            mundoy = actualmundoy;
            areadecolision.width = areadecolisionancho;
            areadecolision.height = areadecolisionalto;

        }if(contadorSprite > motion2_duracion){
            numeroSprite =1;
            contadorSprite = 0;
            atacando = false;
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
    public void setknockback(entidad target, entidad attacker, int knockbackpower){
        this.attacker = attacker;
        target.knockbackdireccion = attacker.direccion;
        target.velocidad = target.defaultspeed + knockbackpower;
        target.knockback = true;
    }
    public void dibujar(Graphics2D g2){
        BufferedImage imagen=null;
        int pantallax= mundox - gp.jugador.mundox + gp.jugador.pantallax;
        int pantallay= mundoy - gp.jugador.mundoy + gp.jugador.pantallay;

        if(mundox + gp.tileSize > gp.jugador.mundox - gp.jugador.pantallax &&
                mundox - gp.tileSize < gp.jugador.mundox + gp.jugador.pantallax &&
                mundoy + gp.tileSize > gp.jugador.mundoy - gp.jugador.pantallay &&
                mundoy - gp.tileSize < gp.jugador.mundoy + gp.jugador.pantallay){
            int temppantallax = pantallax;
            int temppantallay = pantallay;
            switch (direccion){
                case "up":
                    if(atacando == false){
                        if(numeroSprite ==1){
                            imagen = up1;
                        }if(numeroSprite ==2){
                            imagen = up2;
                        }
                    }if(atacando == true){
                    if(numeroSprite ==1){
                        imagen = atqarriba1;
                    }if(numeroSprite ==2){
                        imagen = atqarriba2;
                    }
                }
                    break;
                case "down":
                    if(atacando == false){
                        if(numeroSprite ==1){
                            imagen = down1;
                        }if(numeroSprite ==2){
                            imagen = down2;
                        }
                    }if(atacando == true){
                    if(numeroSprite ==1){
                        imagen = atqabajo1;
                    }if(numeroSprite ==2){
                        imagen = atqabajo2;
                    }
                }
                    break;
                case "left":
                    if(atacando == false){
                        if(numeroSprite ==1){
                            imagen = left1;
                        }if(numeroSprite ==2){
                            imagen = left2;
                        }
                    }if(atacando == true){
                    if(numeroSprite ==1){
                        imagen = atqizq1;
                    }if(numeroSprite ==2){
                        imagen = atqizq2;
                    }
                }
                    break;
                case "right":
                    if(atacando == false){
                        if(numeroSprite ==1){
                            imagen = right1;
                        }if(numeroSprite ==2){
                            imagen = right2;
                        }
                    }if(atacando == true){
                    if(numeroSprite ==1){
                        imagen = atqder1;
                    }if(numeroSprite ==2){
                        imagen = atqder2;
                    }
                }
                    break;
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
            g2.drawImage(imagen, temppantallax, temppantallay,null);
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
    public void buscarcamino(int metacol, int metafila){
        int startcol = (mundox + areadecolision.x)/gp.tileSize;
        int startfila = (mundoy + areadecolision.y)/gp.tileSize;
        gp.pfinder.setnode(startcol, startfila, metacol, metafila, this);

        if(gp.pfinder.buscar() == true){
            //next mundox & mundoy
            int sgtex = gp.pfinder.caminolista.get(0).col * gp.tileSize;
            int sgtey = gp.pfinder.caminolista.get(0).fila * gp.tileSize;
            // entidad area de colision posiciones
            int enizqx = mundox + areadecolision.x;
            int enderx = mundox + areadecolision.x + areadecolision.width;
            int entopy = mundoy + areadecolision.y;
            int enbottomy = mundoy + areadecolision.y + areadecolision.height;

            if(entopy > sgtey && enizqx >= sgtex && enderx < sgtex + gp.tileSize){
                direccion = "up";
            }
            else if(entopy < sgtey && enizqx >= sgtex && enderx < sgtex + gp.tileSize){
                direccion = "down";
            }
            else if(entopy >= sgtey && enbottomy < sgtey + gp.tileSize){
                // izq or der
                if(enizqx > sgtex){
                    direccion = "left";
                }
                if(enizqx < sgtex){
                    direccion = "right";
                }
            }
            else if(entopy > sgtey && enizqx > sgtex){
                // up or left
                direccion = "up";
                comprobarcolision();
                if(colisioon == true){
                    direccion = "left";
                }
            }
            else if(entopy > sgtey && enizqx < sgtex){
                // up or right
                direccion = "up";
                comprobarcolision();
                if(colisioon == true){
                    direccion = "right";
                }
            }
            else if(entopy < sgtey && enizqx > sgtex){
                // down or left
                direccion = "down";
                comprobarcolision();
                if(colisioon == true){
                    direccion = "left";
                }
            }
            else if(entopy < sgtey && enizqx < sgtex){
                // down or right
                direccion = "down";
                comprobarcolision();
                if(colisioon == true){
                    direccion = "right";
                }
            }
            //int nextcol = gp.pfinder.caminolista.get(0).col;
           // int nextfila = gp.pfinder.caminolista.get(0).fila;
            //if(nextcol == metacol && nextfila == metafila){
           //     onpath = false;
           // }
        }
    }
    public int getdetected(entidad usuario, entidad target[][], String targetname){
        int indice = 999;

        int nextmundox = usuario.getizqx();
        int nextmundoy = usuario.gettopy();

        switch (usuario.direccion){
            case "up": nextmundoy = usuario.gettopy()-1; break;
            case "down": nextmundoy = usuario.getbottomy()-1; break;
            case "left": nextmundox = usuario.getizqx()-1; break;
            case "right": nextmundox = usuario.getderx()-1; break;
        }
        int col = nextmundox/gp.tileSize;
        int fila = nextmundoy/gp.tileSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.actualmapa][i] != null){
                if(target[gp.actualmapa][i].getcol() == col &&
                target[gp.actualmapa][i].getfila() == fila &&
                target[gp.actualmapa][i].nombre.equals(targetname)){
                    indice = i;
                    break;
                }
            }
        }
        return indice;
    }
}
