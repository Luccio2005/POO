package Main;

import entidad.entidad;
import objeto.Obj_heart;
import objeto.Obj_manacrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    Panel_de_Juego gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full,crystal_blank;
    public boolean mensajeOn= false;
    //public String mensaje = "";
    //int mensajeContador = 0;
    ArrayList<String> mensaje = new ArrayList<>();
    ArrayList<Integer> contadormensaje = new ArrayList<>();
    public boolean juegoterminado = false;
    public String dialogoactual = "";
    public int numerodecomando = 0;
    public int ranuracol = 0;
    public int ranurafila = 0;
    int substate = 0;

    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Cambria",Font.PLAIN,40);
        arial_80B= new Font("Arial",Font.BOLD,80);
        //crear hud objeto
        entidad heart = new Obj_heart(gp);
        heart_full= heart.imagen;
        heart_half= heart.imagen2;
        heart_blank= heart.imagen3;
        entidad crystal = new Obj_manacrystal(gp);
        crystal_full = crystal.imagen;
        crystal_blank = crystal.imagen2;
    }
    public void anadirmensaje(String texto){
        mensaje.add(texto);
        contadormensaje.add(0);
    }
    public void dibujar(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //pantalla de inicio
        if(gp.estadodeljuego == gp.pantalladeinicio){
            dibujarpantalladeinicio();
        }
        // reanudar
        if(gp.estadodeljuego == gp.reanudar){
            dibujarvidajugador();
            dibujarmensaje();
        }
        // pausar
        if(gp.estadodeljuego == gp.pausar){
            dibujarvidajugador();
            dibujarpausa();
        }
        // dialogo
        if(gp.estadodeljuego == gp.dialogo){
            dibujarvidajugador();
            dibujarpantalladedialogo();
        }
        // estado de personaje
        if(gp.estadodeljuego == gp.estadodepersonaje){
            dibujarpantalladepersonaje();
            dibujarinventario();
        }
        // estado de opciones
        if(gp.estadodeljuego == gp.estadodeopciones){
            dibujarpantalladeopciones();
        }
    }
    public void dibujarvidajugador(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //dibujar vida maxima
        while(i< gp.jugador.vidamax/2){
            g2.drawImage(heart_blank, x,y,null);
            i++;
            x+= gp.tileSize;
        }
        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        // dibujar vida actual
        while(i< gp.jugador.vida){
            g2.drawImage(heart_half,x, y, null);
            i++;
            if(i< gp.jugador.vida){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }
        // dibujar mana maximo
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.jugador.manamaximo){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }
        // dibujar mana
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.jugador.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public void dibujarmensaje(){
        int mensajex = gp.tileSize;
        int mensajey = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for(int i=0; i<mensaje.size(); i++){
            if(mensaje.get(i) !=null){
                g2.setColor(Color.black);
                g2.drawString(mensaje.get(i), mensajex + 2, mensajey + 2);
                g2.setColor(Color.white);
                g2.drawString(mensaje.get(i), mensajex, mensajey);
                int contador = contadormensaje.get(i) + 1;
                contadormensaje.set(i, contador);
                mensajey += 50;
                if(contadormensaje.get(i) > 180){
                    mensaje.remove(i);
                    contadormensaje.remove(i);
                }
            }
        }
    }
    public void dibujarpantalladeinicio(){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.anchoPantalla,gp.altoPantalla);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String texto = "JUEGO RPG";
        int x= getxforcenteredtext(texto);
        int y= gp.tileSize*3;

        g2.setColor(Color.gray);
        g2.drawString(texto,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(texto,x,y);

        //imagen
        x= gp.anchoPantalla/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.jugador.down1,x,y,gp.tileSize*2, gp.tileSize*2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        texto="Nuevo Juego";
        x= getxforcenteredtext(texto);
        y+= gp.tileSize*3.5;
        g2.drawString(texto, x, y);
        if(numerodecomando == 0){
            g2.drawString(">",x- gp.tileSize,y);
        }

        texto="Continuar";
        x= getxforcenteredtext(texto);
        y+= gp.tileSize;
        g2.drawString(texto, x, y);
        if(numerodecomando == 1){
            g2.drawString(">",x- gp.tileSize,y);
        }

        texto="Salir";
        x= getxforcenteredtext(texto);
        y+= gp.tileSize;
        g2.drawString(texto, x, y);
        if(numerodecomando == 2){
            g2.drawString(">",x- gp.tileSize,y);
        }
    }
    public void dibujarpausa(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSA";
        int x=getxforcenteredtext(texto);
        int y = gp.altoPantalla/2;
        g2.drawString(texto, x, y);
    }
    public void dibujarpantalladedialogo(){
        //pestana
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int ancho = gp.anchoPantalla - (gp.tileSize*4);
        int alto = gp.tileSize*4;
        dibujarpestana(x,y,ancho,alto);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line: dialogoactual.split("\n")){
            g2.drawString(line,x,y);
            y+= 40;
        }
    }
    public void dibujarpantalladepersonaje(){
        final int framex = gp.tileSize;
        final int framey = gp.tileSize;
        final int frameancho = gp.tileSize*5;
        final int framealto = gp.tileSize*10;
        dibujarpestana(framex, framey, frameancho, framealto);
        //texto
        int textox = framex + 20;
        int textoy = framey + gp.tileSize;
        final int lineaalto = 35;
        //nombres
        g2.drawString("Lvl",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Life",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Mana",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Str",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Dex",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Atk",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Def",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Exp",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Next Lvl",textox,textoy);
        textoy += lineaalto;
        g2.drawString("Coin",textox,textoy);
        textoy += lineaalto + 10;
        g2.drawString("Weapon",textox,textoy);
        textoy += lineaalto +15;
        g2.drawString("Shield",textox,textoy);
        textoy += lineaalto;
        //valores
        int tailx = (framex + frameancho) -30;
        //reset texxto y
        textoy = framey + gp.tileSize;
        String valor;
        valor = String.valueOf(gp.jugador.lvl);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.vida+"/"+gp.jugador.vidamax);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.mana+"/"+gp.jugador.manamaximo);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.str);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.dex);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.atq);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.def);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.exp);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.nextlvlexp);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        valor = String.valueOf(gp.jugador.coin);
        textox = getxforAligntorighttext(valor,tailx);
        g2.drawString(valor,textox,textoy);
        textoy += lineaalto;

        g2.drawImage(gp.jugador.actualarma.down1, tailx - gp.tileSize, textoy-24,null);
        textoy += gp.tileSize;
        g2.drawImage(gp.jugador.actualescudo.down1, tailx - gp.tileSize,textoy-24,null);
    }
    public void dibujarinventario(){
        //frame
        int framex = gp.tileSize*9;
        int framey = gp.tileSize;
        int frameancho = gp.tileSize*6;
        int framealto = gp.tileSize*5;
        dibujarpestana(framex, framey, frameancho, framealto);
        // slot
        final int ranuraxstart = framex +20;
        final int ranuraystart = framey +20;
        int ranurax = ranuraxstart;
        int ranuray = ranuraystart;
        int tamanoranura = gp.tileSize+3;
        // dibujar items
        for(int i = 0; i< gp.jugador.inventario.size();i++){
            //equipar cursos
            if(gp.jugador.inventario.get(i) == gp.jugador.actualarma ||
            gp.jugador.inventario.get(i) == gp.jugador.actualescudo){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(ranurax, ranuray, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.jugador.inventario.get(i).down1,ranurax,ranuray,null);
            ranurax += tamanoranura;
            if(i==4 || i==9 || i==14){
                ranurax = ranuraxstart;
                ranuray += tamanoranura;
            }
        }
        //cursor
        int cursorx = ranuraxstart + (tamanoranura * ranuracol);
        int cursory = ranuraystart + (tamanoranura * ranurafila);
        int anchocursor = gp.tileSize;
        int altocursor = gp.tileSize;
        //dibujar cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorx, cursory, anchocursor, altocursor,10, 10);
        // descripcion ventana
        int dframex = framex;
        int dframey = framey + framealto;
        int dframeancho = frameancho;
        int dframealto = gp.tileSize*3;
        // dibujar texto de descripcion
        int textox = dframex +20;
        int textoy = dframey +gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        int indiceitem = getitemindexonslot();
        if(indiceitem < gp.jugador.inventario.size()){
            dibujarpestana(dframex,dframey,dframeancho,dframealto);
            for(String linea: gp.jugador.inventario.get(indiceitem).descripcion.split("\n")){
                g2.drawString(linea,textox,textoy);
                textoy +=32;
            }
        }
    }
    public void dibujarpantalladeopciones(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        // PESTANA
        int framex = gp.tileSize*6;
        int framey = gp.tileSize;
        int framewidth = gp.tileSize*8;
        int frameheight = gp.tileSize*10;
        dibujarpestana(framex, framey, framewidth, frameheight);
        switch (substate){
            case 0: opciones_top(framex, framey);break;
            case 1: opciones_notificaciondepantallacompleta(framex, framey);break;
            case 2: opciones_control(framex, framey); break;
        }
        gp.keyH.enterp = false;
    }
    public void opciones_top(int framex, int framey){
        int textox;
        int textoy;
        //titulo
        String texto = "Opciones";
        textox = getxforcenteredtext(texto);
        textoy = framey + gp.tileSize;
        g2.drawString(texto, textox, textoy);
        // pantalla grande on/off
        textox = framex + gp.tileSize;
        textoy += gp.tileSize*2;
        g2.drawString("Full Screen", textox, textoy);
        if(numerodecomando == 0){
            g2.drawString(">", textox-25, textoy);
            if(gp.keyH.enterp == true){
                if(gp.pantallacompletaon == false){
                    gp.pantallacompletaon = true;
                } else if(gp.pantallacompletaon == true){
                    gp.pantallacompletaon = false;
                }
                substate = 1;
            }
        }
        // musica
        textoy += gp.tileSize;
        g2.drawString("Musica", textox, textoy);
        if(numerodecomando == 1){
            g2.drawString(">", textox-25, textoy);
        }
        //SE
        textoy += gp.tileSize;
        g2.drawString("SE", textox, textoy);
        if(numerodecomando == 2){
            g2.drawString(">", textox-25, textoy);
        }
        // CONTROL
        textoy += gp.tileSize;
        g2.drawString("Control", textox, textoy);
        if(numerodecomando == 3){
            g2.drawString(">", textox-25, textoy);
            if(gp.keyH.enterp == true){
                substate = 2;
                numerodecomando = 0;
            }
        }
        // Fin de Juego
        textoy += gp.tileSize;
        g2.drawString("Fin del juego", textox, textoy);
        if(numerodecomando == 4){
            g2.drawString(">", textox-25, textoy);
        }
        // atras
        textoy += gp.tileSize*2;
        g2.drawString("Atras", textox, textoy);
        if(numerodecomando == 5){
            g2.drawString(">", textox-25, textoy);
        }
        // pantalla completa hceck box
        textox = framex + (int)(gp.tileSize*4.5);
        textoy = framey + gp.tileSize*2 +24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textox, textoy, 24, 24);
        if(gp.pantallacompletaon == true){
            g2.fillRect(textox, textoy, 24, 24);
        }
        // music volumen
        textoy += gp.tileSize;
        g2.drawRect(textox, textoy, 120, 24);
        int anchovolumen = 24 * gp.musica.escalavolumen;
        g2.fillRect(textox, textoy, anchovolumen, 24);
        // se volumen
        textoy += gp.tileSize;
        g2.drawRect(textox, textoy, 120, 24);
        anchovolumen = 24 * gp.se.escalavolumen;
        g2.fillRect(textox, textoy, anchovolumen, 24);
    }
    public void opciones_notificaciondepantallacompleta(int framex, int framey){
        int textox = framex + gp.tileSize;
        int textoy = framey + gp.tileSize*3;

        dialogoactual = "el cambio ha sido \ntomado efecto despues \nde reanudar el \njuego";
        for(String linea: dialogoactual.split("\n")){
            g2.drawString(linea, textox, textoy);
            textoy += 40;
        }
        // atras
        textoy = framey + gp.tileSize*9;
        g2.drawString("Atras", textox, textoy);
        if(numerodecomando == 0){
            g2.drawString(">", textox-25, textoy);
            if(gp.keyH.enterp == true){
                substate = 0;
            }
        }
    }
    public void opciones_control(int framex, int framey){
        int textox;
        int textoy;
        // titulo
        String texto = "Control";
        textox = getxforcenteredtext(texto);
        textoy = framey + gp.tileSize;
        g2.drawString(texto, textox, textoy);

        textox = framex + gp.tileSize;
        textoy += gp.tileSize;
        g2.drawString("Mover", textox, textoy); textoy += gp.tileSize;
        g2.drawString("Conf/Atq", textox, textoy); textoy += gp.tileSize;
        g2.drawString("Disp/Cast", textox, textoy); textoy += gp.tileSize;
        g2.drawString("Estado", textox, textoy); textoy += gp.tileSize;
        g2.drawString("Pausa", textox, textoy); textoy += gp.tileSize;
        g2.drawString("Opciones", textox, textoy); textoy += gp.tileSize;
        textox = framex + gp.tileSize*5;
        textoy = framey + gp.tileSize*2;
        g2.drawString("WASD", textox, textoy); textoy += gp.tileSize;
        g2.drawString("ENTER", textox, textoy); textoy += gp.tileSize;
        g2.drawString("F", textox, textoy); textoy += gp.tileSize;
        g2.drawString("C", textox, textoy); textoy += gp.tileSize;
        g2.drawString("P", textox, textoy); textoy += gp.tileSize;
        g2.drawString("ESC", textox, textoy); textoy += gp.tileSize;
        // ATRAS
        textox = framex + gp.tileSize;
        textoy = framey + gp.tileSize*9;
        g2.drawString("Atras", textox, textoy);
        if(numerodecomando == 0){
            g2.drawString(">", textox-25, textoy);
            if(gp.keyH.enterp == true){
                substate = 0;
            }
        }
    }
    public int getitemindexonslot(){
        int indiceitem = ranuracol + (ranurafila*5);
        return indiceitem;
    }
    public void dibujarpestana(int x, int y, int ancho, int alto){
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,ancho,alto,35,35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,ancho-10,alto-10,25,25);
    }
    public int getxforcenteredtext(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();
        int x = gp.anchoPantalla/2 - length/2;
        return x;
    }
    public int getxforAligntorighttext(String texto, int tailx){
        int length = (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();
        int x = tailx - length;
        return x;
    }
}
