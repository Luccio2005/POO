package data;

import Main.Panel_de_Juego;

import java.io.*;

public class guardarcarga {
    Panel_de_Juego gp;
    public guardarcarga(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void guardar(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            datosdealmacenamiento ds = new datosdealmacenamiento();

            ds.lvl = gp.jugador.lvl;
            ds.vidamax = gp.jugador.vidamax;
            ds.vida = gp.jugador.vida;
            ds.manamaximo = gp.jugador.manamaximo;
            ds.mana = gp.jugador.mana;

            oos.writeObject(ds);
        }catch(Exception e){
            System.out.println("Guardar excepcion");
        }
    }
    public void cargar(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            datosdealmacenamiento ds = (datosdealmacenamiento) ois.readObject();

            gp.jugador.lvl = ds.lvl;
            gp.jugador.vidamax = ds.vidamax;
            gp.jugador.vida = ds.vida;
            gp.jugador.manamaximo = ds.manamaximo;
            gp.jugador.mana = ds.mana;
            gp.jugador.str = ds.str;
            gp.jugador.dex = ds.dex;
            gp.jugador.exp = ds.exp;
            gp.jugador.nextlvlexp = ds.nextlvlexp;
            gp.jugador.coin = ds.coin;


        }catch(Exception e){
            System.out.println("Cargar excepcion");
        }
    }
}
