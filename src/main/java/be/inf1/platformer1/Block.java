package be.inf1.platformer1;

public class Block extends Square{  //is de basis
    
    
    protected int blockID;
    protected String color;
    

    public Block(double xCoord, double yCoord , int xSize, int ySize, int blockID) {
        super(xCoord, yCoord,xSize,ySize);
        this.blockID= blockID;
    }
    
    public double getBlockXCoord() {
        return xCoord;
    }
    
    public double getBlockYCoord() {
        return yCoord;
    }
    
    public int getBlockXSize() {
        return this.xSize;
    }
    
    public int getBlockYSize() {
        return this.ySize;
    }
    
    public int getBlockID() {
        return this.blockID;
    }
    

    
    public String getCollor(int blockID){
        
        if (blockID ==1 ){
            return "DARKGRAY";
        }
        if (blockID ==2 ){
            return "RED";
        }
        
        else{
            return("");
        }
    }



}