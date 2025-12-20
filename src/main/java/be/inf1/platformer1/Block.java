package be.inf1.platformer1;

public class Block extends Square{
    
    
    protected double xCoord;
    protected double yCoord;
    protected int xSize;
    protected int ySize;
    protected int blockID;
    

    public Block(double xCoord, double yCoord , int xSize, int ySize, int blockID) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xSize = xSize;
        this.ySize = ySize;
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

}