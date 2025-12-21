package be.inf1.platformer1;
import java.net.URL;
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
    private Square square = new Square(200,650,30,30);
    private final int boardSizeX = 700;
    private final int boardSizeY = 700;
    
    public int getBoardSizeX() {
        return this.boardSizeX;
    }
    
    public int getBoardSizeY() {
        return this.boardSizeY;
    }

    @FXML
    void initialize() {
        rootView.setFocusTraversable(true);
        Platform.runLater(() -> rootView.requestFocus());
        rootView.setOnKeyPressed(this::handleKeyPress);
        rootView.setOnKeyPressed(this::handleKeyPress);
        speler = new Speler(50,50,10,10,boardSizeX,boardSizeY);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 32);
        updateView();
    }

@FXML
    void handleKeyPress(KeyEvent e) {
        switch(e.getCode()) {
            case SPACE:
                speler.jump(10);
                break;
            case Q:
                speler.move(-1);
                break;
            case D:
                speler.move(1);
                break;
        }
    }
    
public void updateView() {
        rootView.getChildren().clear();
        Rectangle backrgroundView = new Rectangle(0,0,boardSizeX,boardSizeY);
        backrgroundView.setFill(Color.RED);
        rootView.getChildren().add(backrgroundView);
        
        Rectangle spelerView = new Rectangle(speler.getXCoord(),speler.getYCoord(),speler.getXSize(),speler.getYSize());
        spelerView.setFill(Color.BLUE);
        rootView.getChildren().add(spelerView);
        
        Rectangle squareView = new Rectangle(square.getXCoord(),square.getYCoord(),square.getXSize(),square.getYSize());
        squareView.setFill(Color.PURPLE);
        rootView.getChildren().add(squareView);
    }

    @Override
    public void run() {
        speler.updateCoords();
        speler.Collision(square);
        Platform.runLater(this::updateView);
    }



}
