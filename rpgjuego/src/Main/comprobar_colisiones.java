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
                suelonum1= gp.sueloM.mapaNum[gp.actualmapa][entidadizqcolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[gp.actualmapa][entidaddercolumna][entidadarrfila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colisioon = true;
                } break;
            case "down":
                entidadabafila = (entidadabamundoy + entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[gp.actualmapa][entidadizqcolumna][entidadabafila];
                suelonum2= gp.sueloM.mapaNum[gp.actualmapa][entidaddercolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colisioon = true;
                } break;
            case "left":
                entidadizqcolumna = (entidadizqmundox - entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[gp.actualmapa][entidadizqcolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[gp.actualmapa][entidadizqcolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colisioon = true;
                } break;
            case "right":
                entidaddercolumna = (entidaddermundox + entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[gp.actualmapa][entidaddercolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[gp.actualmapa][entidaddercolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colisioon = true;
                } break;
        }

    }
    public int comprobarobjeto(entidad entidad, boolean jugador){
        int indice = 999;
        for(int i =0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.actualmapa][i] !=null){
                entidad.areadecolision.x = entidad.mundox + entidad.areadecolision.x;
                entidad.areadecolision.y = entidad.mundoy + entidad.areadecolision.y;

                gp.obj[gp.actualmapa][i].areadecolision.x =  gp.obj[gp.actualmapa][i].mundox + gp.obj[gp.actualmapa][i].areadecolision.x;
                gp.obj[gp.actualmapa][i].areadecolision.y =  gp.obj[gp.actualmapa][i].mundoy + gp.obj[gp.actualmapa][i].areadecolision.y;

                switch (entidad.direccion){
                    case "up":
                        entidad.areadecolision.y -= entidad.velocidad;
                        break;
                    case "down":
                        entidad.areadecolision.y += entidad.velocidad;
                        break;
                    case "left":
                        entidad.areadecolision.x -= entidad.velocidad;
                        break;
                    case "right":
                        entidad.areadecolision.x += entidad.velocidad;
                        break;
                }
                if(entidad.areadecolision.intersects(gp.obj[gp.actualmapa][i].areadecolision)){
                    if(gp.obj[gp.actualmapa][i].colision == true){
                        entidad.colisioon = true;
                    }
                    if(jugador == true){
                        indice=i;
                    }
                }
                entidad.areadecolision.x = entidad.areadecolisionx;
                entidad.areadecolision.y = entidad.areadecolisiony;
                gp.obj[gp.actualmapa][i].areadecolision.x = gp.obj[gp.actualmapa][i].areadecolisionx;
                gp.obj[gp.actualmapa][i].areadecolision.y = gp.obj[gp.actualmapa][i].areadecolisiony;
            }
        }
        return indice;
    }
    // npc o monstruo colision
    public int comprobarentidad(entidad entidad, entidad[][] target){
        int indice = 999;
        for(int i =0;i< target[1].length;i++){
            if(target[gp.actualmapa][i] !=null){
                entidad.areadecolision.x = entidad.mundox + entidad.areadecolision.x;
                entidad.areadecolision.y = entidad.mundoy + entidad.areadecolision.y;

                target[gp.actualmapa][i].areadecolision.x =  target[gp.actualmapa][i].mundox + target[gp.actualmapa][i].areadecolision.x;
                target[gp.actualmapa][i].areadecolision.y =  target[gp.actualmapa][i].mundoy + target[gp.actualmapa][i].areadecolision.y;

                switch (entidad.direccion){
                    case "up":
                        entidad.areadecolision.y -= entidad.velocidad;
                        break;
                    case "down":
                        entidad.areadecolision.y += entidad.velocidad;
                        break;
                    case "left":
                        entidad.areadecolision.x -= entidad.velocidad;
                        break;
                    case "right":
                        entidad.areadecolision.x += entidad.velocidad;
                        break;
                }
                if(entidad.areadecolision.intersects(target[gp.actualmapa][i].areadecolision)){
                    if(target[gp.actualmapa][i] != entidad){
                        entidad.colisioon = true;
                        indice=i;
                    }
                }
                entidad.areadecolision.x = entidad.areadecolisionx;
                entidad.areadecolision.y = entidad.areadecolisiony;
                target[gp.actualmapa][i].areadecolision.x = target[gp.actualmapa][i].areadecolisionx;
                target[gp.actualmapa][i].areadecolision.y = target[gp.actualmapa][i].areadecolisiony;
            }
        }
        return indice;
    }
    public boolean comprobarjugador(entidad entidad){
        boolean contactojugador = false;
        entidad.areadecolision.x = entidad.mundox + entidad.areadecolision.x;
        entidad.areadecolision.y = entidad.mundoy + entidad.areadecolision.y;

        gp.jugador.areadecolision.x =  gp.jugador.mundox + gp.jugador.areadecolision.x;
        gp.jugador.areadecolision.y =  gp.jugador.mundoy + gp.jugador.areadecolision.y;

        switch (entidad.direccion){
            case "up":
                entidad.areadecolision.y -= entidad.velocidad;
                break;
            case "down":
                entidad.areadecolision.y += entidad.velocidad;
                break;
            case "left":
                entidad.areadecolision.x -= entidad.velocidad;
                break;
            case "right":
                entidad.areadecolision.x += entidad.velocidad;
                break;
        }if(entidad.areadecolision.intersects(gp.jugador.areadecolision)){
            entidad.colisioon = true;
            contactojugador = true;
        }
        entidad.areadecolision.x = entidad.areadecolisionx;
        entidad.areadecolision.y = entidad.areadecolisiony;
        gp.jugador.areadecolision.x = gp.jugador.areadecolisionx;
        gp.jugador.areadecolision.y = gp.jugador.areadecolisiony;
        return contactojugador;

    }
}
