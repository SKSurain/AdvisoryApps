package com.example.surai.myapplication.model;

public class Listing {
    private int id;
    private String list_name;
    private float distance;

    public Listing(int id, String list_name, float distance) {
        this.id = id;
        this.list_name = list_name;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getList_name() {
        return list_name;
    }

    public float getDistance() {
        return distance;
    }
}

