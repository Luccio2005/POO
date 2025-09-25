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
    }
    public void setnpc(){
        gp.npc[0]= new npc_oldman(gp);
        gp.npc[0].mundox = gp.tileSize*21;
        gp.npc[0].mundoy = gp.tileSize*21;
    }
}

