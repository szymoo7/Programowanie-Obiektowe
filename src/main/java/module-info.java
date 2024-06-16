module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.view.controller;
    exports com.example.project.model;
    exports com.example.project.service;
    opens com.example.project.model;
    opens com.example.project.service;
    opens com.example.project.view.controller to javafx.fxml;
}