package org.example.dtos;

public class City {
    private String name;
    private String state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }
}