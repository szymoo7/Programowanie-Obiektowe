package com.example.project.model;

public class Tram extends Transport
{
    public Tram(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color, Point onPoint) {
        super(x, y, id, type, exit, enter, vehicle, vmax, reactionTime, acceleration, speed, vehicleGap, color, onPoint);
    }
}
