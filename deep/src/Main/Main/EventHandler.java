package Main;

import data.Progress;
import entity.Entity;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventMaster = new Entity(gp);
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }
    public void setDialogue(){
        eventMaster.dialogues[0][0] = "Caes en un pozo!!!";
        eventMaster.dialogues[1][0] = "Tomaste agua del lago. \ntu vida y mana se recupera!!\n " +
                "(El progreso ha sido guardado)";
    }
    public void checkEvent(){
        // check if the player character is more than 1 tile
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){
            if(hit(0,27,16,"right") == true){
                damagePit(gp.dialogueState);
            }
            else if(hit(0,50,52,"up") == true){
                healingPool(gp.dialogueState);
            }
            else if(hit(0,39,44,"any") == true){
                teleport(1,4,6, gp.indoor);
            }
            else if(hit(1,4,6,"any") == true){
                teleport(0,39,44, gp.outside);
            }
            else if(hit(1,2,2,"any") == true){
                teleport(1,49,11, gp.dungeon);
            }
            else if(hit(1,49,11,"any") == true){
                teleport(1,2,2, gp.indoor);
            }
            else if(hit(0,44,39,"any") == true){
                teleport(1,12,6, gp.indoor);
            }
            else if(hit(1,12,6,"any") == true){
                teleport(0,44,39, gp.outside);
            }
            else if(hit(0,56,39,"any") == true){
                teleport(1,20,6, gp.indoor);
            }
            else if(hit(1,20,6,"any") == true){
                teleport(0,56,39, gp.outside);
            }
            else if(hit(0,61,43,"any") == true){
                teleport(1,28,6, gp.indoor);
            }
            else if(hit(1,28,6,"any") == true){
                teleport(0,61,43, gp.outside);
            }
            else if(hit(0,61,56,"any") == true){
                teleport(1,28,14, gp.indoor);
            }
            else if(hit(1,28,14,"any") == true){
                teleport(0,61,56, gp.outside);
            }
            else if(hit(0,57,61,"any") == true){
                teleport(1,20,14, gp.indoor);
            }
            else if(hit(1,20,14,"any") == true){
                teleport(0,57,61, gp.outside);
            }
            else if(hit(0,44,61,"any") == true){
                teleport(1,12,14, gp.indoor);
            }
            else if(hit(1,12,14,"any") == true){
                teleport(0,44,61, gp.outside);
            }
            else if(hit(0,39,57,"any") == true){
                teleport(1,4,14, gp.indoor);
            }
            else if(hit(1,4,14,"any") == true){
                teleport(0,39,57, gp.outside);
            }
            else if(hit(0,76,17,"any") == true){
                teleport(1,40,11, gp.indoor);
            }
            else if(hit(1,40,11,"any") == true){
                teleport(0,76,17, gp.outside);
            }
            else if(hit(0,20,49,"any") == true){
                teleport(1,51,7, gp.indoor);
            }
            else if(hit(1,51,7,"any") == true){
                teleport(0,20,49, gp.outside);
            }
            else if(hit(1,51,4,"up") == true){
                speak(gp.npc[1][0]);
            }
            else if(hit(0,75,66,"any") == true){
                teleport(2,50,50, gp.dungeon);
            }
            else if(hit(2,50,50,"any") == true){
                teleport(0,75,66, gp.outside);
            }
            else if(hit(2,18,82,"any") == true){
                teleport(3,6,52, gp.dungeon);
            }
            else if(hit(3,6,52,"any") == true){
                teleport(2,18,82, gp.dungeon);
            }
            else if(hit(2,18,83,"any") == true){
                teleport(3,6,53, gp.dungeon);
            }
            else if(hit(3,6,53,"any") == true){
                teleport(2,18,83, gp.dungeon);
            }
            else if(hit(2,18,84,"any") == true){
                teleport(3,6,54, gp.dungeon);
            }
            else if(hit(3,6,54,"any") == true){
                teleport(2,18,84, gp.dungeon);
            }
            else if(hit(2,18,85,"any") == true){
                teleport(3,6,55, gp.dungeon);
            }
            else if(hit(3,6,55,"any") == true){
                teleport(2,18,85, gp.dungeon);
            }
            else if(hit(3,93,13,"any") == true){
                teleport(4,93,13, gp.dungeon);
            }
            else if(hit(4,93,13,"any") == true){
                teleport(3,93,13, gp.dungeon);
            }
            else if(hit(3,93,12,"any") == true){
                teleport(4,93,12, gp.dungeon);
            }
            else if(hit(4,93,12,"any") == true){
                teleport(3,93,12, gp.dungeon);
            }
            else if(hit(4,67,58,"any") == true){
                teleport(5,5,58, gp.dungeon);
            }
            else if(hit(5,5,58,"any") == true){
                teleport(4,67,58, gp.dungeon);
            }
            else if(hit(4,67,59,"any") == true){
                teleport(5,5,59, gp.dungeon);
            }
            else if(hit(5,5,59,"any") == true){
                teleport(4,67,59, gp.dungeon);
            }
            else if(hit(5,45,79,"any") == true){
                teleport(6,45,79, gp.dungeon);
            }
            else if(hit(6,45,79,"any") == true){
                teleport(5,45,79, gp.dungeon);
            }
            else if(hit(5,45,80,"any") == true){
                teleport(6,45,80, gp.dungeon);
            }
            else if(hit(6,45,80,"any") == true){
                teleport(5,45,80, gp.dungeon);
            }
            else if(hit(6,23,74,"any") == true){
                teleport(7,50,95, gp.dungeon);
            }
            else if(hit(7,50,95,"any") == true){
                teleport(6,23,74, gp.dungeon);
            }
            else if(hit(6,23,75,"any") == true){
                teleport(7,50,96, gp.dungeon);
            }
            else if(hit(7,50,96,"any") == true){
                teleport(6,23,75, gp.dungeon);
            }
            else if(hit(0,55,71,"any") == true){
                teleport(1,3,19, gp.dungeon);
            }
            else if(hit(1,3,19,"any") == true){
                teleport(0,55,71, gp.outside);
            }
            else if(hit(0,61,71,"any") == true){
                teleport(1,9,19, gp.dungeon);
            }
            else if(hit(1,9,19,"any") == true){
                teleport(0,61,71, gp.outside);
            }
            else if(hit(0,55,75,"any") == true){
                teleport(1,3,23, gp.dungeon);
            }
            else if(hit(1,3,23,"any") == true){
                teleport(0,55,75, gp.outside);
            }
            else if(hit(0,61,75,"any") == true){
                teleport(1,9,23, gp.dungeon);
            }
            else if(hit(1,9,23,"any") == true){
                teleport(0,61,75, gp.outside);
            }
            else if(hit(0,55,79,"any") == true){
                teleport(1,3,27, gp.dungeon);
            }
            else if(hit(1,3,27,"any") == true){
                teleport(0,55,79, gp.outside);
            }
            else if(hit(0,73,78,"any") == true){
                teleport(2,13,69, gp.dungeon);
            }
            else if(hit(2,13,69,"any") == true){
                teleport(0,73,78, gp.outside);
            }
            else if(hit(0,83,81,"any") == true){
                teleport(2,51,68, gp.dungeon);
            }
            else if(hit(2,51,68,"any") == true){
                teleport(0,83,81, gp.outside);
            }
            /*else if(hit(0, 125,140, "any") == true){
                Lord();
            }*/
        }

    }
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return  hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.playSE(6);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster,1);
            gp.player.life = gp.player.maxlife;
            gp.player.mana = gp.player.maxMana;
            //reiniciar monstruos
            //gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }
    public void teleport(int map, int col, int row, int area){
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }
    public void speak(Entity entity){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public void Lord(){
        if(gp.bossBattleOn == false && Progress.LordDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.Lord;
        }
    }
}
