module com.backend.mazegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.backend.mazegame to javafx.fxml;
    exports com.backend.mazegame;
}