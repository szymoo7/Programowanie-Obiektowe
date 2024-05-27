module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.view.controller;
    opens com.example.project.view.controller to javafx.fxml;
}