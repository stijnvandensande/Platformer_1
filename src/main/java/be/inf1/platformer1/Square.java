package be.inf1.platformer1;

public class Square {
    
    protected double xCoord;
    protected double yCoord;
    protected int size;
    
    public Square(double xCoord, double yCoord , int size) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.size = size;
    }
    
    public void generateCoords() {
        this.xCoord = (int) (Math.random()*490);
        this.yCoord = (int) (Math.random()*490);
    }
    
    public double getXCoord() {
        return xCoord;
    }
    
    public double getYCoord() {
        return yCoord;
    }
    
    public int getSize() {
        return this.size;
    }
}
