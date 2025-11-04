package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;
import entidad.proyectiles;

import java.awt.*;

public class Obj_fireball extends proyectiles {
    Panel_de_Juego gp;
    public Obj_fireball(Panel_de_Juego gp){
        super(gp);
        this.gp = gp;
        nombre = "Bola de Fuego";
        velocidad = 5;
        vidamax = 80;
        vida = vidamax;
        atq = 2;
        usecost = 1;
        vivo = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/proyectiles/fireball_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/proyectiles/fireball_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/proyectiles/fireball_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/proyectiles/fireball_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/proyectiles/fireball_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/proyectiles/fireball_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/proyectiles/fireball_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/proyectiles/fireball_right_2",gp.tileSize,gp.tileSize);
    }
    public boolean haveresource(entidad usuario){
        boolean haveresource = false;
        if(usuario.mana >= usecost){
            haveresource = true;
        }
        return haveresource;
    }
    public void subtractresource(entidad usuario){
        usuario.mana -= usecost;
    }
    public Color getparticulacolor(){
        Color color = new Color(240, 50, 0);
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
