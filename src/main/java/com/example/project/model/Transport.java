package com.example.project.model;

import java.util.ArrayList;
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
    private String road;

    public List<Point> CalculateRoad(List<Point> entries, List<Point> carloop)
    {
        if(Objects.equals(vehicle, "Car"))
        {
            List<Point> road = new ArrayList<>();
            String track = "0123456789";
            Point current = findPointByName(entries, enter);
            String end = exit;
            while(current.getName() != end) {
                road.add(current);
                current = findPointByName(carloop, current.getConnections());
            }


        } else if(Objects.equals(vehicle, "Tram")) {

        } else {

        }


        return null;
    }

    public Point findPointByName(List<Point> points, String name) {
        for(Point point : points) {
            if(Objects.equals(point.getName(), name)) {
                return point;
            }
        }
    }


    public Coords Move()
    {
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
}
