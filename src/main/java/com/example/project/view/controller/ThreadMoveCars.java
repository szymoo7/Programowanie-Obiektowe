package com.example.project.view.controller;

import com.example.project.model.Car;
import com.example.project.model.Point;
import com.example.project.model.Transport;
import com.example.project.service.TrafficManager;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
    private List<Point> entries;
    private List<Point> carloop;
    private List<Point> exits;
    private Car car;
    private int carNum;
    private AnchorPane anchorPane;
    private int sleepTime;

    private Consumer<Long> threadFinishCallback;

    public ThreadMoveCars(List<Car> cars, List<Point> entries, List<Point> carloop, List<Point> exits, Car car, int carNum, AnchorPane mainpane, int sleepTime){
        this.cars = cars;
        this.entries = entries;
        this.carloop = carloop;
        this.exits = exits;
        this.car = car;
        this.carNum = carNum;
        this.anchorPane = mainpane;
        this.sleepTime = sleepTime;
    }
    @Override
    public void run(){


        try {
            Thread.sleep(sleepTime*carNum);
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
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(track);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setNode(car.getImageView());

        pathTransition.setDuration(Duration.millis(distance*2));


        pathTransition.setOnFinished((e) -> {
            cars.remove(car);
            anchorPane.getChildren().remove(car.getImageView());
            System.out.println("Time " + distance*2);
            Instant endTime = Instant.now();
            long threadLifetime = java.time.Duration.between(startTime, endTime).toMillis();
            threadFinishCallback.accept(threadLifetime);
        });
        pathTransition.onFinishedProperty();
        car.getImageView().setVisible(true);
        Platform.runLater(pathTransition::play);
    }

    public void setThreadFinishCallback(Consumer<Long> consumer) {
        this.threadFinishCallback = consumer;
    }
}
