package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    /**
     * Uruchamia główną scenę aplikacji.
     *
     * @param stage główna scena aplikacji
     * @throws IOException jeśli nie można załadować pliku FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Pictures/grunwald-logo.png")));
        stage.setTitle("Grunwald Simulator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Główna metoda uruchamiająca aplikację.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch();
    }
}
