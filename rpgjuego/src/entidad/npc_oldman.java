package entidad;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class npc_oldman extends entidad{
    public npc_oldman(Panel_de_Juego gp){
        super(gp);
        direccion = "down";
        velocidad=1;
        getImage();
    }
    public void getImage(){

        up1= setup("/npc/oldman_up_1");
        up2= setup("/npc/oldman_up_2");
        down1= setup("/npc/oldman_down_1");
        down2= setup("/npc/oldman_down_2");
        left1= setup("/npc/oldman_left_1");
        left2= setup("/npc/oldman_left_2");
        right1= setup("/npc/oldman_right_1");
        right2= setup("/npc/oldman_right_2");
    }
    public void setaction(){
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
