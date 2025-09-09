package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_key extends Superobjeto{
    public Obj_key() {
        nombre="llave";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
