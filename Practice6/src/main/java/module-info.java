module org.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jetbrains.annotations;


    opens application to javafx.fxml;
    exports application;
    exports application.Interface;
    opens application.Interface to javafx.fxml;
}