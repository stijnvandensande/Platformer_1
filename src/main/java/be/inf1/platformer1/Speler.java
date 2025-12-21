package be.inf1.platformer1;

public class Speler extends Square {
    private double xSpeed;
    private double ySpeed;
    private final double acceleration;
    private double friction;
    private int maxX;
    private int maxY;
    private boolean onGround;
    
    
    
    public Speler(double xCoord, double yCoord, int xSize, int ySize,int maxX, int maxY) {
        super(xCoord, yCoord, xSize ,ySize);
        this.maxX = maxX;
        this.maxY = maxY;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.acceleration = 0.3;
        this.friction = 0.2;
        this.onGround = false;
    }
    
    
    public void updateCoords() {
        ySpeed += acceleration;
        yCoord += ySpeed;
        xCoord += xSpeed;
        // vertraging door wrijving
        if (xSpeed > 0) {
            xSpeed -= friction;
        } if (xSpeed < 0) {
            xSpeed += friction;
        }
        // check collition vloer
        if (yCoord >= (maxY-this.ySize)) {
            ySpeed = 0;
            yCoord = maxY-this.ySize;
            onGround = true;
        } else {
            onGround = false;
        }
        
        // check collition rechtermuur
        if ((xCoord > (maxX-this.xSize))) {
            xSpeed = 0;
            xCoord = maxX-this.xSize;
        }
        // check collition linkermuur
        if (xCoord < 0) {
            xSpeed = 0;
            xCoord = 0;
        }
        
    }
    
    
    
    
    public void jump(double jumpSpeed) {
    if (this.onGround) {
        ySpeed = -jumpSpeed;
    }

    void move(double i) {
        this.xSpeed += i;
    }
    
    public boolean checkCollision(Square other){
        if (this.xCoord < other.getXCoord() + other.getXSize() &&
           this.xCoord + this.xSize > other.getXCoord() &&
           this.yCoord < other.getYCoord() + other.getYSize() &&
           this.yCoord + this.ySize > other.getYCoord()){
            System.out.println("Is Touching");
            return true;
        } else{
        return false;
        }
    }
    public void Collision(Square other) {
    // check of ze elkaar raken
    if (checkCollision(other)) {
    // hoever de speler zijn rechterkant in de blokje zit
    // deze waarde zal klein zijn als de speler van links komt, maar heel groot als de speler van rechts komt
    double overlapLeft   = (this.xCoord + this.xSize) - other.getXCoord();
    // hoever de speler zijn linkerkant in het blokje zit
    double overlapRight  = (other.getXCoord() + other.getXSize()) - this.xCoord;
    // hoever de speler zijn onderkant in het blokje zit
    double overlapTop    = (this.yCoord + this.ySize) - other.getYCoord();
    // hoever de speler zijn  bovenkant in het blokje zit
    double overlapBottom = (other.getYCoord() + other.getYSize()) - this.yCoord;
    // kijk welke overlap het minst is sinds dat betekend dat het aan die kant raakt
    double minOverlap = Math.min(
        Math.min(overlapLeft, overlapRight),
        Math.min(overlapTop, overlapBottom)
    );

    // Bovenkant collision 
    if (minOverlap == overlapTop) {
        this.yCoord = other.getYCoord() - this.ySize;
        this.ySpeed = 0;
        this.onGround = true;
    }
    // onderkant collision
    else if (minOverlap == overlapBottom) {
        this.yCoord = other.getYCoord() + other.getYSize();
        this.ySpeed = 0;
    }
    // links collision
    else if (minOverlap == overlapLeft) {
        this.xCoord = other.getXCoord() - this.xSize;
        this.xSpeed = 0;
    }
    // rechts collision
    else if (minOverlap == overlapRight) {
        this.xCoord = other.getXCoord() + other.getXSize();
        this.xSpeed = 0;
    }
    }
    }
}
