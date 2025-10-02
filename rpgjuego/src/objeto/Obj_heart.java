package objeto;

import Main.Panel_de_Juego;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_heart extends Superobjeto{
    Panel_de_Juego gp;
    public Obj_heart(Panel_de_Juego gp) {
        this.gp = gp;
        nombre="corazon";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_full.png"));
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_half.png"));
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_blank.png"));
            imagen = Herramienta.Imagenescala(imagen, gp.tileSize, gp.tileSize);
            imagen2 = Herramienta.Imagenescala(imagen2, gp.tileSize, gp.tileSize);
            imagen3 = Herramienta.Imagenescala(imagen3, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
