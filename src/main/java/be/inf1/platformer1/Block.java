package be.inf1.platformer1;

public class Block extends Square{  //is de basis
    
    
    protected String type;
    protected String color;
    

    public Block(double xCoord, double yCoord , int xSize, int ySize, String type) {
        super(xCoord, yCoord,xSize,ySize);
        this.type= type;
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
    
    public String getType() {
        return this.type;
    }


}