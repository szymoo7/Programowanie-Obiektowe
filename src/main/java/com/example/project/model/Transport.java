package com.example.project.model;

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

    public String CalculateRoad()
    {
        return null;
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
