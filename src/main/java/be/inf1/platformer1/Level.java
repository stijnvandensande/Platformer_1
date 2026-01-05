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
            blocks.add(new BlockGlass(100, boardHeight-100, 100, 20));      //brug
            blocks.add(new BlockGlass(100, boardHeight-170, 100, 20));      //overhang
            
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
           
            
            blocks.add(new BlockPlatform(900, boardHeight-300, 80, 300));   // rechter muur onder
            blocks.add(new BlockGlass(900, 480, 20, 700-480));              //glassmuur
            blocks.add(new BlockPlatform(900, 0, 80, 480));                 // rechter muur boven

            
            blocks.add(new BlockPlatform(300, boardHeight-160, 120, 20));   //eerste platform
            blocks.add(new BlockLava(500, boardHeight-260, 120, 20));       //Tweede platform
            blocks.add(new BlockPlatform(600, boardHeight-520, 100, 20));   //vierde platform
            blocks.add(new BlockPlatform(400, boardHeight-420, 100, 20));   //derde platform
            blocks.add(new BlockPlatform(80, 80, 150, 20));                 //Platform vanboven

            blocks.add(new BlockPlatform(1050+500, boardHeight-40, 125, 20));    //Eindplatform
            blocks.add(new ExitBlock(1085+500, boardHeight-80, 55, 40));         //Exit

            blocks.add(new BlockLava(0, boardHeight-20, boardWidth, 20));   //Lalalalava
            
   
        }

        if (levelNumber == 3) {
            this.respawnCoordX = 1050+500;
            this.respawnCoordY = boardHeight-30;
            
            blocks.add(new BlockPlatform(1050+500-62, boardHeight-20, 125, 20));//beginplatform
            blocks.add(new BlockPlatform(1050+500-62-20, boardHeight-30, 20, 20));//stopper voor mocht je nog q aan het indrukken zijn zodat je niet onmiddelijk dood gaat.... ja is ook een skill issue
            
            blocks.add(new BlockLava(0, boardHeight-10, boardWidth, 10));       //ground lava
            blocks.add(new BlockLava(boardWidth-20, 0, 20, boardHeight));       //side lava            
            blocks.add(new BlockIce(0, 0, 10, boardHeight));                    // linker wall anticheat
            
            //jump1
            blocks.add(new BlockPlatform(250, boardHeight-135, 500, 20));       //Slimeplatform
            blocks.add(new BlockSlime(250, boardHeight-150, 500, 15));          //SLIME
            blocks.add(new BlockLava(400, boardHeight-500, 20, 315+10));        //anti cheese lava
            blocks.add(new BlockPlatform(100, boardHeight-500, 80, 300));       // linker muur
            
            //jump2
            blocks.add(new BlockLava(0, boardHeight-600, 260, 40));             // Roof lava jump 1
            blocks.add(new BlockPlatform(100, 0, 80, boardHeight-600));         // linker muur boven
            blocks.add(new BlockLava(400, boardHeight-500, 20, 315));           //anti cheese lava
            
            //jump3
            blocks.add(new BlockPlatform(750, boardHeight-135-250, 500, 20));   //Slimeplatform in het midden
            blocks.add(new BlockSlime(750, boardHeight-400, 500, 15));          //SLIME midden groot
            blocks.add(new BlockLava(400, boardHeight-500-315, 20, 315));       //anti cheese lava
            
            blocks.add(new BlockSlime(960, boardHeight-470, 80, 15));           //SLIME klein midden boven
            blocks.add(new BlockLava(990, 0, 20, boardHeight-470));             //anti cheese lava
            blocks.add(new BlockLava(990, boardHeight-400-20, 20, 20));         //VIERKANTE Hindernis tussen slimes
            
            blocks.add(new BlockPlatform(boardWidth-200, 160+150, 180, 20));         //Exit platform
            blocks.add(new ExitBlock(boardWidth-120, 120+150, 40, 40));              //Exit


        
        }
        
        
        if (levelNumber == 4) { //still work in progress

            this.respawnCoordX = boardWidth-120;
            this.respawnCoordY = 120;
            
            blocks.add(new BlockPlatform(boardWidth-183, 145, 125, 20));        //beginplatform

            blocks.add(new BlockIce(0, 0, 10, boardHeight));                    // linker anticheat
            blocks.add(new BlockIce(boardWidth-10, 0, 10, boardHeight));        // rechter anticheat
            
            blocks.add(new BlockSlime(450,boardHeight-20,boardWidth-450,20));   //Onder Slime
            
            blocks.add(new BlockLava(450, 0, 80, 700));                         // kolom 1
            blocks.add(new BlockLava(750, 220, 80, 650));                       // kolom 2
            blocks.add(new BlockLava(1050, 0, 80, 700));                        // kolom 3
            blocks.add(new BlockLava(1350, 260, 80, 600));                      // kolom 4

            blocks.add(new BlockJumpPad(boardWidth-80, boardHeight-20, 80, 20));//recover als nodig
            
            
            
            /*
            blocks.add(new BlockLava(100, boardHeight-120, 500, 120));

            blocks.add(new BlockLava(420, boardHeight-180, 80, 60));
            blocks.add(new BlockLava(160, boardHeight-380, 300, 40));


            blocks.add(new BlockJumpPad(820, boardHeight-220, 120, 30));

            blocks.add(new BlockJumpPad(1300, boardHeight-280, 120, 30));

            blocks.add(new BlockPlatform(200, boardHeight-220, 280, 40));
            blocks.add(new ExitBlock(260, boardHeight-260, 55, 40));
        */
        }
        
        
        
        
        
        if (levelNumber == 5) {
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
