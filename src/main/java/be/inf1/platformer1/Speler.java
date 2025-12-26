package be.inf1.platformer1;

public class Speler extends Square {
    private double xSpeed;
    private double ySpeed;
    private final double gravity;
    private double friction;
    private int maxX;
    private int maxY;
    private boolean onGround;
    private boolean onLeftWall;
    private boolean onRightWall;
    private int airJumpsLeft;
    protected int amountOfAirJumps;
    private boolean isDead;
    private boolean reachedExit;
    public Block toRemoveBlock;
    private double prevX;
    private double prevY;
    
    
    public Speler(double xCoord, double yCoord, int xSize, int ySize,int maxX, int maxY) {
        super(xCoord, yCoord, xSize ,ySize);
        this.maxX = maxX;
        this.maxY = maxY;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.gravity = 0.15;
        this.friction = 0.1;
        this.onGround = false;
        this.onLeftWall = false;
        this.onRightWall = false;
        this.amountOfAirJumps = 1;
        
        this.reachedExit = false;
    }
    
    public boolean isOnGround() {
        return onGround;
    }
    
    public boolean isOnWall() {
        return (onLeftWall || onRightWall);
    }
    
    public boolean IsDead(){
        return isDead;
    }
    
    public void kill(){ //Kill player
        isDead = true;
    }
    
    public void revive(){ //revive player
        isDead = false;
    }
    
    public void nextLevel() {
        reachedExit = true;
    }
    
    public boolean getReachedExit() {
        return reachedExit;
    }
    
    public void resetReachedExit() {
        this.reachedExit = false;
    }
    
    //Respawn Setters
    public void setXCoord(double x){
        this.xCoord = x;
    }
    
    public void setYCoord(double y){
        this.yCoord = y;
    }
    
    public Block getToRemoveBlock() {
        return toRemoveBlock;
    }
    
    public void resetSpeed(){
        xSpeed=0;
        ySpeed=0;
        onGround= false;
        onLeftWall= false;
        onRightWall=false;
    }
    
    public void respawnPlayer(Level level) {
        setXCoord(level.getRespawnX());
        setYCoord(level.getRespawnY());
        ySpeed = 0;
        xSpeed = 0;
        revive();
    }
    
    
    public void bounce(String axis){
        if (axis == "vertical") {
        this.ySpeed*=-1; 
        } else if (axis == "horizontal") {
        this.xSpeed*=-1;
        }
    }
    
    public void jumpPadLaunch() {
        this.ySpeed = -15;
    }
    
    
    
    
    public void updateCoords(Level level, Speler speler) {
        prevX = xCoord;
        prevY = yCoord;
        ySpeed += gravity;
        yCoord += ySpeed;
        xCoord += xSpeed;
        onLeftWall = false;
        onRightWall = false;
        // vertraging door wrijving
        if (xSpeed > 0) {
            xSpeed -= friction;
        if (xSpeed < 0) {
            xSpeed = 0;
        }
        } else if (xSpeed < 0) {
        xSpeed += friction;
        if (xSpeed > 0) {
            xSpeed = 0;
        }
        }
        // check collition vloer
        if (yCoord >= (maxY-this.ySize)) {
            ySpeed = 0;
            yCoord = maxY-this.ySize;
            onGround = true;
            airJumpsLeft = amountOfAirJumps;
        } else {
            onGround = false;
        }
        
        // check collition rechtermuur
        if ((xCoord >= (maxX-this.xSize))) {
            xSpeed = 0;
            xCoord = maxX-this.xSize;
            onRightWall = true;
            airJumpsLeft = amountOfAirJumps;
        }
        // check collition linkermuur
        if (xCoord <= 0) {
            xSpeed = 0;
            xCoord = 0;
            onLeftWall = true;
            airJumpsLeft = amountOfAirJumps;
        }
        
        for (Block b : level.getBlocks()) {
            speler.Collision(b);
        }
    }
    
    
    
    
    public void jump(double jumpSpeed) {
        if (this.onGround) {
            ySpeed = -jumpSpeed;
        }
    }
    
    public void wallJump(double jumpSpeed) {
        if (this.onRightWall) {
            ySpeed = -jumpSpeed;
            xSpeed = -(jumpSpeed/2);
        } else if (this.onLeftWall) {
            ySpeed = -jumpSpeed;
            xSpeed = (jumpSpeed/2);
        }
    }
    
    public void airJump(double jumpSpeed) {
        if (airJumpsLeft > 0 && !isOnWall()) {
            ySpeed = -jumpSpeed;
            airJumpsLeft--;
        }
    }

    public void move(double i) {
        xSpeed += i;
    }
    
    public boolean checkCollision(Square other){
        if (this.xCoord <= other.getXCoord() + other.getXSize() &&
           this.xCoord + this.xSize >= other.getXCoord() &&
           this.yCoord <= other.getYCoord() + other.getYSize() &&
           this.yCoord + this.ySize >= other.getYCoord()){
            return true;
        } else{
        return false;
        }
    }
    public void Collision(Block other) {
    if (checkCollision(other)) {
    String type = other.getType();

    // Dodelijk
    if (type == "lava") {
        kill();
        return;
    }

    // Finish
    if (type == "exit") {
        nextLevel();
        return;
    }

    // landen op platform
    if (prevY + ySize <= other.getYCoord()) {

        // Breekbaar glas
        if (type == "glass" && ySpeed > 8) {
            toRemoveBlock = other;
            ySpeed *= 0.85;
            return;
        }
        
        if (type == "slime") {
        bounce("vertical");
        return;
        }
        if (type == "jumpPad") {
        jumpPadLaunch();
        return;
        }

        yCoord = other.getYCoord() - ySize;
        ySpeed = 0;
        onGround = true;
        airJumpsLeft = amountOfAirJumps;
        return;
    }

    // kop tegen platform
    if (prevY >= other.getYCoord() + other.getYSize()) {
        if (type == "slime") {
        bounce("vertical");
        return;
        }
        yCoord = other.getYCoord() + other.getYSize();
        ySpeed = 0;
        return;
    }

    // linkerkant platform
    if (prevX + xSize <= other.getXCoord()) {
        if (type == "slime") {
        bounce("horizontal");
        return;
        }
        xCoord = other.getXCoord() - xSize;
        xSpeed = 0;
        onRightWall = true;
        airJumpsLeft = amountOfAirJumps;
        return;
    }

    // rechterkant platform
    if (prevX >= other.getXCoord() + other.getXSize()) {
        if (type == "slime") {
        bounce("horizontal");
        return;
        }
        xCoord = other.getXCoord() + other.getXSize();
        xSpeed = 0;
        onLeftWall = true;
        airJumpsLeft = amountOfAirJumps;
    }
    }
}

}

