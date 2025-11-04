package objeto;
import entidad.entidad;
import entidad.proyectiles;
import Main.Panel_de_Juego;

import java.awt.*;

public class Obj_rock extends proyectiles {
    Panel_de_Juego gp;
    public Obj_rock(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        nombre = "Roca";
        velocidad = 8;
        vidamax = 80;
        vida = vidamax;
        atq = 2;
        usecost = 1;
        vivo = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        down1 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        left1 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        right1 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/proyectiles/rock_down_1",gp.tileSize,gp.tileSize);
    }
    public boolean haveresource(entidad usuario){
        boolean haveresource = false;
        if(usuario.municion >= usecost){
            haveresource = true;
        }
        return haveresource;
    }
    public void subtractresource(entidad usuario){
        usuario.municion -= usecost;
    }
    public Color getparticulacolor(){
        Color color = new Color(40, 50, 0);
        return color;
    }
    public int getparticulatamano(){
        int size = 10; //6pixeles
        return size;
    }
    public int getpaticulavelocidad(){
        int velocidad = 1;
        return velocidad;
    }
    public int getparticulavidamax(){
        int vidamax = 20;
        return vidamax;
    }
}
