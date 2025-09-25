package entidad;

import Main.Herramientasdeutilidad;
import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class npc_oldman extends entidad{
    public npc_oldman(Panel_de_Juego gp){
        super(gp);
        direccion = "down";
        velocidad=1;
    }
    public void getPlayerImage(){

        up1= setup("/npc/oldman_up_1");
        up2= setup("/npc/oldman_up_2");
        down1= setup("/npc/oldman_down_1");
        down2= setup("/npc/oldman_down_2");
        left1= setup("/npc/oldman_left_1");
        left2= setup("/npc/oldman_left_2");
        right1= setup("/npc/oldman_right_1");
        right2= setup("/npc/oldman_right_2");
    }
}
