package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/worldV3.txt", 0);
        loadMap("/maps/interior01.txt", 1);
    }

    public void getTileImage(){
        setup(0,"002",false);
        setup(1,"032",true);
        setup(2,"019",true);
        setup(3,"017",false);
        setup(4,"016",true);
        setup(5,"003",false);
        setup(6,"002",false);
        setup(7,"032",true);
        setup(8,"019",true);
        setup(9,"017",false);

        setup(10,"001",false);
        setup(11,"002",false);
        setup(12,"018",true);
        setup(13,"019",true);
        setup(14,"020",true);
        setup(15,"021",true);
        setup(16,"022",true);
        setup(17,"023",true);
        setup(18,"024",true);
        setup(19,"025",true);
        setup(20,"026",true);
        setup(21,"027",true);
        setup(22,"028",true);
        setup(23,"029",true);
        setup(24,"030",true);
        setup(25,"031",true);
        setup(26,"003",false);
        setup(27,"004",false);
        setup(28,"005",false);
        setup(29,"006",false);
        setup(30,"007",false);
        setup(31,"008",false);
        setup(32,"009",false);
        setup(33,"010",false);
        setup(34,"011",false);
        setup(35,"012",false);
        setup(36,"013",false);
        setup(37,"014",false);
        setup(38,"015",false);
        setup(39,"017",false);
        setup(40,"032",true);
        setup(41,"016",true);
        setup(42,"033",false);
        setup(43,"034",false);
        setup(44,"035",true);
    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(String filePath, int map){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
        // dibujar camino
        if(drawPath == true){
            g2.setColor(new Color(255,0,0,70));
            for(int i = 0; i < gp.pFinder.pathList.size(); i++){
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}
