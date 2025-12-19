package be.inf1.platformer1;

public class Speler extends Square {
    private double xSpeed;
    private double ySpeed;
    private double acceleration;
    private double friction;
    private int maxX;
    private int maxY;
    
    public Speler(double xCoord, double yCoord, int xSize, int ySize,int maxX, int maxY) {
        super(xCoord, yCoord, xSize ,ySize);
        this.maxX = maxX;
        this.maxY = maxY;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.acceleration = 0.3;
        this.friction = 0.1;
    }
    public void updateCoords() {
        ySpeed += acceleration;
        yCoord += ySpeed;
        xCoord += xSpeed;
        if (xSpeed > 0) {
            xSpeed -= friction;
        } if (xSpeed < 0) {
            xSpeed += friction;
        }
        if (yCoord > (maxY-this.ySize)) {
            ySpeed = 0;
            yCoord = maxY-this.ySize;
        }
    }
    
    public void jump(double jumpSpeed) {
    if (yCoord >= (maxY-this.ySize)) {
        ySpeed = -jumpSpeed;
    }
}

    void move(double i) {
        this.xSpeed += i;
    }
    
     public boolean checkCollision(Speler Other){
        if (((Other.getXCoord()<(this.xCoord))&&(this.xCoord)<(Other.getXCoord()+(Other.getXSize())))
                && ((Other.getYCoord()<(this.yCoord))&&(this.yCoord)<(Other.getYCoord()+(Other.getYSize())))){
            System.out.println("Is Touchting");
            return true;
        } else{
        return false;
        }
    }
}
