package Main;

import entidad.entidad;
import objeto.Obj_heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    Panel_de_Juego gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean mensajeOn= false;
    public String mensaje = "";
    int mensajeContador = 0;
    public boolean juegoterminado = false;
    public String dialogoactual = "";
    public int numerodecomando =0;


    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Cambria",Font.PLAIN,40);
        arial_80B= new Font("Arial",Font.BOLD,80);
        //crear hud objeto
        entidad heart = new Obj_heart(gp);
        heart_full= heart.imagen;
        heart_half= heart.imagen2;
        heart_blank= heart.imagen3;


    }
    public void mostrarmensaje(String texto){
        mensaje = texto;
        mensajeOn = true;
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
        final int framex = gp.tileSize*2;
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
        textoy += lineaalto;
        g2.drawString("Weapon",textox,textoy);
        textoy += lineaalto;
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
