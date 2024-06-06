package com.example.project.service;

import com.example.project.model.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Random;


public class TrafficManager {

    private final Random random = new Random();

    private int id = 0;

    private List<Point> carEntries = new ArrayList<>();
    private List<Point> busEntries = new ArrayList<>();
    private List<Point> tramEntries = new ArrayList<>();

    private List<Point> carExits = new ArrayList<>();
    private List<Point> busExits = new ArrayList<>();
    private List<Point> tramExits = new ArrayList<>();

    private List<Signal> signals = new ArrayList<>();

    public Random getRandom() {
        return random;
    }

    public int getId() {
        return id;
    }

    public List<Point> getCarEntries() {
        return carEntries;
    }

    public List<Point> getBusEntries() {
        return busEntries;
    }

    public List<Point> getTramEntries() {
        return tramEntries;
    }

    public List<Point> getCarExits() {
        return carExits;
    }

    public List<Point> getBusExits() {
        return busExits;
    }

    public List<Point> getTramExits() {
        return tramExits;
    }

    public List<Signal> getSignals() {
        return signals;
    }

    public List<Transport> transportCreate(int amountCar, int amountBus, int amountTram)
    {
        List<Transport> vehicles = new ArrayList<>();
        for(int i = 0; i < amountCar; i++) {
            Car car= new Car(0, 0, id, "Transport", randomExit(), randomEnter(), "Car", 50, 200, 5, 50, 5, randomColor());
            id++;
            vehicles.add(car);
            carEntriesCreate();
            carExitsCreate();
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

    public void carEntriesCreate() {
        int[] coordsx = {695, 648, 502, 189, 302, 811, 815, 504, 43, 43};
        int[] coordsy = {325, 159, 145, 340, 482, 540, 118, 63, 236, 543};
        String[] names = {"A", "B", "C", "D", "E", "A'", "B'", "C'", "D'", "E'"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            if (i == 0) {
                point.setConnections(names[i + 1]);
            } else if (i == 9) {
                point.setConnections(names[i - 1]);
            }
            else {
                point.setConnections(names[i - 1] + names[i + 1]);
            }
            carEntries.add(point);
        }
    }

    public void carExitsCreate() {
        int[] coordsx = {504, 703, 608, 239, 189, 663, 822, 572, 36, 33};
        int[] coordsy = {446, 286, 138, 281, 384, 549, 205, 57, 184, 471};
        String[] names = {"a", "b", "c", "d", "e", "a'", "b'", "c'", "d'", "e'"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            if (i == 0) {
                point.setConnections(names[i + 1]);
            }else if (i == 9) {
                point.setConnections(names[i - 1]);
            }
            else {
                point.setConnections(names[i - 1] + names[i + 1]);
            }
            carExits.add(point);
        }
    }

}
