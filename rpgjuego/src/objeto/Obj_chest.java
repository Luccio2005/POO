package objeto;

import Main.Panel_de_Juego;
import entidad.entidad;

public class Obj_chest extends entidad{

    Panel_de_Juego gp;
    entidad loot;
    boolean opened = false;

    public Obj_chest(Panel_de_Juego gp, entidad loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        tipo = tipo_obstaculo;
        nombre="cofre";
        imagen = setup("/objetos/chest", gp.tileSize, gp.tileSize);
        imagen2 = setup("/objetos/chest_opened", gp.tileSize, gp.tileSize);
        down1 = imagen;
        colision = true;

        areadecolision.x = 4;
        areadecolision.y = 16;
        areadecolision.width = 40;
        areadecolision.height = 32;
        areadecolisionx = areadecolision.x;
        areadecolisiony = areadecolision.y;
    }
    public void interactuar(){
        gp.estadodeljuego = gp.dialogo;
        if(opened == false){
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("Encontraste una"+ loot.nombre + "!");
            if(gp.jugador.inventario.size() == gp.jugador.maxtamanoinventario){
                sb.append("\n...Pero no puedes llevar nada mas");
            }else{
                sb.append("\n Obtienes la " + loot.nombre + "!");
                gp.jugador.inventario.add(loot);
                down1 = imagen2;
                opened = true;
            }
            gp.ui.dialogoactual = sb.toString();
        }else{
            gp.ui.dialogoactual = "esta vacio";
        }
    }
}
