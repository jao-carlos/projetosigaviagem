module com.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    opens com.pi to javafx.fxml;
    exports com.pi;
}