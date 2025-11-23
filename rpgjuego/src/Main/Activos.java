package Main;

import enemigos.slime;
import entidad.npc_mercader;
import entidad.npc_oldman;
import objeto.*;
import suelo.interactivo.arbolmarchito;

public class Activos {
    Panel_de_Juego gp;
    public Activos(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        int i=0;
        gp.obj[mapNum][i] = new Obj_coin_bronce(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*25;
        gp.obj[mapNum][i].mundoy = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new Obj_key(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*21;
        gp.obj[mapNum][i].mundoy = gp.tileSize*19;
        i++;
        gp.obj[mapNum][i] = new Obj_key(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*26;
        gp.obj[mapNum][i].mundoy = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new Obj_axe(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*33;
        gp.obj[mapNum][i].mundoy = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new Obj_shield_blue(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*35;
        gp.obj[mapNum][i].mundoy = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new Obj_potion(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*22;
        gp.obj[mapNum][i].mundoy = gp.tileSize*27;
        i++;
        gp.obj[mapNum][i] = new Obj_heart(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*22;
        gp.obj[mapNum][i].mundoy = gp.tileSize*29;
        i++;
        gp.obj[mapNum][i] = new Obj_manacrystal(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*22;
        gp.obj[mapNum][i].mundoy = gp.tileSize*31;
        i++;
        gp.obj[mapNum][i] = new Obj_Door(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*14;
        gp.obj[mapNum][i].mundoy = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new Obj_Door(gp);
        gp.obj[mapNum][i].mundox = gp.tileSize*12;
        gp.obj[mapNum][i].mundoy = gp.tileSize*12;
        i++;
    }
    public void setnpc(){
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i]= new npc_oldman(gp);
        gp.npc[mapNum][i].mundox = gp.tileSize*21;
        gp.npc[mapNum][i].mundoy = gp.tileSize*21;
        i++;
        //mapa1
        mapNum = 1;
        i=0;
        gp.npc[mapNum][i]= new npc_mercader(gp);
        gp.npc[mapNum][i].mundox = gp.tileSize*12;
        gp.npc[mapNum][i].mundoy = gp.tileSize*7;
        i++;
    }
    public void setenemigos(){
        int mapNum = 0;
        int i =0;
        gp.enemigos[mapNum][i] = new slime(gp);
        gp.enemigos[mapNum][i].mundox = gp.tileSize*23;
        gp.enemigos[mapNum][i].mundoy = gp.tileSize*36;
        i++;
        gp.enemigos[mapNum][i] = new slime(gp);
        gp.enemigos[mapNum][i].mundox = gp.tileSize*23;
        gp.enemigos[mapNum][i].mundoy = gp.tileSize*37;
        i++;
        gp.enemigos[mapNum][i] = new slime(gp);
        gp.enemigos[mapNum][i].mundox = gp.tileSize*24;
        gp.enemigos[mapNum][i].mundoy = gp.tileSize*37;
        i++;
        gp.enemigos[mapNum][i] = new slime(gp);
        gp.enemigos[mapNum][i].mundox = gp.tileSize*34;
        gp.enemigos[mapNum][i].mundoy = gp.tileSize*42;
        i++;
        gp.enemigos[mapNum][i] = new slime(gp);
        gp.enemigos[mapNum][i].mundox = gp.tileSize*38;
        gp.enemigos[mapNum][i].mundoy = gp.tileSize*42;
        i++;
    }
    public void setsuelointeractivo(){
        int mapNum = 0;
        int i = 0;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 27, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 28, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 29, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 30, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 31, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 32, 12);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 18, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 17, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 16, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 15, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 14, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 13, 40);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 13, 41);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 12, 41);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 11, 41);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 10, 41);i++;
        gp.itile[mapNum][i]= new arbolmarchito(gp, 10, 40);i++;
    }
}

