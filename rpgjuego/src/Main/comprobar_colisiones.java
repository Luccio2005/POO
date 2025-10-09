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
                entidaddercolumna = (entidaddermundox + entidad.velocidad)/gp.tileSize;
                suelonum1= gp.sueloM.mapaNum[entidaddercolumna][entidadarrfila];
                suelonum2= gp.sueloM.mapaNum[entidaddercolumna][entidadabafila];
                if(gp.sueloM.suelo[suelonum1].colision == true || gp.sueloM.suelo[suelonum2].colision == true){
                    entidad.colision = true;
                } break;
        }

    }
    public int comprobarobjeto(entidad entidad, boolean jugador){
        int indice = 999;
        for(int i =0;i<gp.obj.length;i++){
            if(gp.obj[i] !=null){
                entidad.areadecolision.x = entidad.mundox + entidad.areadecolision.x;
                entidad.areadecolision.y = entidad.mundoy + entidad.areadecolision.y;

                gp.obj[i].areadecolision.x =  gp.obj[i].mundox + gp.obj[i].areadecolision.x;
                gp.obj[i].areadecolision.y =  gp.obj[i].mundoy + gp.obj[i].areadecolision.y;

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
                if(entidad.areadecolision.intersects(gp.obj[i].areadecolision)){
                    if(gp.obj[i].colision == true){
                        entidad.colision = true;
                    }
                    if(jugador == true){
                        indice=i;
                    }
                }
                entidad.areadecolision.x = entidad.areadecolisionx;
                entidad.areadecolision.y = entidad.areadecolisiony;
                gp.obj[i].areadecolision.x = gp.obj[i].areadecolisionx;
                gp.obj[i].areadecolision.y = gp.obj[i].areadecolisiony;
            }
        }
        return indice;
    }
    // npc o monstruo colision
    public int comprobarentidad(entidad entidad, entidad[] target){
        int indice = 999;
        for(int i =0;i< target.length;i++){
            if(target[i] !=null){
                entidad.areadecolision.x = entidad.mundox + entidad.areadecolision.x;
                entidad.areadecolision.y = entidad.mundoy + entidad.areadecolision.y;

                target[i].areadecolision.x =  target[i].mundox + target[i].areadecolision.x;
                target[i].areadecolision.y =  target[i].mundoy + target[i].areadecolision.y;

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
                if(entidad.areadecolision.intersects(target[i].areadecolision)){
                    if(target[i] != entidad){
                        entidad.colision = true;
                        indice=i;
                    }
                }
                entidad.areadecolision.x = entidad.areadecolisionx;
                entidad.areadecolision.y = entidad.areadecolisiony;
                target[i].areadecolision.x = target[i].areadecolisionx;
                target[i].areadecolision.y = target[i].areadecolisiony;
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
            entidad.colision = true;
            contactojugador = true;
        }
        entidad.areadecolision.x = entidad.areadecolisionx;
        entidad.areadecolision.y = entidad.areadecolisiony;
        gp.jugador.areadecolision.x = gp.jugador.areadecolisionx;
        gp.jugador.areadecolision.y = gp.jugador.areadecolisiony;
        return contactojugador;

    }
}
