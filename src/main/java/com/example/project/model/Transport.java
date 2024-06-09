package com.example.project.model;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private Point onPoint;
    public String roadString = "";

    public List<Point> CalculateRoad(List<Point> entries, List<Point> carloop, List<Point> exits)
    {
        if(vehicle.equals("Car"))
        {
            List<Point> road = new ArrayList<>();
            Point current = findPointByName(entries, enter);
            String end = findPointByName(exits, exit).getConnections();
            while(!current.getName().equals(end)) {
                road.add(current);
                onPoint = current;
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
            for(Point i : road) {
                System.out.println(i.getName());
                roadString = roadString + i.getName();
            }
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


    /*public Point Move(Point current, List<Point> carloop, List<Point> exits)
    {
        if(Objects.equals(vehicle, "Car"))
        {
            Point current = findPointByName(current, enter);
            while(!current.getName().equals(end)) {
                road.add(current);
                onPoint = current;
                if(findPointByName(entries, current.getConnections()) != null) {
                    current = findPointByName(entries, current.getConnections());
                } else if(findPointByName(carloop, current.getConnections()) != null) {
                    current = findPointByName(carloop, current.getConnections());
                } else {
                    current = findPointByName(exits, current.getConnections());
                }
            }

        }
        return null;
    }*/

    public Transport(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color, Point onPoint) {
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
        this.onPoint = onPoint;
    }

    public Signal nextStoplight(List<Signal> signal) {

        return null;
    }

    public Point getOnPoint() {
        return onPoint;
    }

    public void setOnPoint(Point onPoint) {
        this.onPoint = onPoint;
    }
}
