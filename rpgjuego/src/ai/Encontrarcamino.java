package ai;

import Main.Panel_de_Juego;
import entidad.entidad;

import java.util.ArrayList;

public class Encontrarcamino {
    Panel_de_Juego gp;
    node[][] node;
    ArrayList<node> abrirlista = new ArrayList<>();
    public ArrayList<node> caminolista = new ArrayList<>();
    node startnode, goalnode, actualnode;
    boolean metalograda = false;
    int step = 0;

    public Encontrarcamino(Panel_de_Juego gp){
        this.gp = gp;
        instanciarnode();
    }
    public void instanciarnode(){
        node = new node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int fila = 0;

        while(col < gp.maxWorldCol && fila < gp.maxWorldRow){
            node[col][fila] = new node(col,fila);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                fila++;
            }
        }
    }
    public void resetnode(){
        int col = 0;
        int fila = 0;

        while(col < gp.maxWorldCol && fila < gp.maxWorldRow){
            // reset open ,checked and solid state
            node[col][fila].abrir = false;
            node[col][fila].comprobado = false;
            node[col][fila].solido = false;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                fila++;
            }
        }
        // reset other setting
        abrirlista.clear();
        caminolista.clear();
        metalograda = false;
        step = 0;
    }
    public void setnode(int startcol, int startfila, int metacol, int metafila, entidad entidad){
        resetnode();
        //set start and goal node
        startnode = node[startcol][startfila];
        actualnode = startnode;
        goalnode = node[metacol][metafila];
        abrirlista.add(actualnode);
        int col = 0;
        int fila = 0;

        while (col < gp.maxWorldCol && fila < gp.maxWorldRow){
            //set solid node
            // check tiles
            int tilenum = gp.sueloM.mapaNum[gp.actualmapa][col][fila];
            if(gp.sueloM.suelo[tilenum].colision == true){
                node[col][fila].solido = true;
            }
            // check interactive tiles
            for(int i =0; i< gp.itile[1].length;i++){
                if(gp.itile[gp.actualmapa][i] != null && gp.itile[gp.actualmapa][i].destructible == true){
                    int itcol = gp.itile[gp.actualmapa][i].mundox/gp.tileSize;
                    int itfila = gp.itile[gp.actualmapa][i].mundoy/gp.tileSize;
                    node[itcol][itfila].solido = true;
                }
            }
            // set cost
            getCost(node[col][fila]);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                fila++;
            }
        }
    }
    public void getCost(node node){
        // g cost
        int distanciax = Math.abs(node.col - startnode.col);
        int distanciay = Math.abs(node.fila - startnode.fila);
        node.gcost = distanciax + distanciay;
        // h cost
        distanciax = Math.abs(node.col - goalnode.col);
        distanciay = Math.abs(node.fila - goalnode.fila);
        // f cost
        node.fcost = node.gcost + node.hcost;
    }
    public boolean buscar(){
        while (metalograda == false && step < 500){
            int col = actualnode.col;
            int fila = actualnode.fila;
            // comprobar actual node
            actualnode.comprobado = true;
            abrirlista.remove(actualnode);
            // open the up node
            if(fila - 1 >= 0){
                opennode(node[col][fila-1]);
            }
            // open the left node
            if(col - 1 >= 0){
                opennode(node[col-1][fila]);
            }
            // open the down node
            if(fila + 1 < gp.maxWorldRow){
                opennode(node[col][fila+1]);
            }
            // open the right node
            if(fila + 1 < gp.maxWorldCol){
                opennode(node[col+1][fila]);
            }
            // encontrar el mejor camino
            int mejornodeindice = 0;
            int mejornodefcost = 999;
            for(int i = 0; i < abrirlista.size(); i++){
                if(abrirlista.get(i).fcost < mejornodefcost){
                    mejornodeindice = i;
                    mejornodefcost = abrirlista.get(i).fcost;
                }
                else if(abrirlista.get(i).fcost == mejornodefcost){
                    if(abrirlista.get(i).gcost < abrirlista.get(mejornodeindice).gcost){
                        mejornodeindice = i;
                    }
                }
            }
            if(abrirlista.size() == 0){
                break;
            }
            actualnode = abrirlista.get(mejornodeindice);
            if(actualnode == goalnode){
                metalograda = true;
                trackthepath();
            }
            step++;
        }
        return metalograda;
    }
    public void opennode(node node){
        if(node.abrir == false && node.comprobado == false && node.solido == false){
            node.abrir = true;
            node.parent = actualnode;
            abrirlista.add(node);
        }
    }
    public void trackthepath(){
        node actual = goalnode;
        while (actual != startnode){
            caminolista.add(0,actual);
            actual = actual.parent;
        }
    }
}
