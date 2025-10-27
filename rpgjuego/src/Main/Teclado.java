package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    Panel_de_Juego gp;
    public boolean arribap, abajop, izquierdap, derechap, enterp, disparop;
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
        // pantalla de inicio
        if(gp.estadodeljuego == gp.pantalladeinicio){
            pantalladeinicio(codigo);
        }
        //reanudar
        else if(gp.estadodeljuego == gp.reanudar){
            reanudar(codigo);
        }
        //pausar
        else if(gp.estadodeljuego == gp.pausar){
            pausar(codigo);
        }
        //dialogo
        else if(gp.estadodeljuego == gp.dialogo){
            dialogo(codigo);
        }
        // estado de personaje
        else if(gp.estadodeljuego == gp.estadodepersonaje){
            estadodepersonaje(codigo);
        }
    }
    public void pantalladeinicio(int codigo){
        if(codigo == KeyEvent.VK_W){
            gp.ui.numerodecomando--;
            if(gp.ui.numerodecomando <0){
                gp.ui.numerodecomando = 2;
            }
        }
        if(codigo == KeyEvent.VK_S){
            gp.ui.numerodecomando++;
            if(gp.ui.numerodecomando >2){
                gp.ui.numerodecomando =0;
            }
        }
        if(codigo == KeyEvent.VK_ENTER){
            if(gp.ui.numerodecomando ==0){
                gp.estadodeljuego = gp.reanudar;
                gp.playMusic(0);
            }
            if(gp.ui.numerodecomando ==1){

            }
            if(gp.ui.numerodecomando ==2){
                System.exit(0);
            }
        }
    }
    public void reanudar(int codigo){
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
        if(codigo == KeyEvent.VK_C){
            gp.estadodeljuego = gp.estadodepersonaje;
        }
        if(codigo == KeyEvent.VK_ENTER){
            enterp = true;
        }
        if(codigo == KeyEvent.VK_F){
            disparop = true;
        }
    }
    public void pausar(int codigo){
        if(codigo == KeyEvent.VK_P){
            gp.estadodeljuego = gp.reanudar;
        }
    }
    public void dialogo(int codigo){
        if(codigo == KeyEvent.VK_ENTER){
            gp.estadodeljuego = gp.reanudar;
        }
    }
    public void estadodepersonaje(int codigo){
        if(codigo == KeyEvent.VK_C){
            gp.estadodeljuego = gp.reanudar;
        }
        if(codigo == KeyEvent.VK_W){
            if(gp.ui.ranurafila !=0){
                gp.ui.ranurafila--;
                gp.playSE(9);
            }
        }
        if(codigo == KeyEvent.VK_A){
            if(gp.ui.ranuracol !=0){
                gp.ui.ranuracol--;
                gp.playSE(9);
            }
        }
        if(codigo == KeyEvent.VK_S){
            if(gp.ui.ranurafila !=3){
                gp.ui.ranurafila++;
                gp.playSE(9);
            }
        }
        if(codigo == KeyEvent.VK_D){
            if(gp.ui.ranuracol !=4){
                gp.ui.ranuracol++;
                gp.playSE(9);
            }
        }
        if(codigo == KeyEvent.VK_ENTER){
            gp.jugador.seleccionaritem();
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
        if(codigo == KeyEvent.VK_F){
            disparop = false;
        }

    }
}
