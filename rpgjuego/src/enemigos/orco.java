package enemigos;

import Main.Panel_de_Juego;
import entidad.entidad;
import objeto.Obj_coin_bronce;
import objeto.Obj_heart;
import objeto.Obj_manacrystal;
import objeto.Obj_rock;

import java.util.Random;

public class orco extends entidad {
    Panel_de_Juego gp;
    public orco(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;
        tipo = tipo_enemigos;
        nombre = "orco";
        defaultspeed = 1;
        velocidad = defaultspeed;
        vidamax = 10;
        vida= vidamax;
        atq = 8;
        def = 2;
        exp = 10;

        areadecolision.x = 4;
        areadecolision.y = 4;
        areadecolision.width = 40;
        areadecolision.height = 44;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
        areadeataque.width = 48;
        areadeataque.height = 48;
        motion1_duracion = 40;
        motion2_duracion = 85;

        getImage();
        getatqImage();
    }
    public  void getImage(){
        up1 = setup("/enemigos/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemigos/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/enemigos/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemigos/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemigos/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemigos/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemigos/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemigos/orc_right_2", gp.tileSize, gp.tileSize);
    }
    public void getatqImage(){
        atqarriba1 = setup("/enemigos/orc_attack_up_1", gp.tileSize, gp.tileSize);
        atqarriba2 = setup("/enemigos/orc_attack_up_2", gp.tileSize, gp.tileSize);
        atqabajo1 = setup("/enemigos/orc_attack_down_1", gp.tileSize, gp.tileSize);
        atqabajo2 = setup("/enemigos/orc_attack_down_2", gp.tileSize, gp.tileSize);
        atqizq1 = setup("/enemigos/orc_attack_left_1", gp.tileSize, gp.tileSize);
        atqizq2 = setup("/enemigos/orc_attack_left_2", gp.tileSize, gp.tileSize);
        atqder1 = setup("/enemigos/orc_attack_right_1", gp.tileSize, gp.tileSize);
        atqder2 = setup("/enemigos/orc_attack_right_2", gp.tileSize, gp.tileSize);
    }
    public void  setaction(){
        if(onpath == true){
            //check if it stops chasing
            checkstopchasingornot(gp.jugador, 15, 100);
            // search the direction to go
            buscarcamino(getmetacol(gp.jugador), getmetafila(gp.jugador));
        }else{
            checkstartchasingornot(gp.jugador, 5,100);

            getrandomdireccion();
        }
        if(atacando == false){
            checkattackornot(30, gp.tileSize*4, gp.tileSize);
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
