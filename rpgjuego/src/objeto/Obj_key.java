package objeto;

import Main.Panel_de_Juego;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_key extends Superobjeto{
    Panel_de_Juego gp;
    public Obj_key(Panel_de_Juego gp) {
        this.gp = gp;
        nombre="llave";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/key.png"));
            Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
