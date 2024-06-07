package com.example.project.view.controller;

import com.example.project.model.*;
import com.example.project.service.TrafficManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private final Random random = new Random();

    private final TrafficManager trafficManager = new TrafficManager();


    //Zmienne
    @FXML
    private ImageView a, b, c, d, e, f, g, h;
    @FXML
    private Label tram_amount, bus_amount, car_amount;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Slider slider_tram, slider_car, slider_bus;


    int tram_count, bus_count, car_count;
    List<Signal> currentSignals = new ArrayList<>();
    Timeline signalsTimeline;
    boolean playing = false;

    public void start(){
        if (playing) {
            System.out.println("PLAYING");
            stop();
        }
        System.out.println("START");
        List<Transport> currentObjects = trafficManager.transportCreate(car_count, bus_count, tram_count);
        List<Point> carEntries = trafficManager.getCarEntries();
        List<Point> carExits = trafficManager.getCarExits();
        print(currentObjects, currentSignals, carEntries, carExits);
        signalsTimeline = runAnimation(currentSignals);
        playing = true;
    }

    public void stop(){
        System.out.println("STOP");
        signalsTimeline.stop();
        playing = false;
    }

    //Funkcje GUI
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentSignals = trafficManager.signalsCreate();
        anchorChildrenAdd(currentSignals);

        tram_count = (int) slider_tram.getValue();
        bus_count = (int) slider_bus.getValue();
        car_count = (int) slider_car.getValue();

        tram_amount.setText(Integer.toString(tram_count));
        bus_amount.setText(Integer.toString(bus_count));
        car_amount.setText(Integer.toString(car_count));


        slider_tram.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                tram_count = (int) slider_tram.getValue();
                tram_amount.setText(Integer.toString(tram_count));
            }
        });

        slider_car.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                car_count = (int) slider_car.getValue();
                car_amount.setText(Integer.toString(car_count));
            }
        });

        slider_bus.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                bus_count = (int) slider_bus.getValue();
                bus_amount.setText(Integer.toString(bus_count));
            }
        });

    }

    //Logika
    public void print(List<Transport> listTransport, List<Signal> listSignals, List<Point> listCarEntries, List<Point> listCarExits) {
        System.out.println("Transport:");
        for(Transport i : listTransport){
            System.out.println(i.id + " " + i.type + " " + i.enter + " " + i.exit + " " + i.vehicle);
        }
        System.out.println("Signals:");
        for(Signal i : listSignals){
            System.out.println(i.x + " " + i.y + " "  + i.id + " " + i.type);
        }
        System.out.println("Car Entries:");
        for(Point i : listCarEntries){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }
        System.out.println("Car Exits:");
        for(Point i : listCarExits){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }

    }

    public void anchorChildrenAdd(List<Signal> stoplight) {
        for(Signal i : stoplight) {
            mainAnchorPane.getChildren().add(i.getImageView());
            System.out.println("dupa");
        }
    }

    public void changeLights(List<Signal> signals) {
        for(Signal i : signals) {
            i.ChangeColor();
        }
    }

    public Timeline runAnimation(List<Signal> signals) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame change = new KeyFrame(
                Duration.seconds(0.5),
                event -> {
                    changeLights(signals);
                }
        );
        timeline.getKeyFrames().addAll(change);
        timeline.play();
        return timeline;
    }





}