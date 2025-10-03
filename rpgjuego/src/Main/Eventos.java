package Main;

import java.awt.*;

public class Eventos {
    Panel_de_Juego gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public Eventos(Panel_de_Juego gp){
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    public void comprobarevento(){
        if(hit(27,16,"right") == true){
            damagepit(gp.dialogo);
        }
        if(hit(23,12,"up") == true){
            healingpool((gp.dialogo));
        }
    }
    public boolean hit(int eventocol, int eventofil, String reqDireccion){
        boolean hit = false;
        gp.jugador.areadecolision.x = gp.jugador.mundox + gp.jugador.areadecolision.x;
        gp.jugador.areadecolision.y = gp.jugador.mundoy + gp.jugador.areadecolision.y;
        eventRect.x = eventocol*gp.tileSize + eventRect.x;
        eventRect.y = eventofil*gp.tileSize + eventRect.y;
        if(gp.jugador.areadecolision.intersects(eventRect)){
            if(gp.jugador.direccion.contentEquals(reqDireccion) || reqDireccion.contentEquals("any")){
                hit= true;
            }
        }
        gp.jugador.areadecolision.x = gp.jugador.areadecolisionx;
        gp.jugador.areadecolision.y = gp.jugador.areadecolisiony;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
    public void damagepit(int estadodeljuego){
        gp.estadodeljuego = estadodeljuego;
        gp.ui.dialogoactual = "Caiste en un pozo";
        gp.jugador.vida -=1;
    }
    public void healingpool(int estadodeljuego){
        if(gp.keyH.enterp == true){
            gp.estadodeljuego = estadodeljuego;
            gp.ui.dialogoactual = "tu tomas agua del lago \ntu vida se esta recuperando";
            gp.jugador.vida = gp.jugador.vidamax;
        }
    }
}
