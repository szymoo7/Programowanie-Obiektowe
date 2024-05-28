package com.example.project.service;

import com.example.project.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TrafficManager {

    private final Random random = new Random();

    private int id = 0;

    private List<String> carEntries = new ArrayList<>();
    private List<String> busEntries = new ArrayList<>();
    private List<String> tramEntries = new ArrayList<>();

    private List<String> carExits = new ArrayList<>();
    private List<String> busExits = new ArrayList<>();
    private List<String> tramExits = new ArrayList<>();

    private List<Signal> signals = new ArrayList<>();


    public List<String> getCarEntries() {
        return carEntries;
    }

    public void setCarEntries(List<String> carEntries) {
        this.carEntries = carEntries;
    }

    public List<String> getBusEntries() {
        return busEntries;
    }

    public void setBusEntries(List<String> busEntries) {
        this.busEntries = busEntries;
    }

    public List<String> getTramEntries() {
        return tramEntries;
    }

    public void setTramEntries(List<String> tramEntries) {
        this.tramEntries = tramEntries;
    }

    public List<String> getCarExits() {
        return carExits;
    }

    public void setCarExits(List<String> carExits) {
        this.carExits = carExits;
    }

    public List<String> getBusExits() {
        return busExits;
    }

    public void setBusExits(List<String> busExits) {
        this.busExits = busExits;
    }

    public List<String> getTramExits() {
        return tramExits;
    }

    public void setTramExits(List<String> tramExits) {
        this.tramExits = tramExits;
    }

    public List<Transport> transportCreate(int amountCar, int amountBus, int amountTram)
    {
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

    public List<Signal>  signalsCreate() {
        List<Signal> signals = new ArrayList<>();
        int[] coordsx = {712, 629, 469, 273, 154, 323, 533, 577};
        int[] coordsy = {291, 98, 107, 213, 311, 465, 456, 350};
        for(int i = 0; i < 8; i++) {
            Signal signal = new Signal(coordsx[i], coordsy[i], id, "Signal");
            signals.add(signal);
            id++;
        }
        return signals;
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
