package objeto;

import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Superobjeto{
    Panel_de_Juego gp;
    public Obj_Door(Panel_de_Juego gp) {
        this.gp = gp;
        nombre="puerta";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/door.png"));
            Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}
