package be.inf1.platformer1;

public class Speler extends Square {
    
    private double speed;
    private double acceleration;
    private int maxX;
    private int maxY;
    
    public Speler(double xCoord, double yCoord, int size, int maxX, int maxY) {
        super(xCoord, yCoord, size);
        this.maxX = maxX;
        this.maxY = maxY;
        this.speed = 0;
        this.acceleration = 0.3;
    }
    public void updateCoords() {
        speed += acceleration;
        
        yCoord += speed;
        if (yCoord > (maxY-this.size)) {
            speed = 0;
            yCoord = maxY-this.size;
        }
    }
    
    public void jump(double jumpSpeed) {
    if (yCoord >= (maxY-this.size)) {
        speed = -jumpSpeed;
    }
}

    void move(int i) {
        this.xCoord += i;
    }
}
