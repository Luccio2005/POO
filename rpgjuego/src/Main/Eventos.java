package Main;

import entidad.entidad;

import java.awt.*;

public class Eventos {
    Panel_de_Juego gp;
    EventoRect eventoRect[][][];
    int anterioreventox, anterioreventoy;
    boolean cantouchevent = true;
    int tempmap, tempcol, tempfila;
    public Eventos(Panel_de_Juego gp){
        this.gp = gp;
        eventoRect = new EventoRect[gp.maxmap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int fila = 0;
        while(map < gp.maxmap && col < gp.maxWorldCol && fila < gp.maxWorldRow){
            eventoRect[map][col][fila] = new EventoRect();
            eventoRect[map][col][fila].x = 23;
            eventoRect[map][col][fila].y = 23;
            eventoRect[map][col][fila].width = 2;
            eventoRect[map][col][fila].height = 2;
            eventoRect[map][col][fila].eventRectDefaultX = eventoRect[map][col][fila].x;
            eventoRect[map][col][fila].eventRectDefaultY = eventoRect[map][col][fila].y;
            col++;
            if(col == gp.maxWorldCol){
                col=0;
                fila++;
                if(fila == gp.maxWorldRow){
                    fila = 0;
                    map++;
                }
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
            if(hit(0,27,16,"right") == true){
                damagepit(gp.dialogo);
            }
            else if(hit(0,23,12,"up") == true){
                healingpool(gp.dialogo);
            }
            else if (hit(0, 10, 39, "any") == true){
                teleport(1,12,13);
            }
            else if (hit(1, 12, 13, "any") == true){
                teleport(0,10,39);
            }
            else if(hit(1, 12, 9, "up") == true){
                hablar(gp.npc[1][0]);
            }
        }
    }
    public boolean hit(int map, int col, int fila, String reqDireccion){
        boolean hit = false;
        if(map == gp.actualmapa){
            gp.jugador.areadecolision.x = gp.jugador.mundox + gp.jugador.areadecolision.x;
            gp.jugador.areadecolision.y = gp.jugador.mundoy + gp.jugador.areadecolision.y;
            eventoRect[map][col][fila].x = col*gp.tileSize + eventoRect[map][col][fila].x;
            eventoRect[map][col][fila].y = fila*gp.tileSize + eventoRect[map][col][fila].y;
            if(gp.jugador.areadecolision.intersects(eventoRect[map][col][fila]) && eventoRect[map][col][fila].eventohecho == false){
                if(gp.jugador.direccion.contentEquals(reqDireccion) || reqDireccion.contentEquals("any")){
                    hit= true;
                    anterioreventox = gp.jugador.mundox;
                    anterioreventoy = gp.jugador.mundoy;
                }
            }
            gp.jugador.areadecolision.x = gp.jugador.areadecolisionx;
            gp.jugador.areadecolision.y = gp.jugador.areadecolisiony;
            eventoRect[map][col][fila].x = eventoRect[map][col][fila].eventRectDefaultX;
            eventoRect[map][col][fila].y = eventoRect[map][col][fila].eventRectDefaultY;
        }
        return hit;
    }
    public void damagepit(int estadodeljuego){
        gp.estadodeljuego = estadodeljuego;
        gp.ui.dialogoactual = "Caiste en un pozo";
        gp.jugador.vida -=1;
        //eventoRect[col][fila].eventohecho = true;
        cantouchevent = false;
    }
    public void healingpool(int estadodeljuego){
        if(gp.keyH.enterp == true){
            gp.estadodeljuego = estadodeljuego;
            gp.jugador.cancelaratq = true;
            gp.playSE(2);
            gp.ui.dialogoactual = "tomas agua del lago \ntu vida y mana se lleno";
            gp.jugador.vida = gp.jugador.vidamax;
            gp.jugador.mana = gp.jugador.manamaximo;
            gp.aSetter.setenemigos();
        }
    }
    public void teleport(int map, int col, int fila){
        gp.estadodeljuego = gp.estadotransicion;
        tempmap = map;
        tempcol = col;
        tempfila = fila;
        gp.actualmapa = map;

        cantouchevent = false;
        gp.playSE(13);
    }
    public void hablar(entidad entidad){
        if(gp.keyH.enterp == true){
            gp.estadodeljuego = gp.dialogo;
            gp.jugador.cancelaratq = true;
            entidad.hablar();
        }
    }
}
