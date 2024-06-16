package com.example.project.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Transport extends Object /*implements Event */
{
    public String exit;
    public String enter;
    public String vehicle;
    public int vmax;
    public float reactionTime;
    public float acceleration;
    public float speed;
    public float vehicleGap;
    public String color;

    public List<Point> CalculateRoad(List<Point> entries, List<Point> carloop, List<Point> exits)
    {
        if(vehicle.equals("Car"))
        {
            List<Point> road = new ArrayList<>();
            Point current = findPointByName(entries, enter);
            String end = findPointByName(exits, exit).getConnections();
            while(!current.getName().equals(end)) {
                road.add(current);
                if(findPointByName(entries, current.getConnections()) != null) {
                    current = findPointByName(entries, current.getConnections());
                } else if(findPointByName(carloop, current.getConnections()) != null) {
                    current = findPointByName(carloop, current.getConnections());
                } else {
                    current = findPointByName(exits, current.getConnections());
                }
            }
            road.add(current);
            road.add(findPointByName(exits, exit));
            road.add(findPointByName(exits, (exit + "'")));

            return road;

        } else if(vehicle.equals("Tram")) {

        } else {

        }


        return null;
    }

    public static Point findPointByName(List<Point> points, String name) {
        for(Point point : points) {
            if(point.getName().equals(name)) {
                return point;
            }
        }
        return null;
    }


    public Transport(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color) {
        super(x, y, id, type);
        this.exit = exit;
        this.enter = enter;
        this.vehicle = vehicle;
        this.vmax = vmax;
        this.reactionTime = reactionTime;
        this.acceleration = acceleration;
        this.speed = speed;
        this.vehicleGap = vehicleGap;
        this.color = color;
    }


    /**
     * Oblicza całkowitą odległość pomiędzy kolejnymi punktami na liście.
     * Odległość jest obliczana przy użyciu wzoru na odległość euklidesową.
     *
     * @param points lista obiektów typu Point reprezentujących sekwencję punktów
     * @return całkowita odległość pomiędzy kolejnymi punktami na liście
     */
    static public float calculateDistance(List<Point> points) {
        float distance = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            float x1 = points.get(i).getX();
            float y1 = points.get(i).getY();
            float x2 = points.get(i + 1).getX();
            float y2 = points.get(i + 1).getY();

            distance += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
        return distance;
    }
}
