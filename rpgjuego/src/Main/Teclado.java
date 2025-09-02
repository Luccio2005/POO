package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public boolean arribap, abajop, izquierdap, derechap;

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
