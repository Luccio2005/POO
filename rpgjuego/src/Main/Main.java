package Main;
import javax.swing.*;

public class Main {
    public  static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JUEGO RPG");

        Panel_de_Juego paneldejuego = new Panel_de_Juego();
        window.add(paneldejuego);

        //Panel_de_Juego.config.cargarconfig();
        if(Panel_de_Juego.pantallacompletaon == true){
            window.setUndecorated(true);
        }
        

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        paneldejuego.setupGame();
        paneldejuego.startGameThread();
    }
}