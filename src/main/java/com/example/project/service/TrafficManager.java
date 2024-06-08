package com.example.project.service;

import com.example.project.model.*;

import java.util.*;

public class TrafficManager {

    private final Random random = new Random();

    private int id = 0;

    private List<Point> carEntries = new ArrayList<>();
    private List<Point> busEntries = new ArrayList<>();
    private List<Point> tramEntries = new ArrayList<>();

    private List<Point> carExits = new ArrayList<>();
    private List<Point> busExits = new ArrayList<>();
    private List<Point> tramExits = new ArrayList<>();

    private List<Car> cars = new ArrayList<>();
    private List<Tram> trams = new ArrayList<>();
    private List<Bus> buses = new ArrayList<>();

    private List<Signal> signals = new ArrayList<>();

    private Set<Signal> lightOptionOne = new HashSet<Signal>();

    private Set<Signal> lightOptionTwo = new HashSet<Signal>();

    private Set<Signal> lightOptionThree = new HashSet<Signal>();

    private Set<Signal> lightOptionFour = new HashSet<Signal>();

    private Set<Signal> lightOptionFive = new HashSet<Signal>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Tram> getTrams() {
        return trams;
    }

    public void setTrams(List<Tram> trams) {
        this.trams = trams;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Set<Signal> getLightOptionOne() {
        return lightOptionOne;
    }

    public void setLightOptionOne(Set<Signal> lightOptionOne) {
        this.lightOptionOne = lightOptionOne;
    }

    public Set<Signal> getLightOptionTwo() {
        return lightOptionTwo;
    }

    public void setLightOptionTwo(Set<Signal> lightOptionTwo) {
        this.lightOptionTwo = lightOptionTwo;
    }

    public Set<Signal> getLightOptionThree() {
        return lightOptionThree;
    }

    public void setLightOptionThree(Set<Signal> lightOptionThree) {
        this.lightOptionThree = lightOptionThree;
    }

    public Set<Signal> getLightOptionFour() {
        return lightOptionFour;
    }

    public void setLightOptionFour(Set<Signal> lightOptionFour) {
        this.lightOptionFour = lightOptionFour;
    }

    public Set<Signal> getLightOptionFive() {
        return lightOptionFive;
    }

    public void setLightOptionFive(Set<Signal> lightOptionFive) {
        this.lightOptionFive = lightOptionFive;
    }

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
        id = 0;
        List<Transport> vehicles = new ArrayList<>();
        carEntriesCreate();
        carExitsCreate();
        for(int i = 0; i < amountCar; i++) {
            String enter = randomEnter();
            float x = findCarEntriesX(enter);
            float y = findCarEntriesY(enter);
            Car car= new Car(x, y, id, "Transport", randomExit(), randomEnter(), "Car", 50, 200, 5, 50, 5, randomColor());
            vehicles.add(car);
            cars.add(car);
            id++;
        }
        for(int i = 0; i < amountBus; i++) {
            String enter = randomEnter();
            float x = findBusEntriesX(enter);
            float y = findBusEntriesY(enter);
            Bus bus= new Bus(x, y, id, "Transport", randomExit(), randomEnter(), "Bus", 50, 200, 5, 50, 5, randomColor());
            vehicles.add(bus);
            buses.add(bus);
            id++;
        }
        for(int i = 0; i < amountTram; i++) {
            Tram tram= new Tram(0, 0, id, "Transport", randomExit(), randomEnter(), "Tram", 50, 200, 5, 50, 5, randomColor());
            vehicles.add(tram);
            trams.add(tram);
            id++;
        }
        return vehicles;
    }

    public float findCarEntriesX(String enter) {
        for(Point point : carEntries) {
            if(point.getName() == enter) {
                return point.getX();
            }
        }
        return 1;
    }

    public float findCarEntriesY(String enter) {
        for(Point point : carEntries) {
            if(point.getName() == enter) {
                return point.getY();
            }
        }
        return 1;
    }

    public float findBusEntriesX(String enter) {
        for(Point point : carEntries) {
            if(Objects.equals(point.getName(), enter)) {
                return point.getX();
            }
        }
        return 1;
    }

    public float findBusEntriesY(String enter) {
        for(Point point : busEntries) {
            if(Objects.equals(point.getName(), enter)) {
                return point.getY();
            }
        }
        return 1;
    }

    public List<Signal>  signalsCreate() {
        List<Signal> signals = new ArrayList<>();
        int[] coordsx = {712, 629, 469, 273, 154, 323, 533, 577};
        int[] coordsy = {291, 98, 107, 213, 311, 465, 456, 350};
        int[] ids = {65, 66, 67, 68, 69, 70, 71, 72};
                  // A,  B,  C,  D,  E,  F,  G,  H
        for(int i = 0; i < 8; i++) {
            Signal signal = new Signal(coordsx[i], coordsy[i], ids[i], "Signal");
            signal.setColor(false);
            signals.add(signal);
            char idChar = (char) ids[i];
            if (Arrays.asList('A', 'C', 'D', 'F', 'G').contains(idChar)) {
                lightOptionOne.add(signal);
            }
            if (Arrays.asList('A', 'C', 'E', 'F', 'G').contains(idChar)) {
                lightOptionTwo.add(signal);
            }
            if (Arrays.asList('B', 'D', 'F', 'G', 'H').contains(idChar)) {
                lightOptionThree.add(signal);
            }
            if (Arrays.asList('B', 'E', 'G', 'H').contains(idChar)) {
                lightOptionFour.add(signal);
            }
            if (Arrays.asList('C', 'E', 'F', 'H').contains(idChar)) {
                lightOptionFive.add(signal);
            }
        }
        return signals;
    }

    public String randomEnter() {
        String[] entries = new String[]{"A", "B", "C", "D", "E"};
        int randomIndex = random.nextInt(entries.length);
        return entries[randomIndex];
    }

    public String randomExit() {
        String[] exits = new String[]{"a", "b", "c", "d", "e"};
        int randomIndex = random.nextInt(exits.length);
        return exits[randomIndex];
    }

    public String randomColor() {
        String[] colors = new String[]{"Red", "Blue", "Green"};
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }

    //random speed
    public String randomSpeed() {
        String[] colors = new String[]{"Red", "Blue", "Green"};
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }

    public void carEntriesCreate() {
        int[] coordsx = {695, 648, 502, 189, 302, 811, 815, 504, 43, 43};
        int[] coordsy = {325, 159, 145, 340, 482, 540, 118, 63, 236, 543};
        String[] names = {"A", "B", "C", "D", "E", "A'", "B'", "C'", "D'", "E'"};
        String[] connections = {"0", "2", "3", "6", "6"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            if (i < 6) {
                point.setConnections(connections[i]);
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
