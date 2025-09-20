package Main;

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
        gp.obj[0] = new Obj_key(gp);
        gp.obj[0].mundox=23* gp.tileSize;
        gp.obj[0].mundoy=7* gp.tileSize;

        gp.obj[1] = new Obj_key(gp);
        gp.obj[1].mundox=23* gp.tileSize;
        gp.obj[1].mundoy=40* gp.tileSize;

        gp.obj[2] = new Obj_key(gp);
        gp.obj[2].mundox=38* gp.tileSize;
        gp.obj[2].mundoy=8* gp.tileSize;

        gp.obj[3] = new Obj_Door(gp);
        gp.obj[3].mundox=10* gp.tileSize;
        gp.obj[3].mundoy=11* gp.tileSize;

        gp.obj[4] = new Obj_Door(gp);
        gp.obj[4].mundox=8* gp.tileSize;
        gp.obj[4].mundoy=28* gp.tileSize;

        gp.obj[5] = new Obj_Door(gp);
        gp.obj[5].mundox=12* gp.tileSize;
        gp.obj[5].mundoy=22* gp.tileSize;

        gp.obj[6] = new Obj_chest(gp);
        gp.obj[6].mundox=10* gp.tileSize;
        gp.obj[6].mundoy=7* gp.tileSize;

        gp.obj[7] = new Obj_boots(gp);
        gp.obj[7].mundox=37* gp.tileSize;
        gp.obj[7].mundoy=42* gp.tileSize;


    }
}
