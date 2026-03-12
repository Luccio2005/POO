package tile;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager{
    GamePanel gp;
    BufferedImage worldMap[];
    public boolean miniMapOn = false;

    public Map(GamePanel gp){
        super(gp);
        this.gp = gp;
        createWorldMap();
    }
    public void createWorldMap(){

        int scale = 2; // tamaño de cada tile en el minimapa

        int width = gp.maxWorldCol * scale;
        int height = gp.maxWorldRow * scale;

        worldMap = new BufferedImage[gp.maxMap];

        for(int i = 0; i < gp.maxMap; i++){

            worldMap[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = worldMap[i].createGraphics();

            for(int row = 0; row < gp.maxWorldRow; row++){
                for(int col = 0; col < gp.maxWorldCol; col++){

                    int tileNum = mapTileNum[i][col][row];

                    g2.drawImage(
                            tile[tileNum].image,
                            col * scale,
                            row * scale,
                            scale,
                            scale,
                            null
                    );
                }
            }
            g2.dispose();
        }
    }
    public void drawFullMapScreen(Graphics2D g2){
        // background color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // drawmap
        int width = 500;
        int height = 500;
        int x = gp.screenWidth/2 - width/2;
        int y = gp.screenHeight/2 - height/2;
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
        // draw player
        double scale = (double) (gp.tileSize * gp.maxWorldCol)/width;
        int playerX = (int)(x + gp.player.worldX/scale);
        int playerY = (int)(y + gp.player.worldY/scale);
        int playerSize = (int)(gp.tileSize/scale);
        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
        //hint
        g2.setFont(gp.ui.arial_40.deriveFont(32f));
        g2.setColor(Color.white);
        g2.drawString("Presiona M", 750, 550);
    }
    public void drawMiniMap(Graphics2D g2){
        if(miniMapOn == true){
            // drawmap
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - width - 50;
            int y = 50;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[gp.currentMap],x, y, width, height, null);
            // draw player
            double scale = (double) (gp.tileSize * gp.maxWorldCol)/width;
            int playerX = (int)(x + gp.player.worldX/scale);
            int playerY = (int)(y + gp.player.worldY/scale);
            int playerSize = (int)(gp.tileSize/scale);
            g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
