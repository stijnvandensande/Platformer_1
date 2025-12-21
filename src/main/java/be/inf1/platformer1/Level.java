package be.inf1.platformer1;

import java.util.ArrayList;


public class Level {
    
    private ArrayList<Block> blocks;
    private int boardWidth;
    private int boardHeight;
    
    
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

        // Blocks test
        blocks.add(new Block(100, boardHeight-50, 100, 10, 1));
        blocks.add(new Block(250, boardHeight-150, 100, 10, 1));
        blocks.add(new Block(400, 300, 100, 10, 1));

        // Spikes tesy
        blocks.add(new Block(300, 0, 50, 10, 2));
        blocks.add(new Block(350, 480, 50, 10, 2));
    }
}
