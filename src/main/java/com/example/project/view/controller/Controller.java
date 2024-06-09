package com.example.project.view.controller;
import javafx.animation.*;
import com.example.project.model.*;
import com.example.project.service.TrafficManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;


import java.net.URL;
import java.util.*;

import static java.lang.Thread.sleep;

public class Controller implements Initializable{

    // Obiekty/Klasy pomocnicze
    private final Random random = new Random();

    private final TrafficManager trafficManager = new TrafficManager();


    // Zmienne
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
    List<Point> carLoop =new ArrayList<>();

    public void start() throws InterruptedException {
        if (playing) {
            System.out.println("PLAYING");
            stop();

        } else {
            System.out.println("START");
            List<Transport> currentObjects = trafficManager.transportCreate(car_count, bus_count, tram_count);
            List<Car> cars = trafficManager.getCars();
            List<Tram> trams = trafficManager.getTrams();
            List<Bus> buses = trafficManager.getBuses();
            List<Point> carEntries = trafficManager.getCarEntries();
            List<Point> carExits = trafficManager.getCarExits();
            carAnchorChildrenAdd(cars);
            print(currentObjects, currentSignals, carEntries, carExits);
            signalsTimeline = runAnimation(currentSignals);
            List<Timeline> timelines = moveCars(cars, carEntries, carLoop, carExits);
            playing = true;

        }
    }

    public void stop(){
        System.out.println("STOP");
        signalsTimeline.stop();
        playing = false;
        resetLight(trafficManager.getLightOptionOne());
        resetLight(trafficManager.getLightOptionTwo());
        resetLight(trafficManager.getLightOptionThree());
        resetLight(trafficManager.getLightOptionFour());
        resetLight(trafficManager.getLightOptionFive());
    }

    // Funkcje GUI
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentSignals = trafficManager.signalsCreate();
        trafficManager.carLoopCreate();
        carLoop = trafficManager.getCarLoop();
        signalAnchorChildrenAdd(currentSignals);

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

    // Logika
    public void print(List<Transport> listTransport, List<Signal> listSignals, List<Point> listCarEntries, List<Point> listCarExits) {
        /*System.out.println("Transport:");
        for(Transport i : listTransport){
            System.out.println(i.id + " " + i.type + " " + i.enter + " " + i.exit + " " + i.vehicle);
        }*/
        System.out.println("Signals:");
        for(Signal i : trafficManager.getLightOptionOne()){
            System.out.println(i.x + " " + i.y + " "  + i.id + " " + i.type);
        }
        System.out.println("BREAK");
        for(Signal i : listSignals){
            System.out.println(i.x + " " + i.y + " "  + i.id + " " + i.type);
        } /*
        System.out.println("Car Entries:");
        for(Point i : listCarEntries){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }
        System.out.println("Car Exits:");
        for(Point i : listCarExits){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }*/

    }

    public void signalAnchorChildrenAdd(List<Signal> stoplight) {
        for(Signal i : stoplight) {
            mainAnchorPane.getChildren().add(i.getImageView());
        }
    }

    public void carAnchorChildrenAdd(List<Car> cars) {
        for(Car i : cars) {
            mainAnchorPane.getChildren().add(i.getImageView());
        }
    }


    public void changeLights(Set<Signal> signals) {
        for(Signal i : signals) {
            i.ChangeColor();
        }
    }

    public void resetLight(Set<Signal> signals) {
        for(Signal i : signals) {
            i.setColor(false);
        }
    }

    public Timeline runAnimation(List<Signal> signals) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame lights1 = new KeyFrame(
                Duration.seconds(10),
                event -> {
                    resetLight(trafficManager.getLightOptionFive());
                    changeLights(trafficManager.getLightOptionOne());
                }
        );
        KeyFrame lights2 = new KeyFrame(
                Duration.seconds(20),
                event -> {
                    resetLight(trafficManager.getLightOptionOne());
                    changeLights(trafficManager.getLightOptionTwo());
                }
        );
        KeyFrame lights3 = new KeyFrame(
                Duration.seconds(30),
                event -> {
                    resetLight(trafficManager.getLightOptionTwo());
                    changeLights(trafficManager.getLightOptionThree());
                }
        );
        KeyFrame lights4 = new KeyFrame(
                Duration.seconds(40),
                event -> {
                    resetLight(trafficManager.getLightOptionThree());
                    changeLights(trafficManager.getLightOptionFour());
                }
        );
        KeyFrame lights5 = new KeyFrame(
                Duration.seconds(50),
                event -> {
                    resetLight(trafficManager.getLightOptionFour());
                    changeLights(trafficManager.getLightOptionFive());
                    System.out.println("TURA");
                }
        );
        timeline.getKeyFrames().addAll(lights1, lights2, lights3, lights4, lights5);
        timeline.play();

        return timeline;
    }

    public List<Timeline> moveCars(List<Car> cars, List<Point> entries, List<Point> carloop, List<Point> exits) throws InterruptedException {
        List<Timeline> timelines = new ArrayList<>();
        for(Car car : cars) {
            /*System.out.println("------------------------------------------");
            System.out.println("Car Enter and Exit:" + car.enter + car.exit);
            System.out.println(car.getX() + " " + car.getY() + car.getX());*/
            List<Point> road = (car.CalculateRoad(entries, carloop, exits));
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            int frame=0;
            for(Point point : road) {
                int finalFrame = frame;
                int finalFrame1 = frame;
                KeyFrame move = new KeyFrame(
                        Duration.seconds(frame*2),
                        event -> {
                            //car.setImageViewXY(point.getX(), point.getY());
                            TranslateTransition translateTransition = new TranslateTransition();
                            translateTransition.setNode(car.getImageView());
                            translateTransition.setDuration(Duration.seconds(2));


                            System.out.println("X: " + car.x + " -> " + point.getX());
                            System.out.println("Y: " + car.y + " -> " + point.getY());
                            System.out.println("Keyframe:" + finalFrame1);

                            float moveX = point.getX()-car.x;
                            float moveY = point.getY()-car.y;
                            translateTransition.setToX(moveX);
                            translateTransition.setToY(moveY);

                            translateTransition.play();


                        }
                );
                timeline.getKeyFrames().addAll(move);
                frame++;

            }
            timeline.play();
            timelines.add(timeline);



            /*TranslateTransition transition = new TranslateTransition();
            transition.setNode(car.getImageView());
            transition.setDuration(Duration.millis(1000));
            transition.setToX(100);
            transition.setToY(100);
            transition.play();*/


            /*RotateTransition transitionRotate = new RotateTransition();
            transitionRotate.setDuration(Duration.millis(100));

            float angle = (float) Math.toDegrees(Math.atan2(car.getX() - 100, car.getY() - 100));

            if(angle < 0){
                angle += 360;
            }

            transitionRotate.byAngleProperty(angle);
            */
        }
        return timelines;

    }

}