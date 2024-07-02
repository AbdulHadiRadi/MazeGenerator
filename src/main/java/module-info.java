module com.backend.mazegenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.backend.mazegenerator to javafx.fxml;
    exports com.backend.mazegenerator;
}