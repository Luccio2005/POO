package ai;

public class node {
    node parent;
    public int col;
    public int fila;
    int gcost;
    int hcost;
    int fcost;
    boolean solido;
    boolean abrir;
    boolean comprobado;

    public node(int col, int fila){
        this.col = col;
        this.fila = fila;
    }
}
