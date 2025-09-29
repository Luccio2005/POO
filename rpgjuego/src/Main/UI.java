package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Panel_de_Juego gp;
    Graphics2D g2;
    Font arial_40, arial_80B;

    public boolean mensajeOn= false;
    public String mensaje = "";
    int mensajeContador = 0;
    public boolean juegoterminado = false;
    public String dialogoactual = "";


    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Cambria",Font.PLAIN,40);
        arial_80B= new Font("Arial",Font.BOLD,80);

    }
    public void mostrarmensaje(String texto){
        mensaje = texto;
        mensajeOn = true;
    }
    public void dibujar(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.estadodeljuego == gp.reanudar){

        }if(gp.estadodeljuego == gp.pausar){
            dibujarpausa();
        }if(gp.estadodeljuego == gp.dialogo){
            dibujarpantalladedialogo();
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
}
