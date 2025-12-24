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

public class PrimaryController extends TimerTask{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootView;
   
    
    private Speler speler;
    private final int boardSizeX = 1200;
    private final int boardSizeY = 800;
    private final int jumpStrength = 6;
    private final double baseSpeed = 1;
    private double speedMultiplier= 1;
    private double movementSpeed = baseSpeed * speedMultiplier;
    private ArrayList<Level> levels;
    private int levelNumber;
    private Level level1;
    private Level level2;
    private Level level3;
    
    public int getBoardSizeX() {
        return this.boardSizeX;
    }
    
    public int getBoardSizeY() {
        return this.boardSizeY;
    }
    


    
    @FXML
    void initialize() {
        levels = new ArrayList<Level>();
        level1 = new Level(boardSizeX,boardSizeY,1);
        level2 = new Level(boardSizeX,boardSizeY,2);
        level3 = new Level(boardSizeX,boardSizeY,3);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levelNumber = 0;
        rootView.setFocusTraversable(true);
        Platform.runLater(() -> rootView.requestFocus());
        rootView.setOnKeyPressed(this::handleKeyPress);
        speler = new Speler(50,50,10,10,boardSizeX,boardSizeY);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 1000/60);
        updateView();
    }

@FXML
    void handleKeyPress(KeyEvent e) {
        switch(e.getCode()) {
            case SPACE:
                if (speler.isOnGround()) {
                speler.jump(jumpStrength);
                } else if (speler.isOnWall()){
                speler.wallJump(jumpStrength);
                } else {
                speler.airJump(jumpStrength);
                }
                break;
            case Q:
                speler.move(-movementSpeed);
                break;
            case D:
                speler.move(movementSpeed);
                break;
        }
    }
    
    

      
    
    
    
public void updateView() {
    rootView.getChildren().clear();
    //check of player leeft
    if(speler.IsDead()){
        speler.respawnPlayer(levels.get(levelNumber));
        speler.revive();
    }
    
    //achtergrond
    Rectangle backgroundView = new Rectangle(0,0,boardSizeX,boardSizeY);
    backgroundView.setFill(Color.GRAY); //Is iets aangenamer dan ROOD
    rootView.getChildren().add(backgroundView);
    
    
    //Level bouwen
    for (Block b : levels.get(levelNumber).getBlocks()) {
        Rectangle r = new Rectangle(b.getXCoord(), b.getYCoord(), b.getXSize(), b.getYSize());
        
        if (b.getBlockID() == 1) r.setFill(Color.DARKGRAY);             //Blocks
        if (b.getBlockID() == 99) r.setFill(Color.RED);                  //Spikes
        if (b.getBlockID() == 3) r.setFill(Color.web("#fa6400"));       //Lava
        if (b.getBlockID() == 4) r.setFill(Color.web("#8aefff"));       //Glass
        //if (b.getBlockID() == 5) r.setFill(Color.web("#8aefff"));       //Teleport
        
        
        
        
        rootView.getChildren().add(r);
    }
        
    Rectangle squareView = new Rectangle(speler.getXCoord(),speler.getYCoord(),speler.getXSize(),speler.getYSize());
    squareView.setFill(Color.BLUE);
    rootView.getChildren().add(squareView);
    
    }

    @Override
    public void run() {
    speler.updateCoords(levels.get(levelNumber), speler);
    if (speler.getReachedExit()) {
        speler.resetReachedExit();
        levelNumber++;
        if (levelNumber >= levels.size()) {
        levelNumber = 0;
        }
        speler.respawnPlayer(levels.get(levelNumber));
    }

    Platform.runLater(this::updateView);
    }



}
