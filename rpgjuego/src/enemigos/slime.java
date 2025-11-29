package enemigos;

import Main.Panel_de_Juego;
import entidad.entidad;
import objeto.Obj_coin_bronce;
import objeto.Obj_heart;
import objeto.Obj_manacrystal;
import objeto.Obj_rock;

import java.util.Random;

public class slime extends entidad{
    Panel_de_Juego gp;
    public slime(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;
        tipo = tipo_enemigos;
        nombre = "slime";
        defaultspeed = 1;
        velocidad = defaultspeed;
        vidamax = 4;
        vida= vidamax;
        atq = 5;
        def = 0;
        exp = 2;
        proyectiles = new Obj_rock(gp);

        areadecolision.x = 3;
        areadecolision.y = 10;
        areadecolision.width = 42;
        areadecolision.height = 30;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
        getImage();
    }
    public  void getImage(){
        up1 = setup("/enemigos/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemigos/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/enemigos/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemigos/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemigos/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemigos/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemigos/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemigos/greenslime_down_2", gp.tileSize, gp.tileSize);
    }
    public void  setaction(){
        if(onpath == true){
            //check if it stops chasing
            checkstopchasingornot(gp.jugador, 15, 100);
            // search the direction to go
            buscarcamino(getmetacol(gp.jugador), getmetafila(gp.jugador));
            // check if it shoots a proyectiles
            checkshootornot(200, 30);
        }else{
            checkstartchasingornot(gp.jugador, 5,100);

            getrandomdireccion();
        }
    }
    public void reaccionaldamage(){
        bloqueodeaccion = 0;
        //direccion = gp.jugador.direccion;
        onpath = true;
    }
    public void checkdrop(){
        // cast a die
        int i = new Random().nextInt(100)+1;
        // set the monster drop
        if(i<50){
            dropitem(new Obj_coin_bronce(gp));
        }if(i>=50 && i<75){
            dropitem(new Obj_heart(gp));
        }if(i>=75 && i<100){
            dropitem(new Obj_manacrystal(gp));
        }
    }
}
