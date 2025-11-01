package suelo.interactivo;

import Main.Panel_de_Juego;
import entidad.entidad;

public class arbolmarchito extends suelointeractivo{
    Panel_de_Juego gp;

    public arbolmarchito(Panel_de_Juego gp, int col, int fila){
        super(gp, col, fila);
        this.gp = gp;

        this.mundox = gp.tileSize * col;
        this.mundoy = gp.tileSize * fila;

        down1 = setup("/interactivo/drytree",gp.tileSize,gp.tileSize);
        destructible = true;
    }
    public boolean itemcorrecto(entidad entidad){
        boolean itemcorrecto = false;
        if(entidad.actualarma.tipo == tipo_hacha){
            itemcorrecto = true;
        }
        return itemcorrecto;
    }
    public void playSE(){
        gp.playSE(11);
    }
    public suelointeractivo getformadestruida(){
        suelointeractivo suelo = new tronco(gp, mundox/gp.tileSize, mundoy/gp.tileSize);
        return suelo;
    }
}
