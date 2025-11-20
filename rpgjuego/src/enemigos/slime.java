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
        velocidad =1;
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
    public void actualizar(){
        super.actualizar();
        int distanciax = Math.abs(mundox - gp.jugador.mundox);
        int distanciay = Math.abs(mundoy - gp.jugador.mundoy);
        int titulodistancia = (distanciax + distanciay)/gp.tileSize;
        if(onpath == false && titulodistancia < 5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onpath = true;
            }
        }
    }
    public void  setaction(){
        if(onpath == true){
            //int metacol = 12;
            //int metafila = 9;
            int metacol = (gp.jugador.mundox + gp.jugador.areadecolision.x)/gp.tileSize;
            int metafila = (gp.jugador.mundoy + gp.jugador.areadecolision.y)/gp.tileSize;
            buscarcamino(metacol, metafila);
            int i = new Random().nextInt(200)+1;
            if(i > 197 && proyectiles.vivo == false && contadordisparodisponible == 30){
                proyectiles.set(mundox, mundoy, direccion, true, this);
                gp.listaproyectil.add(proyectiles);
                contadordisparodisponible = 0;
            }
        }else{
            bloqueodeaccion ++;
            if(bloqueodeaccion ==120){
                Random random = new Random();
                int i = random.nextInt(100)+1;
                if(i<=25){
                    direccion = "up";
                } if(i>25 && i<=50){
                    direccion = "down";
                } if(i>50 && i <=75){
                    direccion = "left";
                } if(i>75 && i<=100){
                    direccion = "right";
                }
                bloqueodeaccion = 0;
            }
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
