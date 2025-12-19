package be.inf1.platformer1;

public class Square {
    
    private int xCoord;
    private double yCoord;
    private int size;
    private double speed;
    private double acceleration;
    
    public Square(int xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.speed = 0;
        this.acceleration = 0.1;
    }
    
    public void verhoogSpeed(int speed) {
        this.speed += speed;
    }
    
    public void generateCoords() {
        this.xCoord = (int) (Math.random()*490);
        this.yCoord = (int) (Math.random()*490);
    }
    
    public int getXCoord() {
        return xCoord;
    }
    
    public double getYCoord() {
        return yCoord;
    }
    
    
    
    public void updateCoords() {
        speed += acceleration;
        
        yCoord += speed;
        if (yCoord > 450) {
            speed = 0;
            yCoord = 450;
        }
    }
    
    public void jump(double jumpSpeed) {
    if (yCoord >= 450) {
        speed = -jumpSpeed;
    }
}

    void move(int i) {
        this.xCoord += i;
    }
}
