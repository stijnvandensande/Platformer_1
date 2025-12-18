module be.inf1.platformer1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens be.inf1.platformer1 to javafx.fxml;
    exports be.inf1.platformer1;
}
