public class Main {
    public static void main(String[] args) {
        // Creamos una persona
        Persona persona1 = new Persona("Luis");

        // Creamos objetos de Saludo y Despedida
        Saludo saludo = new Saludo();
        Despedida despedida = new Despedida();

        // Usamos los métodos
        saludo.decirHola(persona1);
        despedida.decirAdios(persona1);
    }
}
