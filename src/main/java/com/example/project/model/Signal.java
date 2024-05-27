package com.example.project.model;

public class Signal extends Object
{
    private String direction;
    private boolean color;

    public Signal(float x, float y, int id, String type) {
        super(x, y, id, type);
    }

    public boolean ChangeColor(boolean color)
    {
        color = !color;
        return color;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
