package Main;

import entidad.entidad;
import entidad.jugador;
import suelo.administradordesuelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Panel_de_Juego extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int escala = 3;

    public final int tileSize = originalTileSize * escala;
    public final int tamanoColumna = 16;
    public final int tamanoFila = 12;
    public final int anchoPantalla = tileSize * tamanoColumna;
    public final int altoPantalla = tileSize * tamanoFila;

    //public final int maxWorldCol = 155;
    //public final int maxWorldRow = 155;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    administradordesuelo sueloM = new administradordesuelo(this);
    public Teclado keyH = new Teclado(this);
    sonido se= new sonido();
    sonido musica = new sonido();
    public comprobar_colisiones comprobar = new comprobar_colisiones(this);
    public Activos aSetter = new Activos(this);
    public UI ui= new UI(this);
    public Eventos evento = new Eventos(this);
    Thread gameThread;

    //entidad y objetos
    public jugador jugador= new jugador(this,keyH);
    public entidad obj[]=new entidad[10];
    public entidad npc[] = new entidad[10];
    public entidad enemigos[] = new entidad[20];
    public ArrayList<entidad> listaproyectil = new ArrayList<>();
    ArrayList<entidad> listaentidad = new ArrayList<>();

    //estados del juego
    public int estadodeljuego;
    public final int pantalladeinicio =0;
    public final int reanudar = 1;
    public final int pausar =2;
    public final int dialogo = 3;
    public final int estadodepersonaje = 4;
    public Panel_de_Juego(){
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setnpc();
        aSetter.setenemigos();
        //playMusic(0);
        estadodeljuego = pantalladeinicio;


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
        if(estadodeljuego == reanudar){
            //jugador
            jugador.actualizar();
            //npc
            for(int i =0; i< npc.length; i++){
                if(npc[i] !=null){
                    npc[i].actualizar();
                }
            }
            for(int i=0; i<enemigos.length; i++){
                if(enemigos[i] != null){
                    if(enemigos[i].vivo == true && enemigos[i].muriendo == false){
                        enemigos[i].actualizar();
                    }
                    if(enemigos[i].vivo == false){
                        enemigos[i] =null;
                    }
                }
            }
            for(int i=0; i < listaproyectil.size(); i++){
                if(listaproyectil.get(i) != null){
                    if(listaproyectil.get(i).vivo == true){
                        listaproyectil.get(i).actualizar();
                    }
                    if(listaproyectil.get(i).vivo == false){
                        listaproyectil.remove(i);
                    }
                }
            }
        }
        if(estadodeljuego == pausar){

        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //pantalla de inicio
        if(estadodeljuego == pantalladeinicio){
            ui.dibujar(g2);

        }
        else{
            sueloM.dibujar(g2);
            // anadir entidades a la lista
            listaentidad.add(jugador);
            for(int i=0; i< npc.length;i++){
                if(npc[i] != null){
                    listaentidad.add(npc[i]);
                }
            }
            for(int i=0; i< obj.length;i++){
                if(obj[i] != null){
                    listaentidad.add(obj[i]);
                }
            }
            for(int i=0; i< enemigos.length;i++){
                if(enemigos[i] != null){
                    listaentidad.add(enemigos[i]);
                }
            }
            for(int i=0; i< listaproyectil.size(); i++){
                if(listaproyectil.get(i) != null){
                    listaentidad.add(listaproyectil.get(i));
                }
            }
            // sort
            Collections.sort(listaentidad, new Comparator<entidad>() {
                @Override
                public int compare(entidad e1, entidad e2) {
                    int result = Integer.compare(e1.mundoy, e2.mundoy);
                    return result;
                }
            });
            // dibujar entidades
            for(int i = 0; i< listaentidad.size(); i++){
                listaentidad.get(i).dibujar(g2);
            }
            // vaciar lista entidad
            listaentidad.clear();

            ui.dibujar(g2);
        }
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
