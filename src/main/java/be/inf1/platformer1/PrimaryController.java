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
    
    private Label timerText;
    
    private Label levelTimesText;
    
    private double milliseconden;
    
    private boolean qPressed = false;
    private boolean dPressed = false;
    private boolean spacePressed = false;
    
    private String levelTimesTextContent;
   
    
    private Speler speler;
    private final int boardSizeX = 1920;
    private final int boardSizeY = 1000;
    private final int jumpStrength = 6;
    private final double baseSpeed = 0.3;
    private double speedMultiplier= 1;
    private double movementSpeed = baseSpeed * speedMultiplier;
    private ArrayList<Level> levels;
    private int levelNumber;
    private Level level1;
    private Level level2;
    private Level level3;
    private ArrayList<String> completedLevelsTimes;
    
    public int getBoardSizeX() {
        return this.boardSizeX;
    }
    
    public int getBoardSizeY() {
        return this.boardSizeY;
    }
    


    
    @FXML
    void initialize() {
        levelTimesTextContent = "";
        levelText = new Label("Level 1");
        timerText = new Label("Time: 0");
        levelTimesText = new Label("Completed levels: ");
        levelTimesText.setLayoutX(20);
        levelTimesText.setLayoutY(100);
        levelTimesText.setTextFill(Color.WHITE);
        levelTimesText.setFont(new Font(20));
        levelText.setLayoutX(20);
        levelText.setLayoutY(20);
        levelText.setTextFill(Color.WHITE);
        levelText.setFont(new Font(20));
        timerText.setLayoutX(20);
        timerText.setLayoutY(60);
        timerText.setTextFill(Color.WHITE);
        timerText.setFont(new Font(20));

        hudPane.getChildren().add(levelText);
        hudPane.getChildren().add(timerText);
        hudPane.getChildren().add(levelTimesText);
        levels = new ArrayList<Level>();
        level1 = new Level(boardSizeX,boardSizeY,1);
        level2 = new Level(boardSizeX,boardSizeY,2);
        level3 = new Level(boardSizeX,boardSizeY,3);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levelNumber = 0;
        completedLevelsTimes = new ArrayList<String>();
        completedLevelsTimes.add("Completed Levels:");
        rootView.setFocusTraversable(true);
        Platform.runLater(() -> rootView.requestFocus());
        rootView.setOnKeyPressed(this::handleKeyPress);
        rootView.setOnKeyReleased(this::handleKeyRelease);
        speler = new Speler(10,boardSizeY-120,10,10,boardSizeX,boardSizeY);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 1000/60);
        updateView();
    }

    @FXML
    void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                qPressed = true;
                break;
            case D:
                dPressed = true;
                break;
            case SPACE:
                spacePressed = true;
                break;
            case R:
                restartGame();
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
            case SPACE:
                spacePressed = false;
                break;
        }
    }
    
    public void restartGame() {
        levelNumber = 0;
        speler.respawnPlayer(levels.get(levelNumber));
        completedLevelsTimes.clear();
        completedLevelsTimes.add("Completed Levels:");
        milliseconden = 0;
    }
      
    
    
    
public void updateView() {
    gamePane.getChildren().clear();
    levelText.setText("Level " + (levelNumber + 1));
    milliseconden += 1000/60;
    timerText.setText("Time: " + milliseconden/1000);
    for (String i : completedLevelsTimes) {
        levelTimesTextContent+=i;
    }
    levelTimesText.setText(levelTimesTextContent);
    levelTimesTextContent="";
    //check of player leeft
    if(speler.IsDead()){
        speler.respawnPlayer(levels.get(levelNumber));
        speler.revive();
        speler.resetSpeed();
    }
    
    //achtergrond
    Rectangle backgroundView = new Rectangle(0,0,boardSizeX,boardSizeY);
    backgroundView.setFill(Color.GRAY); //Is iets aangenamer dan ROOD
    gamePane.getChildren().add(backgroundView);
    
    
    //Level bouwen
    for (Block b : levels.get(levelNumber).getBlocks()) {
        Rectangle r = new Rectangle(b.getXCoord(), b.getYCoord(), b.getXSize(), b.getYSize());
        
        if (b.getType() == "platform") r.setFill(Color.DARKGRAY);             //Blocks
        if (b.getType() == "exit") r.setFill(Color.BLACK);                //Finish
        if (b.getType() == "lava") r.setFill(Color.web("#fa6400"));       //Lava
        if (b.getType() == "spikes") r.setFill(Color.RED);                 //Spikes
        if (b.getType() == "lava") r.setFill(Color.web("#fa6400"));       //Lava
        if (b.getType() == "glass") r.setFill(Color.web("#8aefff"));       //Glass
        if (b.getType() == "jumpPad") r.setFill(Color.web("#af32ed"));       //JumpPad
        if (b.getType() == "slime") r.setFill(Color.web("#65FF00"));       //Slime
        
        
        
        
        
        gamePane.getChildren().add(r);
    }
        
    Rectangle squareView = new Rectangle(speler.getXCoord(),speler.getYCoord(),speler.getXSize(),speler.getYSize());
    squareView.setFill(Color.BLUE);
    gamePane.getChildren().add(squareView);
    
    }

    @Override
    public void run() {
    if (qPressed) {
        speler.move(-movementSpeed);
    }

    if (dPressed) {
        speler.move(movementSpeed);
    }

    if (spacePressed) {
        if (speler.isOnGround()) {
            speler.jump(jumpStrength);
        } else if (speler.isOnWall()) {
            speler.wallJump(jumpStrength);
        } else {
            speler.airJump(jumpStrength);
        }
        spacePressed = false; // important
    }
    speler.updateCoords(levels.get(levelNumber), speler);
    levels.get(levelNumber).getBlocks().remove(speler.getToRemoveBlock());
    if (speler.getReachedExit()) {
        speler.resetReachedExit();
        completedLevelsTimes.add("\nLevel " + (levelNumber + 1) + ": " + milliseconden/1000);
        levelNumber++;
        if (levelNumber >= levels.size()) {
        levelNumber = 0;
        }
        speler.respawnPlayer(levels.get(levelNumber));
    }

    Platform.runLater(this::updateView);
    }



}
