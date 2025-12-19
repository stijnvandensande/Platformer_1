package be.inf1.platformer1;

public class Square {
    
    protected double xCoord;
    protected double yCoord;
    protected int xSize;
    protected int ySize;
    
    public Square(double xCoord, double yCoord , int xSize, int ySize) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xSize = xSize;
        this.ySize = ySize;
    }
    
    public double getXCoord() {
        return xCoord;
    }
    
    public double getYCoord() {
        return yCoord;
    }
    
    public int getXSize() {
        return this.xSize;
    }
    
    public int getYSize() {
        return this.ySize;
    }
}
