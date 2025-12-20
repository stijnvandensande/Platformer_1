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
                speler.jump(5);
                break;
            case Q:
                speler.move(-0.5);
                break;
            case D:
                speler.move(0.5);
                break;
        }
    }
    
public void updateView() {
        rootView.getChildren().clear();
        Rectangle backrgroundView = new Rectangle(0,0,boardSizeX,boardSizeY);
        backrgroundView.setFill(Color.GRAY); //Is iets aangenamer dan ROOD
        rootView.getChildren().add(backrgroundView);
        
        Rectangle squareView = new Rectangle(speler.getXCoord(),speler.getYCoord(),speler.getXSize(),speler.getYSize());
        squareView.setFill(Color.BLUE);
        rootView.getChildren().add(squareView);
    }

    @Override
    public void run() {
        speler.updateCoords();
        Platform.runLater(this::updateView);
    }



}
