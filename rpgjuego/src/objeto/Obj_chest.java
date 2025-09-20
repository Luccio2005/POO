package objeto;

import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_chest extends Superobjeto{
    Panel_de_Juego gp;
    public Obj_chest(Panel_de_Juego gp) {
        this.gp = gp;
        nombre="cofre";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/chest.png"));
            Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
