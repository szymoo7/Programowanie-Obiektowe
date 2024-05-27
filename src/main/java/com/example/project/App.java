package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Pictures/grunwald-logo.png")));
        stage.setTitle("Grunwald Simulator");
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);

        stage.setScene(scene);
        stage.show();

        /*List<Event> events = new ArrayList<>();
        Bus bus1 = new Bus();
        events.add(bus1);

        events.get(0).callEvent(EventType.Cyclist);*/
    }


    public static void main(String[] args) {
        launch();
    }
}