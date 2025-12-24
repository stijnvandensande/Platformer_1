package be.inf1.platformer1;

import java.util.ArrayList;


public class Level {
    
    private ArrayList<Block> blocks;
    private int boardWidth;
    private int boardHeight;
    private int respawnCoordX;
    private int respawnCoordY;

    
    
    public Level(int boardWidth,int boardHeight,int levelNumber) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        
        blocks = new ArrayList<>();
        levelTest(levelNumber);
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    
    private void levelTest(int levelNumber) {
        if (levelNumber == 1) {
        this.respawnCoordX = 50;
        this.respawnCoordY = 200;
        
        // Blocks test
        blocks.add(new Block(100, boardHeight-50, 100, 10, 1));
        blocks.add(new Block(250, boardHeight-150, 100, 10, 1));
        blocks.add(new Block(400, 300, 100, 10, 1));
        blocks.add(new Block(600,boardHeight-500,20,480,1));

        // Spikes tesy
        blocks.add(new BlockLava(300, 0, 50, 10));
        blocks.add(new BlockLava(350, 480, 50, 10));
        
        
        //Glass Test
        blocks.add(new BlockGlass(800, boardHeight-50, 100, 10));
        
        // Exit Test
        blocks.add(new ExitBlock(boardWidth-70,boardHeight-50,50,50));
        }
        
        if (levelNumber == 2) {
        this.respawnCoordX = 200;
        this.respawnCoordY = 200;
            
        blocks.add(new Block(200,boardHeight-30,100,10,1));
        }
    }
    
    
    
    public int getRespawnX() {
        return respawnCoordX;
    }

    public int getRespawnY() {
        return respawnCoordY;
    }

    
    
    
    
    
    
}
