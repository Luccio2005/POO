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
            if(gp.estadodeljuego == gp.reanudar){
                gp.estadodeljuego = gp.pausar;
            }
            else if(gp.estadodeljuego == gp.pausar){
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
