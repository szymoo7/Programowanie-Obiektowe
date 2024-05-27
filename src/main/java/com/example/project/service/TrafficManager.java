package com.example.project.service;

import java.util.ArrayList;
import java.util.List;


public class TrafficManager {

    private List<String> carEntries = new ArrayList<>(){};
    private List<String> busEntries = new ArrayList<>(){};
    private List<String> tramEntries = new ArrayList<>(){};

    private List<String> carExits = new ArrayList<>(){};
    private List<String> busExits = new ArrayList<>(){};
    private List<String> tramExits = new ArrayList<>(){};

    public List<String> getCarEntries() {
        return carEntries;
    }

    public void setCarEntries(List<String> carEntries) {
        this.carEntries = carEntries;
    }

    public List<String> getBusEntries() {
        return busEntries;
    }

    public void setBusEntries(List<String> busEntries) {
        this.busEntries = busEntries;
    }

    public List<String> getTramEntries() {
        return tramEntries;
    }

    public void setTramEntries(List<String> tramEntries) {
        this.tramEntries = tramEntries;
    }

    public List<String> getCarExits() {
        return carExits;
    }

    public void setCarExits(List<String> carExits) {
        this.carExits = carExits;
    }

    public List<String> getBusExits() {
        return busExits;
    }

    public void setBusExits(List<String> busExits) {
        this.busExits = busExits;
    }

    public List<String> getTramExits() {
        return tramExits;
    }

    public void setTramExits(List<String> tramExits) {
        this.tramExits = tramExits;
    }
}
