package Main;

import entidad.entidad;

public class comprobar_colisiones {
    Panel_de_Juego gp;
    public comprobar_colisiones(Panel_de_Juego gp){
        this.gp = gp;
    }
    public void comprobarsuelo(entidad entidad){
        int entidadizqmundox = entidad.mundox + entidad.areadecolision.x;
        int entidaddermundox = entidad.mundox + entidad.areadecolision.x + entidad.areadecolision.width;
        int entidadarrmundoy = entidad.mundoy + entidad.areadecolision.y;
        int entidadabamundoy = entidad.mundoy + entidad.areadecolision.y + entidad.areadecolision.height;

        int entidadizqcolumna = entidadizqmundox/gp.tileSize;
        int entidaddercolumna = entidaddermundox/gp.tileSize;
        int entidadarrfila = entidadarrmundoy/gp.tileSize;
        int entidadabafila = entidadabamundoy/gp.tileSize;

        int suelonum1, suelonum2;

        switch (entidad.direccion){
            case "up":
                entidadarrfila = (entidadarrmundoy - entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[entidadizqcolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[entidaddercolumna][entidadarrfila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colision = true;
                } break;
            case "down":
                entidadabafila = (entidadabamundoy + entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[entidadizqcolumna][entidadabafila];
                suelonum2= gp.sueloM.mapaNum[entidaddercolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colision = true;
                } break;
            case "left":
                entidadizqcolumna = (entidadizqmundox - entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[entidadizqcolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[entidadizqcolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colision = true;
                } break;
            case "right":
                entidaddercolumna = (entidaddermundox - entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[entidaddercolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[entidaddercolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colision = true;
                } break;
        }

    }
}
