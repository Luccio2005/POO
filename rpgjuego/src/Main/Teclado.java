package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    Panel_de_Juego gp;
    public boolean arribap, abajop, izquierdap, derechap;
    boolean checkDrawTime = false;

    public Teclado(Panel_de_Juego gp){
        this.gp = gp;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();
        //reanudar
        if(gp.estadodeljuego == gp.reanudar){
            if(codigo == KeyEvent.VK_W){
                arribap = true;
            }
            if(codigo == KeyEvent.VK_S){
                abajop = true;
            }
            if(codigo == KeyEvent.VK_A){
                izquierdap = true;
            }
            if(codigo == KeyEvent.VK_D){
                derechap = true;
            }
            if(codigo == KeyEvent.VK_P){
                gp.estadodeljuego = gp.pausar;
            }
        }
        //pausar
        else if(gp.estadodeljuego == gp.pausar){
            if(codigo == KeyEvent.VK_P){
                gp.estadodeljuego = gp.reanudar;
            }
        }
        //dialogo
        else if(gp.estadodeljuego == gp.dialogo){
            if(codigo == KeyEvent.VK_ENTER){
                gp.estadodeljuego = gp.reanudar;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode();
        if(codigo == KeyEvent.VK_W){
            arribap = false;
        }
        if(codigo == KeyEvent.VK_S){
            abajop = false;
        }
        if(codigo == KeyEvent.VK_A){
            izquierdap = false;
        }
        if(codigo == KeyEvent.VK_D){
            derechap = false;
        }

    }
}
