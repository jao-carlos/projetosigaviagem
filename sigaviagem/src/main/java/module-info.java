module com.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires java.sql;
    opens com.pi to javafx.fxml;
    exports com.pi;
}