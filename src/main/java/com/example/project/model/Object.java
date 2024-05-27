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
}
