package com.example.project.model;

public class Object extends Board
{
    public float x;
    public float y;
    public int id;
    public String type;

    public Object(float x, float y, int id, String type) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
