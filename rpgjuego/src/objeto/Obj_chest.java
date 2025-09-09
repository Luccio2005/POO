package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_chest extends Superobjeto{
    public Obj_chest() {
        nombre="cofre";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
