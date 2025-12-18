import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootView;

    @FXML
    void initialize() {
        assert rootView != null : "fx:id=\"rootView\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
