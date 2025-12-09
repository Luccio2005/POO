package Main;

import ai.Encontrarcamino;
import ambiente.ambientemanager;
import entidad.entidad;
import entidad.jugador;
import suelo.administradordesuelo;
import suelo.interactivo.suelointeractivo;
import suelo.mapa;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Panel_de_Juego extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int escala = 3;

    public final int tileSize = originalTileSize * escala;
    public final int tamanoColumna = 20;
    public final int tamanoFila = 12;
    public final int anchoPantalla = tileSize * tamanoColumna;
    public final int altoPantalla = tileSize * tamanoFila;

    //public final int maxWorldCol = 155;
    //public final int maxWorldRow = 155;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxmap = 10;
    public int actualmapa = 0;
    // panatalla completa
    int anchopantalla2 = anchoPantalla;
    int altopantalla2 = altoPantalla;
    BufferedImage temppantalla;
    Graphics2D g2;
    public static boolean pantallacompletaon = false;

    int FPS = 60;

    public administradordesuelo sueloM = new administradordesuelo(this);
    public Teclado keyH = new Teclado(this);
    sonido se= new sonido();
    sonido musica = new sonido();
    public comprobar_colisiones comprobar = new comprobar_colisiones(this);
    public Activos aSetter = new Activos(this);
    public UI ui= new UI(this);
    public Eventos evento = new Eventos(this);
    public config config = new config(this);
    public Encontrarcamino pfinder = new Encontrarcamino(this);
    ambientemanager emanager = new ambientemanager(this);
    mapa mapa = new mapa(this);
    Thread gameThread;

    //entidad y objetos
    public jugador jugador= new jugador(this,keyH);
    public entidad obj[][]=new entidad[maxmap][20];
    public entidad npc[][] = new entidad[maxmap][10];
    public entidad enemigos[][] = new entidad[maxmap][20];
    public suelointeractivo itile[][] = new suelointeractivo[maxmap][50];
    public entidad proyectiles[][] = new entidad[maxmap][20];
    //public ArrayList<entidad> listaproyectil = new ArrayList<>();
    public ArrayList<entidad> listaparticula = new ArrayList<>();
    ArrayList<entidad> listaentidad = new ArrayList<>();

    //estados del juego
    public int estadodeljuego;
    public final int pantalladeinicio =0;
    public final int reanudar = 1;
    public final int pausar = 2;
    public final int dialogo = 3;
    public final int estadodepersonaje = 4;
    public final int estadodeopciones = 5;
    public final int estadogameover = 6;
    public final int estadotransicion = 7;
    public final int estadointercambio = 8;
    public final int estadodormir = 9;
    public final int estadomapa = 10;

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
        aSetter.setsuelointeractivo();
        emanager.setup();
        //playMusic(0);
        estadodeljuego = pantalladeinicio;
        temppantalla = new BufferedImage(anchoPantalla, altoPantalla, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) temppantalla.getGraphics();
        if(pantallacompletaon == true){
            setFullScreen();
        }
    }
    public void resetgame(boolean restart){
        jugador.setdefaultpositions();
        jugador.restaurarvidaymana();
        aSetter.setnpc();
        aSetter.setenemigos();
        if(restart == true){
            jugador.valorespredeterminados();
            aSetter.setObject();
            aSetter.setsuelointeractivo();
            emanager.iluminacion.resetdia();
        }
    }
    public void setFullScreen(){
        // get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        // get full screen width and height
        anchopantalla2 = Main.window.getWidth();
        altopantalla2 = Main.window.getHeight();
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
            dibujartemppantalla();
            dibujarpantalla();
            //repaint();

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
            for(int i =0; i< npc[1].length; i++){
                if(npc[actualmapa][i] !=null){
                    npc[actualmapa][i].actualizar();
                }
            }
            for(int i=0; i<enemigos[1].length; i++){
                if(enemigos[actualmapa][i] != null){
                    if(enemigos[actualmapa][i].vivo == true && enemigos[actualmapa][i].muriendo == false){
                        enemigos[actualmapa][i].actualizar();
                    }
                    if(enemigos[actualmapa][i].vivo == false){
                        enemigos[actualmapa][i].checkdrop();
                        enemigos[actualmapa][i] =null;
                    }
                }
            }
            for(int i=0; i < proyectiles[1].length; i++){
                if(proyectiles[actualmapa][i] != null){
                    if(proyectiles[actualmapa][i].vivo == true){
                        proyectiles[actualmapa][i].actualizar();
                    }
                    if(proyectiles[actualmapa][i].vivo == false){
                        proyectiles[actualmapa][i] = null;
                    }
                }
            }
            for(int i=0; i < listaparticula.size(); i++){
                if(listaparticula.get(i) != null){
                    if(listaparticula.get(i).vivo == true){
                        listaparticula.get(i).actualizar();
                    }
                    if(listaparticula.get(i).vivo == false){
                        listaparticula.remove(i);
                    }
                }
            }
            for(int i=0; i< itile[1].length; i++){
                if(itile[actualmapa][i] != null){
                    itile[actualmapa][i].actualizar();
                }
            }
            emanager.actualizar();
        }
        if(estadodeljuego == pausar){

        }
    }
    public void dibujartemppantalla(){
        //pantalla de inicio
        if(estadodeljuego == pantalladeinicio){
            ui.dibujar(g2);
        }
        //pantalla mapa
        else if(estadodeljuego == estadomapa){
            mapa.dibujarfullpantallademapa(g2);
        }
        else{
            sueloM.dibujar(g2);
            // suelo interactivo
            for(int i=0; i< itile[1].length; i++){
                if(itile[actualmapa][i] != null){
                    itile[actualmapa][i].dibujar(g2);
                }
            }
            // anadir entidades a la lista
            listaentidad.add(jugador);
            for(int i=0; i< npc[1].length;i++){
                if(npc[actualmapa][i] != null){
                    listaentidad.add(npc[actualmapa][i]);
                }
            }
            for(int i=0; i< obj[1].length;i++){
                if(obj[actualmapa][i] != null){
                    listaentidad.add(obj[actualmapa][i]);
                }
            }
            for(int i=0; i< enemigos[1].length;i++){
                if(enemigos[actualmapa][i] != null){
                    listaentidad.add(enemigos[actualmapa][i]);
                }
            }
            for(int i=0; i< proyectiles[1].length; i++){
                if(proyectiles[actualmapa][i] != null){
                    listaentidad.add(proyectiles[actualmapa][i]);
                }
            }
            for(int i=0; i< listaparticula.size(); i++){
                if(listaparticula.get(i) != null){
                    listaentidad.add(listaparticula.get(i));
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
            // ambiente
            emanager.dibujar(g2);
            // minimap
            mapa.dibujarminimapa(g2);
            // ui
            ui.dibujar(g2);
        }
    }
    public void dibujarpantalla(){
        Graphics g = getGraphics();
        g.drawImage(temppantalla, 0, 0, anchopantalla2, altopantalla2, null);
        g.dispose();
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
