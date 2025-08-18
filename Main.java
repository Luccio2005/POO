public class Main {
    public static void main(String[] args) {
        // Creamos una persona
        Persona persona1 = new Persona("Luis");

        // Creamos el saludo
        Saludo saludo = new Saludo();

        // Usamos el método de la clase Saludo
        saludo.decirHola(persona1);
    }
}