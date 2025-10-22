package Main;

import enemigos.slime;
import entidad.npc_oldman;
import objeto.Obj_Door;
import objeto.Obj_boots;
import objeto.Obj_chest;
import objeto.Obj_key;

public class Activos {
    Panel_de_Juego gp;
    public Activos(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setObject(){
    }
    public void setnpc(){
        gp.npc[0]= new npc_oldman(gp);
        gp.npc[0].mundox = gp.tileSize*21;
        gp.npc[0].mundoy = gp.tileSize*21;
    }
    public void setenemigos(){
        int i =0;
        gp.enemigos[i] = new slime(gp);
        gp.enemigos[i].mundox = gp.tileSize*23;
        gp.enemigos[i].mundoy = gp.tileSize*36;
        i++;
        gp.enemigos[i] = new slime(gp);
        gp.enemigos[i].mundox = gp.tileSize*23;
        gp.enemigos[i].mundoy = gp.tileSize*37;
        i++;
        gp.enemigos[i] = new slime(gp);
        gp.enemigos[i].mundox = gp.tileSize*24;
        gp.enemigos[i].mundoy = gp.tileSize*37;
        i++;
        gp.enemigos[i] = new slime(gp);
        gp.enemigos[i].mundox = gp.tileSize*34;
        gp.enemigos[i].mundoy = gp.tileSize*42;
        i++;
        gp.enemigos[i] = new slime(gp);
        gp.enemigos[i].mundox = gp.tileSize*38;
        gp.enemigos[i].mundoy = gp.tileSize*42;
        i++;
    }
}

