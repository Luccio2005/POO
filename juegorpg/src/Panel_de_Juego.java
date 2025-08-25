import javax.swing.*;
import java.awt.*;

public class Panel_de_Juego extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int escala = 3;

    final int tileSize = originalTileSize * escala;
    final int tamanoColumna = 16;
    final int tamanoFila = 12;
    final int anchoPantalla = tileSize * tamanoColumna;
    final int altoPantalla = tileSize * tamanoFila;

    Thread gameThread;
    int FPS = 60;

    public Panel_de_Juego(){
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

    }
    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public int getTileSize() {
        return tileSize;
}
