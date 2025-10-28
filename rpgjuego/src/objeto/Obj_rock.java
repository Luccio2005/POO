package objeto;
import entidad.proyectiles;
import Main.Panel_de_Juego;

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
}
