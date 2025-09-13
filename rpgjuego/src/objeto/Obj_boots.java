package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_boots extends Superobjeto{
    public Obj_boots() {
        nombre="botas";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
