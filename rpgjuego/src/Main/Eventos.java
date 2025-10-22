package Main;

import java.awt.*;

public class Eventos {
    Panel_de_Juego gp;
    EventoRect eventoRect[][];
    int anterioreventox, anterioreventoy;
    boolean cantouchevent = true;
    public Eventos(Panel_de_Juego gp){
        this.gp = gp;
        eventoRect = new EventoRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int fila = 0;
        while(col < gp.maxWorldCol && fila < gp.maxWorldRow){
            eventoRect[col][fila] = new EventoRect();
            eventoRect[col][fila].x = 23;
            eventoRect[col][fila].y = 23;
            eventoRect[col][fila].width = 2;
            eventoRect[col][fila].height = 2;
            eventoRect[col][fila].eventRectDefaultX = eventoRect[col][fila].x;
            eventoRect[col][fila].eventRectDefaultY = eventoRect[col][fila].y;
            col++;
            if(col == gp.maxWorldCol){
                col=0;
                fila++;
            }
        }


    }
    public void comprobarevento(){
        //comprobar si el jugador se mueve un suelo mas q en el ultimo evento
        int distanciax = Math.abs(gp.jugador.mundox - anterioreventox);
        int distanciay = Math.abs(gp.jugador.mundoy - anterioreventoy);
        int distancia = Math.max(distanciax, distanciay);
        if(distancia > gp.tileSize){
            cantouchevent = true;
        }
        if(cantouchevent == true){
            if(hit(27,16,"right") == true){
                damagepit(27,16, gp.dialogo);
            }
            if(hit(27,19,"any") == true){
                damagepit(27,16, gp.dialogo);
            }
            if(hit(23,12,"up") == true){
                healingpool(23,12, gp.dialogo);
            }
        }
    }
    public boolean hit(int col, int fila, String reqDireccion){
        boolean hit = false;
        gp.jugador.areadecolision.x = gp.jugador.mundox + gp.jugador.areadecolision.x;
        gp.jugador.areadecolision.y = gp.jugador.mundoy + gp.jugador.areadecolision.y;
        eventoRect[col][fila].x = col*gp.tileSize + eventoRect[col][fila].x;
        eventoRect[col][fila].y = fila*gp.tileSize + eventoRect[col][fila].y;
        if(gp.jugador.areadecolision.intersects(eventoRect[col][fila]) && eventoRect[col][fila].eventohecho == false){
            if(gp.jugador.direccion.contentEquals(reqDireccion) || reqDireccion.contentEquals("any")){
                hit= true;
                anterioreventox = gp.jugador.mundox;
                anterioreventoy = gp.jugador.mundoy;
            }
        }
        gp.jugador.areadecolision.x = gp.jugador.areadecolisionx;
        gp.jugador.areadecolision.y = gp.jugador.areadecolisiony;
        eventoRect[col][fila].x = eventoRect[col][fila].eventRectDefaultX;
        eventoRect[col][fila].y = eventoRect[col][fila].eventRectDefaultY;
        return hit;
    }
    public void damagepit(int col, int fila, int estadodeljuego){
        gp.estadodeljuego = estadodeljuego;
        gp.ui.dialogoactual = "Caiste en un pozo";
        gp.jugador.vida -=1;
        //eventoRect[col][fila].eventohecho = true;
        cantouchevent = false;
    }
    public void healingpool(int col, int fila, int estadodeljuego){
        if(gp.keyH.enterp == true){
            gp.estadodeljuego = estadodeljuego;
            gp.jugador.cancelaratq = true;
            gp.playSE(2);
            gp.ui.dialogoactual = "tu tomas agua del lago \ntu vida se esta recuperando";
            gp.jugador.vida = gp.jugador.vidamax;
            gp.aSetter.setenemigos();
        }
    }
}
