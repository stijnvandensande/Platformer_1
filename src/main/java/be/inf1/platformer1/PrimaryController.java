package be.inf1.platformer1;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class PrimaryController extends TimerTask{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootView;
    
    @FXML
    private AnchorPane gamePane;

    @FXML
    private AnchorPane hudPane;
    
    private Label levelText;
    
    private ArrayList<Double> currentSplits;
    private ArrayList<Double> personalBestSplits;
    
    private Label timerText;
    
    private Label levelTimesText;
    
    private Label deathCounter;
    
    private Label authorTimeText;
    
    private Label personalBestText;
    
    private double personalBestTime;
    
    private double milliseconden;
    
    private boolean qPressed = false;
    private boolean dPressed = false;
    private boolean spacePressed = false;
    private boolean timerStarted = false;
    private boolean gameCompleted = false;
    
    private String levelTimesTextContent;
    
    
   
    
    private Speler speler;
    private final int playerSize = 10;
    private final int boardSizeX = 1920;
    private final int boardSizeY = 1000;
    private final int jumpStrength = 6;
    private final double baseSpeed = 1;
    private double speedMultiplier= 1;
    private double movementSpeed = baseSpeed * speedMultiplier;
    private ArrayList<Level> levels;
    private int levelNumber;
    private Level level1;
    private Level level2;
    private Level level3;
    private Level level4;
    private Level level5;
    private ArrayList<String> completedLevelsTimes;
    
    public int getBoardSizeX() {
        return this.boardSizeX;
    }
    
    public int getBoardSizeY() {
        return this.boardSizeY;
    }
    


    
    @FXML
    void initialize() {
        currentSplits = new ArrayList<>();
        personalBestSplits = new ArrayList<>();

        levelTimesTextContent = "";
        levelText = new Label("Level 1");
        timerText = new Label("Time: 0");
        authorTimeText = new Label("Time to beat: 33.96");
        personalBestText = new Label("PB: No Time");
        deathCounter = new Label("0");
        levelTimesText = new Label("Completed levels: ");
        levelTimesText.setLayoutX(20);
        levelTimesText.setLayoutY(220);
        levelTimesText.setTextFill(Color.WHITE);
        levelTimesText.setFont(new Font(20));
        deathCounter.setLayoutX(20);
        deathCounter.setLayoutY(100);
        deathCounter.setTextFill(Color.WHITE);
        deathCounter.setFont(new Font(20));
        levelText.setLayoutX(20);
        levelText.setLayoutY(20);
        levelText.setTextFill(Color.WHITE);
        levelText.setFont(new Font(20));
        timerText.setLayoutX(20);
        timerText.setLayoutY(60);
        timerText.setTextFill(Color.WHITE);
        timerText.setFont(new Font(20));
        personalBestText.setLayoutX(20);
        personalBestText.setLayoutY(180);
        personalBestText.setTextFill(Color.WHITE);
        personalBestText.setFont(new Font(20));
        authorTimeText.setLayoutX(20);
        authorTimeText.setLayoutY(140);
        authorTimeText.setTextFill(Color.WHITE);
        authorTimeText.setFont(new Font(20));

        hudPane.getChildren().add(levelText);
        hudPane.getChildren().add(timerText);
        hudPane.getChildren().add(levelTimesText);
        hudPane.getChildren().add(deathCounter);
        hudPane.getChildren().add(personalBestText);
        hudPane.getChildren().add(authorTimeText);
        levels = new ArrayList<Level>();
        level1 = new Level(boardSizeX,boardSizeY,1);
        level2 = new Level(boardSizeX,boardSizeY,2);
        level3 = new Level(boardSizeX,boardSizeY,3);
        level4 = new Level(boardSizeX,boardSizeY,4);
        level5 = new Level(boardSizeX,boardSizeY,5);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
        levelNumber = 1;
        completedLevelsTimes = new ArrayList<String>();
        completedLevelsTimes.add("Completed Levels:");
        rootView.setFocusTraversable(true);
        Platform.runLater(() -> rootView.requestFocus());
        rootView.setOnKeyPressed(this::handleKeyPress);
        rootView.setOnKeyReleased(this::handleKeyRelease);
        speler = new Speler(level1.getRespawnX(),level1.getRespawnY(),playerSize,playerSize,boardSizeX,boardSizeY);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 1000/60);
        updateView();
    }

    @FXML
    void handleKeyPress(KeyEvent e) {
        if (!gameCompleted) {
        startTimer(); 
        }
        switch (e.getCode()) {
            case Q:
                qPressed = true;
                break;
            case D:
                dPressed = true;
                break;
            case SPACE:
                if (speler.isOnGround()) {
                    speler.jump(jumpStrength);
                } else if (speler.isOnWall()) {
                    speler.wallJump(jumpStrength);
                } else {
                    speler.airJump(jumpStrength);
                }      
                break;
            case R:
                restartGame();
                break;
            case H:
                //HardcoreMode = true;
                restartGame();
                break;
            case LEFT:
                if (levelNumber > 1){
                    levelNumber -= 1;
                    speler.respawnPlayer(levels.get(levelNumber - 1));
                }
                break;
                
            case RIGHT:
                if (levelNumber < levels.size()){
                    levelNumber += 1;
                    speler.respawnPlayer(levels.get(levelNumber - 1));
                }
                break;
        }
    } 
    
    @FXML
    void handleKeyRelease(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                qPressed = false;
                break;
            case D:
                dPressed = false;
                break;
        }
    }
    
    public void resetBlocks() {
        levels.clear();
        levels.add(new Level(boardSizeX, boardSizeY, 1));
        levels.add(new Level(boardSizeX, boardSizeY, 2));
        levels.add(new Level(boardSizeX, boardSizeY, 3));
        levels.add(new Level(boardSizeX, boardSizeY, 4));
        levels.add(new Level(boardSizeX, boardSizeY, 5));
    }
    
    public void restartGame() {
        levelNumber = 1;
        speedMultiplier = 1;
        speler.resetDeathCount();
        speler.respawnPlayer(levels.get(levelNumber-1));
        completedLevelsTimes.clear();
        completedLevelsTimes.add("Completed Levels:");
        gameCompleted=false;
        milliseconden = 0;
        timerStarted= false;
        currentSplits.clear();
        levels.clear();
        levels.add(new Level(boardSizeX, boardSizeY, 1));
        levels.add(new Level(boardSizeX, boardSizeY, 2));
        levels.add(new Level(boardSizeX, boardSizeY, 3));
        levels.add(new Level(boardSizeX, boardSizeY, 4));
        levels.add(new Level(boardSizeX, boardSizeY, 5));
    }
      
    
    
    public void startTimer(){
        timerStarted=true;
    }
    
    public void doubleSpeedMultiplier() {
        speedMultiplier*=2;
    }
    
    
    public void updateView() {
        gamePane.getChildren().clear();
        if (levelNumber < levels.size()) {
        levelText.setText("Level " + (levelNumber));
        } else {
        levelText.setText("The End");
        }
        if(timerStarted){   //start alleen timer als bij keypress
            milliseconden += 1000/60;
        }
        timerText.setText("Time: " + milliseconden/1000);
        for (String i : completedLevelsTimes) {
            levelTimesTextContent+=i;
        }
        levelTimesText.setText(levelTimesTextContent);
        levelTimesTextContent="";
        deathCounter.setText("Deaths: " + speler.getDeathCount() + "");
        //check of player leeft
        if(speler.IsDead()){
            speler.respawnPlayer(levels.get(levelNumber-1));
            speedMultiplier = 1;
            resetBlocks();
            speler.revive();
            speler.resetSpeed();
        }
    
        //achtergrond
        Rectangle backgroundView = new Rectangle(0,0,boardSizeX,boardSizeY);
        backgroundView.setFill(Color.GRAY); //Is iets aangenamer dan ROOD
        gamePane.getChildren().add(backgroundView);
    
        
        if (gameCompleted) {
            Rectangle endBG = new Rectangle(0, 0, boardSizeX, boardSizeY);
            endBG.setFill(Color.BLACK);

            Label winText = new Label("YOU WIN!");
            winText.setTextFill(Color.WHITE);
            winText.setFont(new Font(80));
            winText.setLayoutX(boardSizeX / 2 - 200);
            winText.setLayoutY(boardSizeY / 2 - 100);

            Label retryText = new Label("Press R to restart");
            retryText.setTextFill(Color.GRAY);
            retryText.setFont(new Font(40));
            retryText.setLayoutX(boardSizeX / 2 - 200);
            retryText.setLayoutY(boardSizeY / 2);

            gamePane.getChildren().add(endBG);
            gamePane.getChildren().add(winText);
            gamePane.getChildren().add(retryText);
            if (milliseconden < personalBestTime || personalBestTime == 0) {
                personalBestTime = milliseconden;
                personalBestSplits = new ArrayList<>(currentSplits);
                currentSplits.clear();
            }
            personalBestText.setText("PB: " + personalBestTime/1000);
            return;
        }
        
        
        
        
    
        //Level bouwen
        for (Block b : levels.get(levelNumber-1).getBlocks()) {
            Rectangle r = new Rectangle(b.getXCoord(), b.getYCoord(), b.getXSize(), b.getYSize());
        
            if ("platform".equals(b.getType())) r.setFill(Color.DARKGRAY);           //Blocks
            if ("exit".equals(b.getType())) r.setFill(Color.BLACK);                  //Finish
            if ("lava".equals(b.getType())) r.setFill(Color.web("#fa6400"));         //Lava
            if ("spikes".equals(b.getType())) r.setFill(Color.RED);                  //Spikes
            if ("lava".equals(b.getType())) r.setFill(Color.web("#fa6400"));         //Lava
            if ("glass".equals(b.getType())){                                        //Glass
                r.setFill(Color.web("#8aefff"));
                r.setOpacity(0.35);
            }       
            
            if ("jumpPad".equals(b.getType())) r.setFill(Color.web("#af32ed"));      //JumpPad
            if ("slime".equals(b.getType())) r.setFill(Color.web("#65FF00"));        //Slime
            if ("ice".equals(b.getType())) r.setFill(Color.web("#89a9d6"));          //Ice
            if ("food".equals(b.getType())) r.setFill(Color.web("#e6e60b"));          //Ice
            
        
        
        
        
        
        gamePane.getChildren().add(r);
        }
        
        Rectangle squareView = new Rectangle(speler.getXCoord(),speler.getYCoord(),speler.getXSize(),speler.getYSize());
        squareView.setFill(Color.BLUE);
        gamePane.getChildren().add(squareView);
    
    }

    @Override
    public void run() {
    movementSpeed = baseSpeed * speedMultiplier;
    if (qPressed) {
        speler.move(-movementSpeed);
    }

    if (dPressed) {
        speler.move(movementSpeed);
    }

    speler.updateCoords(levels.get(levelNumber-1), speler);
    levels.get(levelNumber-1).getBlocks().remove(speler.getToRemoveBlock());
    if (speler.getFoodEaten()) {
        doubleSpeedMultiplier();
        speler.resetFoodEaten();
    }
    if (speler.getReachedExit()) {
        speler.resetReachedExit();
        speedMultiplier = 1;
        currentSplits.add(milliseconden);
        completedLevelsTimes.add("\nLevel " + (levelNumber) + ": " + milliseconden/1000);
        if (!personalBestSplits.isEmpty()) {
        completedLevelsTimes.add(" " + ((currentSplits.get(levelNumber-1) - personalBestSplits.get(levelNumber-1))/1000));
        }
        levelNumber++;
        
        if ((levelNumber) >= (levels.size())) {
            gameCompleted=true;
            timerStarted=false;
            return;
        }
        speler.respawnPlayer(levels.get(levelNumber-1));
    }

    Platform.runLater(this::updateView);
    }
}