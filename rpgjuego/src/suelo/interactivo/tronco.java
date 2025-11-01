package suelo.interactivo;

import Main.Panel_de_Juego;

public class tronco extends suelointeractivo{
    Panel_de_Juego gp;

    public tronco(Panel_de_Juego gp, int col, int fila){
        super(gp, col, fila);
        this.gp = gp;

        this.mundox = gp.tileSize * col;
        this.mundoy = gp.tileSize * fila;

        down1 = setup("/interactivo/trunk",gp.tileSize,gp.tileSize);
        areadecolision.x = 0;
        areadecolision.y = 0;
        areadecolision.width = 0;
        areadecolision.height = 0;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
    }
}
