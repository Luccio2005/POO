package Main;

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
        gp.obj[0] = new Obj_Door(gp);
        gp.obj[0].mundox = gp.tileSize*21;
        gp.obj[0].mundoy = gp.tileSize*22;

        gp.obj[1] = new Obj_Door(gp);
        gp.obj[1].mundox = gp.tileSize*23;
        gp.obj[1].mundoy = gp.tileSize*25;
    }
    public void setnpc(){
        gp.npc[0]= new npc_oldman(gp);
        gp.npc[0].mundox = gp.tileSize*21;
        gp.npc[0].mundoy = gp.tileSize*21;

        gp.npc[1]= new npc_oldman(gp);
        gp.npc[1].mundox = gp.tileSize*11;
        gp.npc[1].mundoy = gp.tileSize*21;

        gp.npc[2]= new npc_oldman(gp);
        gp.npc[2].mundox = gp.tileSize*21;
        gp.npc[2].mundoy = gp.tileSize*21;

        gp.npc[3]= new npc_oldman(gp);
        gp.npc[3].mundox = gp.tileSize*21;
        gp.npc[3].mundoy = gp.tileSize*11;

        gp.npc[4]= new npc_oldman(gp);
        gp.npc[4].mundox = gp.tileSize*21;
        gp.npc[4].mundoy = gp.tileSize*31;
    }
}

