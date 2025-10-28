package entidad;

import Main.Panel_de_Juego;

public class proyectiles extends entidad{
    entidad usuario;
    public  proyectiles(Panel_de_Juego gp){
        super(gp);
    }
    public void set(int mundox, int mundoy, String direccion, boolean vivo, entidad usuario){
        this.mundox = mundox;
        this.mundoy = mundoy;
        this.direccion = direccion;
        this.vivo = vivo;
        this.usuario = usuario;
        this.vida = this.vidamax;
    }
    public void actualizar(){
        if(usuario == gp.jugador){
            int indiceenemigo = gp.comprobar.comprobarentidad(this, gp.enemigos);
            if(indiceenemigo != 999){
                gp.jugador.damageenemigo(indiceenemigo, atq);
                vivo = false;
            }
        }
        if(usuario != gp.jugador){

        }
        switch (direccion){
            case "up": mundoy -= velocidad; break;
            case "down": mundoy += velocidad; break;
            case "left": mundox -= velocidad; break;
            case "right": mundox += velocidad; break;
        }
        vida--;
        if(vida <=0){
            vivo = false;
        }
        contadorSprite++;
        if(contadorSprite > 12){
            if(numeroSprite == 1){
                numeroSprite =2;
            } else if(numeroSprite == 2){
                numeroSprite =1;
            }
            contadorSprite = 0;
        }
    }
}
