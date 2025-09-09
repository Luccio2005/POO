package Main;

import objeto.Obj_key;

public class Activos {
    Panel_de_Juego gp;
    public Activos(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new Obj_key();
        gp.obj[0].mundox=23* gp.tileSize;
        gp.obj[0].mundoy=7* gp.tileSize;

        gp.obj[1] = new Obj_key();
        gp.obj[1].mundox=23* gp.tileSize;
        gp.obj[1].mundoy=40* gp.tileSize;


    }
}
