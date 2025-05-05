module com.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.pi to javafx.fxml;
    exports com.pi;
}