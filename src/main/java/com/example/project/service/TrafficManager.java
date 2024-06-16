package com.example.project.service;

import com.example.project.model.*;
import javafx.scene.Group;

import java.util.*;

public class TrafficManager {

    private final Random random = new Random();

    private int id = 0;

    private final List<Point> carEntries = new ArrayList<>();
    private final List<Point> busEntries = new ArrayList<>();
    private final List<Point> tramEntries = new ArrayList<>();

    private final List<Point> carExits = new ArrayList<>();
    private final List<Point> busExits = new ArrayList<>();
    private final List<Point> tramExits = new ArrayList<>();

    private final List<Car> cars = new ArrayList<>();
    private final List<Tram> trams = new ArrayList<>();
    private final List<Bus> buses = new ArrayList<>();

    private final List<Signal> signals = new ArrayList<>();
    private final List<Point> carLoop = new ArrayList<>();

    private final Set<Signal> lightOptionOne = new HashSet<Signal>();

    private Set<Signal> lightOptionTwo = new HashSet<Signal>();

    private Set<Signal> lightOptionThree = new HashSet<Signal>();

    private Set<Signal> lightOptionFour = new HashSet<Signal>();

    private Set<Signal> lightOptionFive = new HashSet<Signal>();

    public List<Point> getCarLoop() {
        return carLoop;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Set<Signal> getLightOptionOne() {
        return lightOptionOne;
    }


    public Set<Signal> getLightOptionTwo() {
        return lightOptionTwo;
    }

    public Set<Signal> getLightOptionThree() {
        return lightOptionThree;
    }

    public Set<Signal> getLightOptionFour() {
        return lightOptionFour;
    }

    public Set<Signal> getLightOptionFive() {
        return lightOptionFive;
    }

    public int getId() {
        return id;
    }

    public List<Point> getCarEntries() {
        return carEntries;
    }

    public List<Point> getCarExits() {
        return carExits;
    }

    /**
     * Tworzy listę pojazdów w zadanej ilości dla samochodów, autobusów i tramwajów.
     *
     * @param amountCar ilość samochodów do utworzenia
     * @param amountBus ilość autobusów do utworzenia
     * @param amountTram ilość tramwajów do utworzenia
     * @return lista utworzonych pojazdów
     */
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
            Car car= new Car(x, y, id, "Transport", randomExit(), randomEnter(), "Car", 50, 200, 5, 50, 5, randomColor(), Transport.findPointByName(carEntries, randomEnter()));
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
    /**
     * Znajduje współrzędną X dla punktu wejściowego samochodów.
     *
     * @param enter nazwa punktu wejściowego
     * @return współrzędna X punktu wejściowego
     */
    public float findCarEntriesX(String enter) {
        for(Point point : carEntries) {
            if(point.getName().equals(enter)) {
                return point.getX();
            }
        }
        return 1;
    }

    /**
     * Znajduje współrzędną Y dla punktu wejściowego samochodów.
     *
     * @param enter nazwa punktu wejściowego
     * @return współrzędna Y punktu wejściowego
     */
    public float findCarEntriesY(String enter) {
        for(Point point : carEntries) {
            if(point.getName().equals(enter)) {
                return point.getY();
            }
        }
        return 1;
    }
    /**
     * Znajduje współrzędną X dla punktu wejściowego autobusów.
     *
     * @param enter nazwa punktu wejściowego
     * @return współrzędna X punktu wejściowego
     */
    public float findBusEntriesX(String enter) {
        for(Point point : carEntries) {
            if(Objects.equals(point.getName(), enter)) {
                return point.getX();
            }
        }
        return 1;
    }
    /**
     * Znajduje współrzędną Y dla punktu wejściowego autobusów.
     *
     * @param enter nazwa punktu wejściowego
     * @return współrzędna Y punktu wejściowego
     */
    public float findBusEntriesY(String enter) {
        for(Point point : busEntries) {
            if(Objects.equals(point.getName(), enter)) {
                return point.getY();
            }
        }
        return 1;
    }
    /**
     * Tworzy listę sygnałów świetlnych.
     *
     * @return lista utworzonych sygnałów świetlnych
     */
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
    /**
     * Losuje nazwę punktu wejściowego.
     *
     * @return nazwa punktu wejściowego
     */
    public String randomEnter() {
        String[] entries = new String[]{"A'", "B'", "C'", "D'", "E'"};
        int randomIndex = random.nextInt(entries.length);
        return entries[randomIndex];
    }
    /**
     * Losuje nazwę punktu wyjściowego.
     *
     * @return nazwa punktu wyjściowego
     */
    public String randomExit() {
        String[] exits = new String[]{"a", "b", "c", "d", "e"};
        int randomIndex = random.nextInt(exits.length);
        return exits[randomIndex];
    }
    /**
     * Losuje kolor pojazdu.
     *
     * @return kolor pojazdu
     */
    public String randomColor() {
        String[] colors = new String[]{"Red", "Blue", "Green"};
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }
    /**
     * Tworzy punkty wejściowe dla samochodów.
     */
    public void carEntriesCreate() {
        int[] coordsx = {695, 648, 502, 189, 302, 811, 815, 504, 43, 43};
        int[] coordsy = {325, 159, 145, 340, 482, 540, 118, 63, 236, 543};
        String[] names = {"A", "B", "C", "D", "E", "A'", "B'", "C'", "D'", "E'"};
        String[] connections = {"0", "2", "3", "6", "6", "A", "B", "C", "D", "E"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            point.setConnections(connections[i]);

            carEntries.add(point);
        }
    }
    /**
     * Tworzy punkty wyjściowe dla samochodów.
     */
    public void carExitsCreate() {
        int[] coordsx = {504, 703, 608, 239, 189, 663, 822, 572, 36, 33};
        int[] coordsy = {446, 286, 138, 281, 384, 549, 205, 57, 184, 471};
        String[] names = {"a", "b", "c", "d", "e", "a'", "b'", "c'", "d'", "e'"};
        String[] connections = {"7", "8", "2", "4", "4"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            if (i < 5) {
                point.setConnections(connections[i]);
            }

            carExits.add(point);
        }
    }
    /**
     * Tworzy pętlę ruchu samochodów.
     */
    public void carLoopCreate() {
        int[] coordsx = {682, 657, 618, 499, 276, 253, 343, 473, 581, 629};
        int[] coordsy = {298, 247, 163, 172, 281, 370, 468, 430, 356, 287};
        String[] names = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int i = 0; i < 10; i++) {
            Point point = new Point(coordsx[i], coordsy[i], id, "Point", names[i]);
            if(i < 9) {
                point.setConnections(names[i+1]);
            } else {
                point.setConnections(names[1]);
            }

            carLoop.add(point);
        }
    }
}
