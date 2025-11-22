package entidad;
import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;
import Main.Teclado;
import objeto.*;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class jugador extends entidad{
    Teclado keyH;

    public final int pantallax;
    public final int pantallay;
    //public int tenerllave =0;
    public boolean cancelaratq = false;

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

        //areadeataque.width = 20;
        //areadeataque.height = 20;

        valorespredeterminados();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void valorespredeterminados(){
        mundox= gp.tileSize * 23;
        mundoy= gp.tileSize * 21;
        defaultspeed =4;
        velocidad= defaultspeed;
        direccion= "down";
        //estado jugador
        lvl = 1;
        vidamax =6;
        vida = vidamax;
        manamaximo = 4;
        mana = manamaximo;
        municion = 10;
        str = 1; // mas str mas dano ocasiona
        dex = 1; // mas dex menos dano recibe
        exp = 0;
        nextlvlexp = 5;
        coin = 500;
        actualarma = new Obj_sword_normal(gp);
        actualescudo = new Obj_shield_wood(gp);
        proyectiles = new Obj_fireball(gp);
        //proyectiles = new Obj_rock(gp);
        atq = getAttack(); // el valor de atq es decidido por str con elarma
        def = getDefense(); // el valor de def es decidido por dex con el escudo
    }
    public void setdefaultpositions(){
        mundox= gp.tileSize * 23;
        mundoy= gp.tileSize * 21;
        direccion= "down";
    }
    public void restaurarvidaymana(){
        vida = vidamax;
        mana = manamaximo;
        invencible = false;
    }
    public void setItems(){
        inventario.clear();
        inventario.add(actualarma);
        inventario.add(actualescudo);
        inventario.add(new Obj_key(gp));
    }
    public int getAttack(){
        areadeataque = actualarma.areadeataque;
        return atq = str*actualarma.valordeatq;
    }
    public int getDefense(){
        return def = dex*actualescudo.valordedef;
    }
    public void getPlayerImage(){

        up1= setup("/jugador/Arriba1-1", gp.tileSize, gp.tileSize);
        up2= setup("/jugador/Arriba1-2", gp.tileSize, gp.tileSize);
        down1= setup("/jugador/Abajo1-1", gp.tileSize, gp.tileSize);
        down2= setup("/jugador/Abajo1-2", gp.tileSize, gp.tileSize);
        left1= setup("/jugador/Izquierda1-1", gp.tileSize, gp.tileSize);
        left2= setup("/jugador/Izquierda1-2", gp.tileSize, gp.tileSize);
        right1= setup("/jugador/Derecha1-1", gp.tileSize, gp.tileSize);
        right2= setup("/jugador/Derecha1-2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        if(actualarma.tipo == tipo_espada){
            atqarriba1 = setup("/jugador/ataquearriba1", gp.tileSize, gp.tileSize);
            atqarriba2 = setup("/jugador/ataquearriba2", gp.tileSize, gp.tileSize);
            atqabajo1 = setup("/jugador/ataqueabajo1", gp.tileSize, gp.tileSize);
            atqabajo2 = setup("/jugador/ataqueabajo2", gp.tileSize, gp.tileSize);
            atqizq1 = setup("/jugador/ataqueizq1", gp.tileSize, gp.tileSize);
            atqizq2 = setup("/jugador/ataqueizq2", gp.tileSize, gp.tileSize);
            atqder1 = setup("/jugador/ataqueder1", gp.tileSize, gp.tileSize);
            atqder2 = setup("/jugador/ataqueder2", gp.tileSize, gp.tileSize);
        }if(actualarma.tipo == tipo_hacha){
            atqarriba1 = setup("/jugador/hachaarriba-1", gp.tileSize, gp.tileSize);
            atqarriba2 = setup("/jugador/hachaarriba-2", gp.tileSize, gp.tileSize);
            atqabajo1 = setup("/jugador/hachaabajo-1", gp.tileSize, gp.tileSize);
            atqabajo2 = setup("/jugador/hachaabajo-2", gp.tileSize, gp.tileSize);
            atqizq1 = setup("/jugador/hachaizq-1", gp.tileSize, gp.tileSize);
            atqizq2 = setup("/jugador/hachaizq-2", gp.tileSize, gp.tileSize);
            atqder1 = setup("/jugador/hachader-1", gp.tileSize, gp.tileSize);
            atqder2 = setup("/jugador/hachader-2", gp.tileSize, gp.tileSize);
        }


    }
    public void actualizar(){
        if(atacando == true){
            atacando();
        }
        else if(keyH.arribap == true || keyH.abajop ==true
                || keyH.izquierdap == true || keyH.derechap == true ||keyH.enterp == true){
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
            // comprobar colosiion de suelos
            colisioon = false;
            gp.comprobar.comprobarsuelo(this);

            // comprobar colision de objetos
            int objindice= gp.comprobar.comprobarobjeto(this,true);
            recogerobjeto(objindice);

            // comprobar la colision del npc
            int indicenpc = gp.comprobar.comprobarentidad(this, gp.npc);
            interactuarnpc(indicenpc);

            //comprobar colision con enemigos
            int indiceenemigos = gp.comprobar.comprobarentidad(this, gp.enemigos);
            contactoenemigo(indiceenemigos);

            // comprobar colision suelo interactivo
            int indiceitile = gp.comprobar.comprobarentidad(this, gp.itile);

            //combrobar evento
            gp.evento.comprobarevento();

            if(colisioon == false && keyH.enterp == false){
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
            if(keyH.enterp == true && cancelaratq == false){
                gp.playSE(7);
                atacando = true;
                contadorSprite = 0;
            }
            cancelaratq = false;
            gp.keyH.enterp = false;
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
        if(gp.keyH.disparop == true && proyectiles.vivo == false
                && contadordisparodisponible == 30 && proyectiles.haveresource(this) == true){
            //set default coordinates, direccion
            proyectiles.set(mundox, mundoy, direccion, true, this);
            //subtract the cost(mana, ammo, etc)
            proyectiles.subtractresource(this);
            // add it to the list

            for(int i = 0; i < gp.proyectiles[1].length; i++){
                if(gp.proyectiles[gp.actualmapa][i] == null){
                    gp.proyectiles[gp.actualmapa][i] = proyectiles;
                    break;
                }
            }
            contadordisparodisponible =0;
            gp.playSE(10);
        }
        if(invencible == true){
            contadorinvencible ++;
            if(contadorinvencible > 60){
                invencible = false;
                contadorinvencible = 0;
            }
        }
        if(contadordisparodisponible < 30){
            contadordisparodisponible++;
        }
        if(vida > vidamax){
            vida = vidamax;
        }
        if(mana > manamaximo){
            mana = manamaximo;
        }
        if(vida <= 0){
            gp.estadodeljuego = gp.estadogameover;
            gp.ui.numerodecomando = -1;
            gp.stopMusic();;
            gp.playSE(12);
        }
    }
    public void atacando(){
        contadorSprite++;
        if(contadorSprite <=5){
            numeroSprite = 1;
        }if(contadorSprite >5 && contadorSprite <=25){
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

            int indiceenemigo = gp.comprobar.comprobarentidad(this, gp.enemigos);
            damageenemigo(indiceenemigo, atq, actualarma.knockbackpower);

            int indiceitile = gp.comprobar.comprobarentidad(this, gp.itile);
            damagesuelointeractivo(indiceitile);

            int indiceproyectil = gp.comprobar.comprobarentidad(this, gp.proyectiles);
            damageproyectil(indiceproyectil);

            mundox = actualmundox;
            mundoy = actualmundoy;
            areadecolision.width = areadecolisionancho;
            areadecolision.height = areadecolisionalto;

        }if(contadorSprite > 25){
            numeroSprite =1;
            contadorSprite = 0;
            atacando = false;
        }
    }
    public void recogerobjeto (int i){
        if(i != 999){
            // recoger solo items
            if(gp.obj[gp.actualmapa][i].tipo == tipo_agarrarsolo){
                gp.obj[gp.actualmapa][i].usar(this);
                gp.obj[gp.actualmapa][i] = null;
            }else{
                String texto;
                if(inventario.size() != maxtamanoinventario){
                    inventario.add(gp.obj[gp.actualmapa][i]);
                    gp.playSE(1);
                    texto = "Conseguiste una " + gp.obj[gp.actualmapa][i].nombre + "!";
                }else{
                    texto = "Inventario lleno";
                }
                gp.ui.anadirmensaje(texto);
                gp.obj[gp.actualmapa][i] = null;
            }
        }
    }
    public void interactuarnpc(int i) {
        if (gp.keyH.enterp == true) {
            if (i != 999) {
                cancelaratq = true;
                gp.estadodeljuego = gp.dialogo;
                gp.npc[gp.actualmapa][i].hablar();
            }
        }
    }
    public void contactoenemigo(int i){
        if(i !=999){
            if(invencible == false && gp.enemigos[gp.actualmapa][i].muriendo == false){
                gp.playSE(6);
                int damage = gp.enemigos[gp.actualmapa][i].atq - def;
                if(damage<0){
                    damage = 0;
                }
                vida -= damage;
                invencible = true;
            }
        }
    }
    public void damageenemigo(int i, int atq, int knockbackpower){
        if(i != 999){
            if(gp.enemigos[gp.actualmapa][i].invencible == false){
                gp.playSE(5);
                if(knockbackpower > 0){
                    knockback(gp.enemigos[gp.actualmapa][i], knockbackpower);
                }

                int damage = atq - gp.enemigos[gp.actualmapa][i].def;
                if(damage<0){
                    damage = 0;
                }
                gp.enemigos[gp.actualmapa][i].vida -= damage;
                gp.ui.anadirmensaje(damage + " damage!");
                gp.enemigos[gp.actualmapa][i].invencible = true;
                gp.enemigos[gp.actualmapa][i].reaccionaldamage();
                if(gp.enemigos[gp.actualmapa][i].vida <= 0){
                    gp.enemigos[gp.actualmapa][i].muriendo = true;
                    gp.ui.anadirmensaje("mato al " + gp.enemigos[gp.actualmapa][i].nombre + "!");
                    gp.ui.anadirmensaje("exp + " + gp.enemigos[gp.actualmapa][i].exp + "!");
                    exp += gp.enemigos[gp.actualmapa][i].exp;
                    comprobarlvlup();
                }
            }
        }
    }
    public void knockback(entidad entidad, int knockbackpower){
        entidad.direccion = direccion;
        entidad.velocidad += knockbackpower;
        entidad.knockback = true;
    }
    public void damagesuelointeractivo(int i){
        if(i != 999 && gp.itile[gp.actualmapa][i].destructible == true
                && gp.itile[gp.actualmapa][i].itemcorrecto(this) == true && gp.itile[gp.actualmapa][i].invencible == false){
            gp.itile[gp.actualmapa][i].playSE();
            gp.itile[gp.actualmapa][i].vida--;
            gp.itile[gp.actualmapa][i].invencible = true;
            //generador particula
            generadorparticula(gp.itile[gp.actualmapa][i], gp.itile[gp.actualmapa][i]);
            if(gp.itile[gp.actualmapa][i].vida == 0){
                gp.itile[gp.actualmapa][i] = gp.itile[gp.actualmapa][i].getformadestruida();
            }
        }
    }
    public void damageproyectil(int i){
        if(i != 999){
            entidad proyectiles = gp.proyectiles[gp.actualmapa][i];
            proyectiles.vivo = false;
            generadorparticula(proyectiles,proyectiles);
        }
    }
    public void comprobarlvlup(){
        if(exp >= nextlvlexp){
            lvl++;
            nextlvlexp = nextlvlexp*2;
            vidamax +=2;
            str++;
            dex++;
            atq = getAttack();
            def = getDefense();
            gp.playSE(8);
            gp.estadodeljuego = gp.dialogo;
            gp.ui.dialogoactual = "eres nivel " + lvl + " ahora!";
        }
    }
    public  void seleccionaritem(){
        int indiceitem = gp.ui.getitemindexonslot(gp.ui.jugadorranuracol, gp.ui.jugadorranurafila);
        if(indiceitem < inventario.size()){
            entidad itemseleccionado = inventario.get(indiceitem);
            if(itemseleccionado.tipo == tipo_espada || itemseleccionado.tipo == tipo_hacha){
                actualarma = itemseleccionado;
                atq = getAttack();
                getPlayerAttackImage();
            }
            if(itemseleccionado.tipo == tipo_escudo){
                actualescudo = itemseleccionado;
                def = getDefense();
            }
            if(itemseleccionado.tipo == tipo_consumible){
                itemseleccionado.usar(this);
                inventario.remove(indiceitem);
            }
        }
    }
    public void dibujar(Graphics2D g2){
       // g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage imagen=null;
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
        if(invencible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(imagen,pantallax,pantallay, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //debug
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invencible:"+contadorinvencible,10,400);
        //g2.setColor(Color.red);
        //g2.drawRect(pantallax+ areadecolision.x, pantallay+areadecolision.y, areadecolision.width,areadecolision.height);
    }
}
