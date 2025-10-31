package suelo.interactivo;

import Main.Panel_de_Juego;

public class arbolmarchito extends suelointeractivo{
    Panel_de_Juego gp;

    public arbolmarchito(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;

        down1 = setup("/interactivo/drytree",gp.tileSize,gp.tileSize);
        destructible = true;
    }
}
