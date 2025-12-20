package be.inf1.platformer1;

import java.util.ArrayList;


public class Level {
    
    private ArrayList<Block> blocks;

    
    
    public Level() {
        blocks = new ArrayList<>();
        levelTest();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    
    private void levelTest() {

        // Blocks test
        blocks.add(new Block(100, 400, 100, 20, 1));
        blocks.add(new Block(250, 350, 100, 20, 1));
        blocks.add(new Block(400, 300, 100, 20, 1));

        // Spikes tesy
        blocks.add(new Block(300, 480, 50, 20, 2));
        blocks.add(new Block(350, 480, 50, 20, 2));
    }
}
