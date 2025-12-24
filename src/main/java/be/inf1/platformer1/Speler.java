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
    private boolean isDead;
    private boolean reachedExit;
    public Block toRemoveBlock;
    
    
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
        this.airJumpsLeft = 3;
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
        revive();
    }
    
    
    
    public void updateCoords(Level level, Speler speler) {
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
            airJumpsLeft = 3;
        } else {
            onGround = false;
        }
        
        // check collition rechtermuur
        if ((xCoord >= (maxX-this.xSize))) {
            xSpeed = 0;
            xCoord = maxX-this.xSize;
            onRightWall = true;
            airJumpsLeft = 3;
        }
        // check collition linkermuur
        if (xCoord <= 0) {
            xSpeed = 0;
            xCoord = 0;
            onLeftWall = true;
            airJumpsLeft = 3;
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
            System.out.println("Is Touching");
            return true;
        } else{
        return false;
        }
    }
    public void Collision(Block other) {
    // check of ze elkaar raken
    if (checkCollision(other)) {
        
        
        if (other instanceof Block && ((Block) other).getBlockID() > 75) { // alle dodelijke block hebben een ID hoger dan deze waarde anders zou je moeten maken dat je 1, 2, 3, 4 allemaal moet cheken of ze dodenlijk zijn
        kill();// Also dat is met casting en behulp van chatGPT MAGIC
        return;
        }
        if (other instanceof Block && ((Block) other).getBlockID() == 2) {
            nextLevel();
            return;
        }
        double overlapX = Math.min(this.xCoord + this.xSize, other.getXCoord() + other.getXSize())
                - Math.max(this.xCoord, other.getXCoord());
        double overlapY = Math.min(this.yCoord + this.ySize, other.getYCoord() + other.getYSize())
                - Math.max(this.yCoord, other.getYCoord());
        if (overlapY < overlapX) {
        if (this.ySpeed > 0) { // falling
            if (other instanceof Block && ((Block) other).getBlockID() == 4) {
                if (ySpeed > 10) {
                    toRemoveBlock = other;
                }
            }
            this.yCoord = other.getYCoord() - this.ySize;
            this.ySpeed = 0;
            this.onGround = true;
            this.airJumpsLeft = 3;
        } else if (this.ySpeed < 0) { // jumping up
            this.yCoord = other.getYCoord() + other.getYSize();
            this.ySpeed = 0;
        }
        } else { // left/right
            if (xSpeed > 0) { // hit left side
                this.xCoord = other.getXCoord() - this.xSize;
                this.xSpeed = 0;
                this.onRightWall = true;
                this.airJumpsLeft = 3;
            } else if (xSpeed < 0) { // hit right side
                this.xCoord = other.getXCoord() + other.getXSize();
                this.xSpeed = 0;
                this.onLeftWall = true;
                this.airJumpsLeft = 3;
            } else if (this.xCoord == other.getXCoord() + other.getXSize()) {
                this.onLeftWall = true;
            } else if (this.xCoord + this.xSize == other.getXCoord()){
                this.onRightWall = true;
            }
        }
    }
    }

}

