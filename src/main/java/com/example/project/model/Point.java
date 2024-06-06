package com.example.project.model;

public class Point extends Object
{
    private String connections = "";
    private String name = "";

    public Point(float x, float y, int id, String type, String name) {
        super(x, y, id, type);
        this.connections = connections;
        this.name = name;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
