package com.example.gpstrackingtest;

import android.location.Location;

public class Waypoint {

    private Location location;
    private String notes;


    public Waypoint(Location location, String notes)
    {
            this.location = location;
            this.notes = notes;
    }


    public Location getLocation()
    {
        return location;
    }

    public String getNotes()
    {
        return notes;
    }
}
