package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import javax.imageio.ImageIO;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 25;
        //attackArea.width = 36;
        //attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 6;
        direction = "down";
        //player status
        level = 1;
        maxlife = 6;
        life = maxlife;
        maxMana = 4;
        mana = maxMana;
        strength = 1; // mas strenght mas damage
        dexterity = 1; // mas destreza menos damage recibe
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public void getPlayerImage(){
        up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/right2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("/player/up2",gp.tileSize, gp.tileSize);
            attackUp2 = setup("/player/atq_up2", gp.tileSize*23/16, gp.tileSize*23/16);
            attackDown1 = setup("/player/down2", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/player/atq_down2", gp.tileSize*23/16, gp.tileSize*23/16);
            attackLeft1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/player/aleft2", gp.tileSize*23/16, gp.tileSize*23/16);
            attackRight1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/player/aright2", gp.tileSize*23/16, gp.tileSize*23/16);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/player/up2",gp.tileSize, gp.tileSize);
            attackUp2 = setup("/player/axe_up", gp.tileSize*23/16, gp.tileSize*23/16);
            attackDown1 = setup("/player/down2", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/player/axe_down", gp.tileSize*23/16, gp.tileSize*23/16);
            attackLeft1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/player/axe_left", gp.tileSize*23/16, gp.tileSize*23/16);
            attackRight1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/player/axe_right", gp.tileSize*23/16, gp.tileSize*23/16);
        }
    }
    public void update(){
        if (attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }else if(keyH.downPressed == true){
                direction = "down";
            }else if(keyH.leftPressed == true){
                direction = "left";
            }else if(keyH.rightPressed == true){
                direction = "right";
            }
            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //check object colision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // check nc colision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            // check monster olision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //check event
            gp.eHandler.checkEvent();
            // if collisiom is alse
            if(collisionOn == false && keyH.enterPressed == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            if(keyH.enterPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gp.keyH.enterPressed = false;
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter =0;
            }
        }
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true){
            projectile.set(worldX, worldY, direction, true, this);
            projectile.subtractResource(this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(10);
        }
        // thos need t be oputside
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxlife){
            life = maxlife;
        }if(mana > maxMana){
            mana = maxMana;
        }
    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }if(spriteCounter > 25){
            spriteNum = 1;
            standCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i){
        if(i != 999){
            //pickup only items
            if(gp.obj[i].type == type_pickupOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }else {
                String text;
                if(inventory.size() != maxInventorySize){
                    inventory.add(gp.obj[i]);
                    gp.playSE(1);
                    text = "Conseguiste una " + gp.obj[i].name + "!";
                } else{
                    text = "No puedes agarrar nada mas";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i !=999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false && gp.monster[i].dying == false){
                gp.playSE(6);
                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack){
        if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.playSE(5);
                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + "damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("mataste un " + gp.monster[i].name + "!");
                    gp.ui.addMessage("exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxlife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Eres nivel " + level + "!";
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemOnSlot();

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
            if(selectedItem.type == type_boots){
                selectedItem.use_boots(this);
                inventory.remove(itemIndex);
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = up1;
                    }if(spriteNum == 2){
                        image = up2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackUp1;
                    }if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = down1;
                    }if(spriteNum == 2){
                        image = down2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackDown1;
                    }if(spriteNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = left1;
                    }if(spriteNum == 2){
                        image = left2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackLeft1;
                    }if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = right1;
                    }if(spriteNum == 2){
                        image = right2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackRight1;
                    }if(spriteNum == 2){
                        image = attackRight2;
                    }
                }
                break;
        }
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //debug
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invencible:" +invincibleCounter,10,400);
        //lineas de colision
        //g2.setColor(Color.red);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
