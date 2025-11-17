package entidad;

import Main.Panel_de_Juego;
import objeto.*;

public class npc_mercader extends entidad{
    public npc_mercader(Panel_de_Juego gp){
        super(gp);
        direccion = "down";
        velocidad=1;
        getImage();
        setdialogo();
        setitems();
    }
    public void getImage(){

        up1= setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2= setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1= setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2= setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1= setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2= setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1= setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2= setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }
    public void setdialogo(){
        dialogos[0]= "Je, je asi q me encontraste\n soy el mercader y puedo \nintercambiar cosas buenas";

    }
    public void setitems(){
        inventario.add(new Obj_potion(gp));
        inventario.add(new Obj_key(gp));
        inventario.add(new Obj_sword_normal(gp));
        inventario.add(new Obj_axe(gp));
        inventario.add(new Obj_shield_wood(gp));
        inventario.add(new Obj_shield_blue(gp));
    }
    public void hablar(){
        super.hablar();
        gp.estadodeljuego = gp.estadointercambio;
        gp.ui.npc = this;
    }
}
