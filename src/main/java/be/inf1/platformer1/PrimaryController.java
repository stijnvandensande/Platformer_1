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
    
    private Square square;
    
    

    @FXML
    void initialize() {
        rootView.setFocusTraversable(true);
        Platform.runLater(() -> rootView.requestFocus());
        rootView.setOnKeyPressed(this::handleKeyPress);
        rootView.setOnKeyPressed(this::handleKeyPress);
        square = new Square(50,50);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 32);
        updateView();
    }

@FXML
    void handleKeyPress(KeyEvent e) {
        switch(e.getCode()) {
            case Z:
                square.jump(5);
                break;
            case Q:
                square.move(-10);
                break;
            case D:
                square.move(10);
                break;
        }
    }
    
public void updateView() {
        rootView.getChildren().clear();
        Rectangle backrgroundView = new Rectangle(0,0,500,500);
        backrgroundView.setFill(Color.RED);
        rootView.getChildren().add(backrgroundView);
        
        Rectangle squareView = new Rectangle(square.getXCoord(),square.getYCoord(),50,50);
        squareView.setFill(Color.BLUE);
        rootView.getChildren().add(squareView);
    }

    @Override
    public void run() {
        square.updateCoords();
        Platform.runLater(this::updateView);
    }



}
