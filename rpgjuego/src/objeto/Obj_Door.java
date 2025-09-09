package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Superobjeto{
    public Obj_Door() {
        nombre="puerta";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
