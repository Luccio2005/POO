package ambiente;

import Main.Panel_de_Juego;

import java.awt.*;

public class ambientemanager {
    Panel_de_Juego gp;
    public iluminacion iluminacion;

    public ambientemanager(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setup(){
        iluminacion = new iluminacion(gp);
    }
    public void actualizar(){
        iluminacion.actualizar();
    }
    public void dibujar(Graphics2D g2){
        iluminacion.dibujar(g2);
    }
}
