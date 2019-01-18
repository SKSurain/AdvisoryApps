package com.example.surai.myapplication.model;

import java.util.ArrayList;

public class ListingResponse {
    private ArrayList<Listing> listing;
    private Status status;

    public ListingResponse(ArrayList<Listing> listing, Status status) {
        this.listing = listing;
        this.status = status;
    }

    public ArrayList<Listing> getListing() {
        return listing;
    }

    public Status getStatus() {
        return status;
    }
}
