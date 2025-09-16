package Main;

import objeto.Obj_key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    Panel_de_Juego gp;
    Font arial_40;
    BufferedImage imagenllave;
    public boolean mensajeOn= false;
    public String mensaje = "";
    int mensajeContador = 0;

    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Arial",Font.PLAIN,40);
        Obj_key key = new Obj_key();
        imagenllave = key.imagen;
    }
    public void mostrarmensaje(String texto){
        mensaje = texto;
        mensajeOn = true;
    }
    public void dibujar(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(imagenllave,gp.tileSize/2,gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.jugador.tenerllave,74,65);

        if(mensajeOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(mensaje, gp.tileSize/2, gp.tileSize*5 );
            mensajeContador++;
            if(mensajeContador>120){
                mensajeContador=0;
                mensajeOn=false;
            }
        }
    }
}
