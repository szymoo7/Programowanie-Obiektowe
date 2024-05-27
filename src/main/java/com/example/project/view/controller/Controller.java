package com.example.project.view.controller;

import com.example.project.model.*;
import com.example.project.service.TrafficManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final Random random = new Random();

    private final TrafficManager trafficManager = new TrafficManager();

    //Zmienne
    @FXML
    private Label tram_amount, bus_amount, car_amount;

    @FXML
    private Slider slider_tram, slider_car, slider_bus;

    int tram_count, bus_count, car_count;

    public void start(ActionEvent e){
        System.out.println("PRESS");
        List<Transport> currentObjects = objectCreate(car_count, bus_count, tram_count);

        print(currentObjects);
    }


    //Funkcje GUI
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    public List<Transport> objectCreate(int amountCar, int amountBus, int amountTram)
    {
        int id = 0;
        List<Transport> vehicles = new ArrayList<>();
        for(int i = 0; i < amountCar; i++) {
            Car car= new Car(0, 0, id, "Transport", randomExit(), randomEnter(), "Car", 50, 200, 5, 50, 5, randomColor());
            id++;
            vehicles.add(car);
        }
        for(int i = 0; i < amountBus; i++) {
            Bus bus= new Bus(0, 0, id, "Transport", randomExit(), randomEnter(), "Bus", 50, 200, 5, 50, 5, randomColor());
            id++;
            vehicles.add(bus);
        }
        for(int i = 0; i < amountTram; i++) {

            //Point start =
            Tram tram= new Tram(0, 0, id, "Transport", randomExit(), randomEnter(), "Tram", 50, 200, 5, 50, 5, randomColor());
            id++;
            vehicles.add(tram);
        }
        return vehicles;
    }

    public void print(List<Transport> list) {
        for(Transport i : list){
            System.out.println(i.id + " " + i.type + " " + i.enter + " " + i.exit + " " + i.vehicle);
        }
    }

    public String randomEnter() {
        String[] entries = new String[]{"A", "C", "E", "G", "I"};
        int randomIndex = random.nextInt(entries.length);
        return entries[randomIndex];
    }

    public String randomExit() {
        String[] exits = new String[]{"B", "D", "F", "H", "J"};
        int randomIndex = random.nextInt(exits.length);
        return exits[randomIndex];
    }

    public String randomColor() {
        String[] colors = new String[]{"Red", "Blue", "Green"};
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }




}