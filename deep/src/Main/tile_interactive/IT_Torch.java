/*package tile_interactive;

import Main.GamePanel;
import entity.Entity;

import java.awt.*;

public class IT_Torch extends InteractiveTile {

    GamePanel gp;

    public IT_Torch(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_light;
        name = "Antorcha";
        solidArea = new Rectangle(0,0,0,0);
        collision = false;

        worldX = col * gp.tileSize;
        worldY = row * gp.tileSize;

        down1 = setup("/objects/torch1", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/torch2", gp.tileSize, gp.tileSize);

        destructible = true;
        collision = false;
        life = 1;

        lightRadius = 150; // radio de luz
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon.type == type_axe;
    }

    @Override
    public void playSE() {
        gp.playSE(11);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        return null; // desaparece completamente
    }

    @Override
    public Color getParticleColor() {
        return new Color(255, 180, 50);
    }

    @Override
    public int getParticleSize() {
        return 4;
    }

    @Override
    public int getParticleSpeed() {
        return 1;
    }

    @Override
    public int getParticleMaxLife() {
        return 15;
    }

    @Override
    public void update() {
        super.update();

        spriteCounter++;

        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (spriteNum == 1) {
            image = down1;
        } else {
            image = down2;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, null);

    }
}*/
