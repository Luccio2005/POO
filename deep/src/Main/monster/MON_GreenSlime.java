package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxlife = 4;
        life = maxlife;
        attack = 2;
        defense = 0;
        exp = 1;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        motion1_duration = 5;
        motion2_duration = 25;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        if(onPath == true){
            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 10, 100);
            // me sigue busca la direccion para ir
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //check if itshoot a projectile
            checkShootOrNot(200, 30);
        }else{
            checkStartChasingOrNot(gp.player, 5, 100);
            getRandoDirection();
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop(){
        // cast a die
        int i = new Random().nextInt(100)+1;
        //set the monster drop
        if(i < 50){
            dropItem(new OBJ_Coin_Bronze(gp));
        }if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }if(i >= 75 && i < 100){
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
