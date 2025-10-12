package enemigos;

import Main.Panel_de_Juego;
import entidad.entidad;

import java.util.Random;

public class slime extends entidad{
    Panel_de_Juego gp;
    public slime(Panel_de_Juego gp) {
        super(gp);
        this.gp = gp;
        tipo = 2;
        nombre = "slime";
        velocidad =1;
        vidamax = 4;
        vida= vidamax;

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
            } if(i>75 && i <=100){
                direccion = "right";
            }
            bloqueodeaccion = 0;
        }
    }
}
