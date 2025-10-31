package suelo.interactivo;

import Main.Panel_de_Juego;

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
}
