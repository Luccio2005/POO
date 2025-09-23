package Main;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Panel_de_Juego gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    //BufferedImage imagenllave;
    public boolean mensajeOn= false;
    public String mensaje = "";
    int mensajeContador = 0;
    public boolean juegoterminado = false;

    double tiempodeJuego;
    DecimalFormat dformato = new DecimalFormat("#0.00");

    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Arial",Font.PLAIN,40);
        arial_80B= new Font("Arial",Font.BOLD,80);
        //Obj_key key = new Obj_key(gp);
       // imagenllave = key.imagen;
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
        }
    }
    public void dibujarpausa(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSA";
        int x=getxforcenteredtext(texto);
        int y = gp.altoPantalla/2;
        g2.drawString(texto, x, y);
    }
    public int getxforcenteredtext(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();
        int x = gp.anchoPantalla/2 - length/2;
        return x;
    }
}
