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


    
    private void levelTest(int levelNumber) { // == de Level maker
        if (levelNumber == 1) {
            this.respawnCoordX = 10;
            this.respawnCoordY = boardHeight-120;
        
            // Blocks
            blocks.add(new BlockPlatform(0, boardHeight-100, 100, 100));    //spawn platform
            blocks.add(new BlockPlatform(0, 0, 100, boardHeight-150));      //eerste kolom
            blocks.add(new BlockPlatform(200, boardHeight-100, 100, 100));  //tweede platform
            blocks.add(new BlockPlatform(200, 200, 100, boardHeight-350));  //tweede kolom
            blocks.add(new BlockPlatform(300, boardHeight-100, 100,20));    //Eerste Jump platform
            blocks.add(new BlockPlatform(650, boardHeight-150, 100,20));    //Tweede Jump platform
            blocks.add(new BlockPlatform(1000, boardHeight-300, 175,20));   //Derde Jump platform
            blocks.add(new BlockPlatform(550, boardHeight-500, 150,20));    //Vierde Jump platform
            blocks.add(new BlockPlatform(300, 200, 100,20));                //laatste Jump platform
            blocks.add(new BlockPlatform(780,boardHeight-550,20,20));
            blocks.add(new BlockPlatform(900,boardHeight-550,20,20));
            // Lava
            blocks.add(new BlockLava(300,boardHeight-20,boardWidth-300,20));//botttom spikes
            blocks.add(new BlockLava(boardWidth-20, 0, 20, boardHeight));   //side spikes
            // Ice
            blocks.add(new BlockIce(300, 220, 20, boardHeight-370));
            
        
            //Glass
            blocks.add(new BlockGlass(100, boardHeight-100, 100, 20));      //overhang
            blocks.add(new BlockGlass(100, boardHeight-170, 100, 20));      //brug
            
            //JumpPad
            blocks.add(new BlockJumpPad(800,boardHeight-550,100,20));
        
            //Exit
            blocks.add(new ExitBlock(100,boardHeight-20,100,20));           //finish
        }

        if (levelNumber == 2) {
            this.respawnCoordX = 150;
            this.respawnCoordY = boardHeight-90 ;
            
            blocks.add(new BlockPlatform(0, boardHeight-100, 100, 100));    //Block
            blocks.add(new BlockPlatform(100, boardHeight-80, 150, 80));    //spawnplatform
            blocks.add(new BlockPlatform(250, boardHeight-30, 150, 10));    //Low hovering platform bij lava
            blocks.add(new BlockPlatform(100, boardHeight-500, 80, 300));   // linker muur
            
            
            
            
            
            blocks.add(new BlockGlass(900, 480, 20, 320));
            
            
            blocks.add(new BlockPlatform(900, boardHeight-300, 80, 300));   // rechter muur onder
            blocks.add(new BlockPlatform(900, 0, 80, 480));                 // rechter muur boven

            blocks.add(new BlockPlatform(300, boardHeight-160, 120, 20));   //eerste platform
            blocks.add(new BlockPlatform(500, boardHeight-260, 120, 20));   //Tweede platform
            blocks.add(new BlockPlatform(600, boardHeight-520, 100, 20));   //vierde platform
            blocks.add(new BlockPlatform(400, boardHeight-420, 100, 20));   //derde platform
            blocks.add(new BlockPlatform(80, 80, 150, 20));                 //Platform vanboven
            blocks.add(new BlockPlatform(730, boardHeight-30, 170, 10));   //Catch platform voor te landen na ice desroyed

            
            
            
            blocks.add(new BlockPlatform(1050, boardHeight-40, 125, 20));    //Eindplatform
            blocks.add(new ExitBlock(1085, boardHeight-80, 55, 40));         //Exit

            blocks.add(new BlockLava(0, boardHeight-20, boardWidth, 20));   //Lalalalava
            
            
            
            
            



            


            

            
        }
        
        if (levelNumber == 3) {
            this.respawnCoordX = 50;
            this.respawnCoordY = boardHeight-110;
            
            blocks.add(new BlockPlatform(0,boardHeight-100,200,100)); //spawnplatform
            blocks.add(new BlockIce(0,0,100,boardHeight-150)); // ice linkerkant
            blocks.add(new BlockIce(1000,200,boardWidth-1000,boardHeight-200)); // ice midden
            blocks.add(new BlockLava(200,boardHeight-50,boardWidth-200,50)); // lava onderkant
            blocks.add(new BlockLava(400,boardHeight-350,20,50)); // lava eerste platform
            blocks.add(new BlockLava(400,boardHeight-360,40,10)); // lava eerste platform bovenkant
            blocks.add(new BlockPlatform(420,boardHeight-350,20,50)); // platform eerste platform
            blocks.add(new BlockLava(700,boardHeight-550,20,50)); // lava tweede platform
            blocks.add(new BlockPlatform(720,boardHeight-550,20,50)); // lava tweede platform
            blocks.add(new BlockLava(700,boardHeight-560,40,10)); // lava tweede platform bovenkant
            blocks.add(new BlockPlatform(280,boardHeight-600,20,50)); // platform derde platform
            blocks.add(new BlockLava(300,boardHeight-600,20,50)); // lava derde platform
            blocks.add(new BlockLava(280,boardHeight-610,40,10)); // lava derde platform bovenkant
            blocks.add(new BlockLava(100,boardHeight-430,400,10)); // lava om route tussen platform 1 en 3 te stoppen
            blocks.add(new BlockPlatform(470,boardHeight-840,20,50)); // platform vierde platform
            blocks.add(new BlockLava(450,boardHeight-840,20,50)); // lava vierde platform
            blocks.add(new BlockLava(450,boardHeight-850,40,10)); // lava vierde platform bovenkant
            blocks.add(new ExitBlock(boardWidth-20,0,20,200)); // exit
        }
    }
    
    
    
    public int getRespawnX() {
        return respawnCoordX;
    }

    public int getRespawnY() {
        return respawnCoordY;
    }

    
    
    
    
    
    
}
