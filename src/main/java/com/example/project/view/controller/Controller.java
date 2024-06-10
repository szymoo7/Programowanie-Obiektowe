package com.example.project.view.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

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
    Set<Signal> signalsGreen = new HashSet<>();
    Timeline signalsTimeline;
    boolean playing = false;
    List<Point> carLoop = new ArrayList<>();
    List<Timeline> carTimelines = new ArrayList<>();
    List<Car> currentCars = new ArrayList<>();
    List<Long> times = new ArrayList<>();

    public void start() throws InterruptedException {
        if (playing) {
            System.out.println("PLAYING");
            stop();
            times.clear();

        } else {
            System.out.println("START");
            List<Transport> currentObjects = trafficManager.transportCreate(car_count, bus_count, tram_count);
            currentCars = trafficManager.getCars();
            List<Tram> trams = trafficManager.getTrams();
            List<Bus> buses = trafficManager.getBuses();
            List<Point> carEntries = trafficManager.getCarEntries();
            List<Point> carExits = trafficManager.getCarExits();
            carAnchorChildrenAdd(currentCars);
            Rectangle rect1 = new Rectangle(6, 556, 898, 55);
            rect1.setArcHeight(5.0);
            rect1.setArcWidth(5.0);
            rect1.setFill(Color.web("#2d2d2a"));
            rect1.setStroke(Color.BLACK);
            rect1.setStrokeWidth(0.0);

            Rectangle rect2 = new Rectangle(825, 15, 66, 581);
            rect2.setArcHeight(5.0);
            rect2.setArcWidth(5.0);
            rect2.setFill(Color.web("#2d2d2a"));
            rect2.setStroke(Color.TRANSPARENT);
            rect2.setStrokeWidth(0.0);

            Rectangle rect3 = new Rectangle(11, 1, 898, 55);
            rect3.setArcHeight(5.0);
            rect3.setArcWidth(5.0);
            rect3.setFill(Color.web("#2d2d2a"));
            rect3.setStroke(Color.BLACK);
            rect3.setStrokeWidth(0.0);

            Rectangle rect4 = new Rectangle(3, 15, 28, 613);
            rect4.setArcHeight(5.0);
            rect4.setArcWidth(5.0);
            rect4.setFill(Color.web("#2d2d2a"));
            rect4.setStroke(Color.BLACK);
            rect4.setStrokeWidth(0.0);


            mainAnchorPane.getChildren().addAll(rect1, rect2, rect3, rect4);
            print(currentObjects, currentSignals, carEntries, carExits);
            signalsTimeline = runAnimation(currentSignals);
            carTimelines = moveCars(currentCars, carEntries, carLoop, carExits);
            for(Timeline timeline : carTimelines) {
                timeline.play();
            }


            playing = true;

        }
    }

    public void stop(){
        System.out.println("STOP");
        playing = false;

        if (signalsTimeline != null) {
            signalsTimeline.stop();
        }

        for (Timeline timeline : carTimelines) {
            if (timeline != null) {
                timeline.stop();
            }
        }
        carTimelines.clear();

        for (Car car : currentCars) {
            if (car != null && car.getImageView() != null) {
                mainAnchorPane.getChildren().remove(car.getImageView());
            }
        }
        currentCars.clear();


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
        }
        System.out.println("Car Entries:");
        for(Point i : listCarEntries){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }
        System.out.println("Car Exits:");
        for(Point i : listCarExits){
            System.out.println(i.x + " " + i.y + " "  + i.getName() + " " + i.getConnections());
        }
        System.out.println("------------------------------------------");
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
                    signalsGreen = trafficManager.getLightOptionOne();
                }
        );
        KeyFrame lights2 = new KeyFrame(
                Duration.seconds(20),
                event -> {
                    resetLight(trafficManager.getLightOptionOne());
                    changeLights(trafficManager.getLightOptionTwo());
                    signalsGreen = trafficManager.getLightOptionTwo();
                }
        );
        KeyFrame lights3 = new KeyFrame(
                Duration.seconds(30),
                event -> {
                    resetLight(trafficManager.getLightOptionTwo());
                    changeLights(trafficManager.getLightOptionThree());
                    signalsGreen = trafficManager.getLightOptionThree();
                }
        );
        KeyFrame lights4 = new KeyFrame(
                Duration.seconds(40),
                event -> {
                    resetLight(trafficManager.getLightOptionThree());
                    changeLights(trafficManager.getLightOptionFour());
                    signalsGreen = trafficManager.getLightOptionFour();
                }
        );
        KeyFrame lights5 = new KeyFrame(
                Duration.seconds(50),
                event -> {
                    resetLight(trafficManager.getLightOptionFour());
                    changeLights(trafficManager.getLightOptionFive());
                    signalsGreen = trafficManager.getLightOptionFive();
                    System.out.println("TURA");
                }
        );
        timeline.getKeyFrames().addAll(lights1, lights2, lights3, lights4, lights5);
        timeline.play();

        return timeline;
    }

    public List<Timeline> moveCars(List<Car> cars, List<Point> entries, List<Point> carloop, List<Point> exits) throws InterruptedException {
        List<Timeline> timelines = new ArrayList<>();
        int carNum=1;
        int sleepTime = 1000 / Arrays.asList(cars).toArray().length;
        for(Car car : cars) {
            /*System.out.println("------------------------------------------");
            System.out.println("Car Enter and Exit:" + car.enter + car.exit);
            System.out.println(car.getX() + " " + car.getY() + car.getX());*/

            //Application
            ThreadMoveCars move = new ThreadMoveCars(cars, entries, carloop, exits, car, carNum, mainAnchorPane, sleepTime, currentSignals);
            move.setThreadFinishCallback(this::test);
            Thread moveThread = new Thread(move);
            move.setThread(moveThread);
            moveThread.start();


            carNum++;
            /*List<Point> road = car.CalculateRoad(entries, carloop, exits);
            Polyline track = new Polyline();

            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            car.setImageViewXY(road.get(0).getX(), road.get(0).getY());
            for(Point point : road) {
                track.getPoints().add((double) point.getX());
                track.getPoints().add((double) point.getY());
            }
            PathTransition pathTransition = new PathTransition();
            pathTransition.setPath(track);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setNode(car.getImageView());
            pathTransition.setDuration(Duration.seconds(2));
            pathTransition.play();


             */


        }
        return timelines;

    }

    public void test(Long a) {
        times.add(a);
    }

    /*public void lightsMatch() {
        Rectangle E = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        E.setArcHeight(5.0);
        E.setArcWidth(5.0);
        E.setStroke(Color.BLACK);
        E.setLayoutX(164.0);
        E.setLayoutY(307.0);

        Rectangle F = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        F.setArcHeight(5.0);
        F.setArcWidth(5.0);
        F.setStroke(Color.BLACK);
        F.setLayoutX(262.0);
        F.setLayoutY(466.0);

        Rectangle G = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        G.setArcHeight(5.0);
        G.setArcWidth(5.0);
        G.setStroke(Color.BLACK);
        G.setLayoutX(529.0);
        G.setLayoutY(446.0);

        Rectangle H = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        H.setArcHeight(5.0);
        H.setArcWidth(5.0);
        H.setStroke(Color.BLACK);
        H.setLayoutX(537.0);
        H.setLayoutY(357.0);

        Rectangle A = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        A.setArcHeight(5.0);
        A.setArcWidth(5.0);
        A.setStroke(Color.BLACK);
        A.setLayoutX(680.0);
        A.setLayoutY(322.0);

        Rectangle B = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        B.setArcHeight(5.0);
        B.setArcWidth(5.0);
        B.setStroke(Color.BLACK);
        B.setLayoutX(649.0);
        B.setLayoutY(138.0);

        Rectangle C = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        C.setArcHeight(5.0);
        C.setArcWidth(5.0);
        C.setStroke(Color.BLACK);
        C.setLayoutX(480.0);
        C.setLayoutY(118.0);

        Rectangle D = new Rectangle(40.0, 40.0, Color.DODGERBLUE);
        D.setArcHeight(5.0);
        D.setArcWidth(5.0);
        D.setStroke(Color.BLACK);
        D.setLayoutX(282.0);
        D.setLayoutY(238.0);
        }

    }*/


}