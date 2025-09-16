package Main;

import entidad.jugador;
import objeto.Superobjeto;
import suelo.administradordesuelo;

import javax.swing.*;
import java.awt.*;

public class Panel_de_Juego extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int escala = 3;

    public final int tileSize = originalTileSize * escala;
    public final int tamanoColumna = 16;
    public final int tamanoFila = 12;
    public final int anchoPantalla = tileSize * tamanoColumna;
    public final int altoPantalla = tileSize * tamanoFila;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    administradordesuelo sueloM = new administradordesuelo(this);
    Teclado keyH = new Teclado();
    sonido se= new sonido();
    sonido musica = new sonido();
    public comprobar_colisiones comprobar = new comprobar_colisiones(this);
    public Activos aSetter = new Activos(this);
    public UI ui= new UI(this);
    Thread gameThread;

    public jugador jugador= new jugador(this,keyH);
    public Superobjeto obj[]=new Superobjeto[10];


    public Panel_de_Juego(){
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);


    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double dibujarIntervalo = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + dibujarIntervalo;

        while(gameThread != null){

            actualizar();

            repaint();

            try{
                double tiempoRestante = nextDrawTime - System.nanoTime();
                tiempoRestante = tiempoRestante/1000000;

                if(tiempoRestante <0){
                    tiempoRestante = 0;
                }
                Thread.sleep((long) tiempoRestante);

                nextDrawTime += dibujarIntervalo;
            } catch (InterruptedException e){
                e.printStackTrace();
            }


        }
    }
    public void actualizar(){

        jugador.actualizar();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        sueloM.dibujar(g2);

        for(int i=0;i< obj.length;i++){
            if(obj[i] !=null){
                obj[i].dibujar(g2,this);
            }
        }

        jugador.dibujar(g2);
        ui.dibujar(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        musica.setFile(i);
        musica.play();
        musica.loop();
    }
    public void stopMusic(){
        musica.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }


    }
