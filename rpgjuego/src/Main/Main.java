package Main;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JUEGO RPG");

        Panel_de_Juego paneldejuego = new Panel_de_Juego();
        System.out.println("Tamaño de pantalla: " + paneldejuego.getAnchoPantalla() + "x" + paneldejuego.getAltoPantalla());


        window.add(paneldejuego);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        paneldejuego.setupGame();
        paneldejuego.startGameThread();


    }
}