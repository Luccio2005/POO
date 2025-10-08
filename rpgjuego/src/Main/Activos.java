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
        gp.enemigos[0] = new slime(gp);
        gp.enemigos[0].mundox = gp.tileSize*23;
        gp.enemigos[0].mundoy = gp.tileSize*36;

        gp.enemigos[1] = new slime(gp);
        gp.enemigos[1].mundox = gp.tileSize*23;
        gp.enemigos[1].mundoy = gp.tileSize*37;
    }
}

