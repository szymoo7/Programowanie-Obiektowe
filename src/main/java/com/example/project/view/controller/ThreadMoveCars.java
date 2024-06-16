package com.example.project.view.controller;

import com.example.project.model.Car;
import com.example.project.model.Point;
import com.example.project.model.Signal;
import com.example.project.model.Transport;
import com.example.project.service.TrafficManager;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ThreadMoveCars extends Controller implements Runnable
{
    private List<Car> cars;
    private final List<Point> entries;
    private final List<Point> carloop;
    private final List<Point> exits;
    private final Car car;
    private final int carNum;
    private final AnchorPane anchorPane;
    private final int sleepTime;
    private final List<Signal> signals;
    private PathTransition pathTransition;
    private boolean isRedLightDetected;
    /**
     * Ustawia wątek dla tego obiektu.
     *
     * @param thread wątek do ustawienia
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    private Thread thread;
    private Consumer<Long> threadFinishCallback;

    /**
     * Konstruktor klasy ThreadMoveCars.
     *
     * @param cars lista wszystkich samochodów
     * @param entries lista punktów wejściowych dla samochodów
     * @param carloop lista punktów tworzących pętlę ruchu samochodów
     * @param exits lista punktów wyjściowych dla samochodów
     * @param car pojedynczy samochód, którym będzie zarządzał ten wątek
     * @param carNum numer samochodu
     * @param mainpane główny panel aplikacji
     * @param sleepTime czas w milisekundach, który wątek ma czekać przed rozpoczęciem ruchu
     * @param signals lista sygnałów świetlnych
     */
    public ThreadMoveCars(List<Car> cars, List<Point> entries, List<Point> carloop, List<Point> exits, Car car, int carNum, AnchorPane mainpane, int sleepTime, List<Signal> signals) {
        this.entries = entries;
        this.carloop = carloop;
        this.exits = exits;
        this.car = car;
        this.carNum = carNum;
        this.anchorPane = mainpane;
        this.sleepTime = sleepTime;
        this.signals = signals;
    }
    /**
     * Metoda uruchamiana w wątku, odpowiedzialna za animację ruchu samochodu.
     */
    @Override
    public void run(){


        try {
            Thread.sleep(((long) sleepTime *carNum)/2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instant startTime = Instant.now();

        List<Point> road = car.CalculateRoad(entries, carloop, exits);
        Polyline track = new Polyline();
        int distance = (int) Transport.calculateDistance(road);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        car.setImageViewXY(road.get(0).getX(), road.get(0).getY());
        for(Point point : road) {
            track.getPoints().add((double) point.getX());
            track.getPoints().add((double) point.getY());
        }
        pathTransition = new PathTransition();
        //System.out.println("X: " + car.getX());
        //System.out.println("Y: " + car.getY());
        pathTransition.setPath(track);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setNode(car.getImageView());
        pathTransition.setDuration(Duration.millis(distance*10));

        int[] rectX = {680,649,480,282,154,262,529,537};
        int[] rectY = {322, 138, 118, 238, 297, 466, 446, 357};

        car.getImageView().translateXProperty().addListener((obs, oldX, newX) -> {
            Bounds boundsInScene = car.getImageView().localToScene(car.getImageView().getBoundsInLocal());
            car.setX((float) boundsInScene.getMinX()+22.5f);
            //System.out.println("X: " + car.getX()+22.5);

            for (int i = 0; i < rectX.length; i++) {
                if (car.getX() < rectX[i] + 40 &&
                        car.getX() > rectX[i] &&
                        car.getY() < rectY[i] + 40 &&
                        car.getY() > rectY[i]) {
                    int finalI = i;

//                    for (Signal signal : signals) {
//                        System.out.println(signal.getColor());
//                    }


                    boolean foundSignal = signals.get(i).getColor();
                    //System.out.println(foundSignal);
                    if (!foundSignal) {
                        pauseAnimation();
                        //System.out.println(foundSignal);
                    }
                }
                    //System.out.println("COLLISION DETECTED WITH: "+ (char) (i+65));}

            }

        });

        car.getImageView().translateYProperty().addListener((obs, oldY, newY) -> {
            Bounds boundsInScene = car.getImageView().localToScene(car.getImageView().getBoundsInLocal());
            car.setY((float) boundsInScene.getMinY()+25f);

            //System.out.println("Y: " + car.getY());
        });


        pathTransition.setOnFinished((e) -> {
            anchorPane.getChildren().remove(car.getImageView());
            //System.out.println("Time " + distance*10);
            Instant endTime = Instant.now();
            long threadLifetime = java.time.Duration.between(startTime, endTime).toMillis();
            //System.out.println(threadLifetime);
            threadFinishCallback.accept(threadLifetime);
        });

        pathTransition.onFinishedProperty();
        car.getImageView().setVisible(true);
        Platform.runLater(pathTransition::play);
        //System.out.println("X: " + car.getX());
        //System.out.println("Y: " + car.getY());

    }


    /**
     *
     * @param consumer obiekt typu {@link Consumer<Long>}, który zostanie ustawiony jako callback.
     * //     *                 Callback przyjmuje jako argument czas zakończenia wątku w formie wartości typu Long (czas w milisekundach).
     */
    public void setThreadFinishCallback(Consumer<Long> consumer) {
        this.threadFinishCallback = consumer;
    }

    /**
     * Zatrzymuje animację samochodu na określony czas, symulując czerwone światło.
     */
    private void pauseAnimation() {
        if (pathTransition.getStatus() == PathTransition.Status.RUNNING) {
            pathTransition.pause();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2)); // Red light duration
            pauseTransition.setOnFinished(event -> {
                isRedLightDetected = false;
                pathTransition.play();
            });
            pauseTransition.play();

        }
    }


}