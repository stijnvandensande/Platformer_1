package be.inf1.platformer1;

import java.util.ArrayList;


public class Level {
    
    private ArrayList<Block> blocks;
    private int boardWidth;
    private int boardHeight;
    private int respawnCoordX;
    private int respawnCoordY;

    
    
    public Level(int boardWidth,int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        
        blocks = new ArrayList<>();
        levelTest();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    
    private void levelTest() {
        
        this.respawnCoordX = 50;
        this.respawnCoordY = 200;
        
        // Blocks test
        blocks.add(new Block(100, boardHeight-50, 100, 10, 1));
        blocks.add(new Block(250, boardHeight-150, 100, 10, 1));
        blocks.add(new Block(400, 300, 100, 10, 1));
        blocks.add(new Block(600,boardHeight-500,20,480,1));

        // Spikes tesy
        blocks.add(new Block(300, 0, 50, 10, 99));
        blocks.add(new Block(350, 480, 50, 10, 99));
        
        
        //Glass Test
        blocks.add(new Block(800, boardHeight-50, 100, 10,4));
        
    }
    
    
    
    public int getRespawnX() {
        return respawnCoordX;
    }

    public int getRespawnY() {
        return respawnCoordY;
    }

    
    
    
    
    
    
}
