package com.example.project.model;

public class Bus extends Transport
{
    /*@Override
    public void callEvent(EventType eventType) {
        if(!(eventType.equals(EventType.Cyclist))) {
            return;
        }
        System.out.println("MPK moment");
    }*/


    public Bus(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color) {
        super(x, y, id, type, exit, enter, vehicle, vmax, reactionTime, acceleration, speed, vehicleGap, color);
    }
}
