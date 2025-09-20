package objeto;

import Main.Panel_de_Juego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_boots extends Superobjeto{
    Panel_de_Juego gp;
    public Obj_boots(Panel_de_Juego gp) {
        this.gp = gp;
        nombre="botas";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/boots.png"));
            Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
