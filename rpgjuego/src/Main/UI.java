package Main;

import java.awt.*;

public class UI {
    Panel_de_Juego gp;
    Font arial_40;

    public UI(Panel_de_Juego gp){
        this.gp= gp;
        arial_40= new Font("Arial",Font.PLAIN,40);
    }
    public void dibujar(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("llave = "+gp.jugador.tenerllave,50,50);
    }
}
