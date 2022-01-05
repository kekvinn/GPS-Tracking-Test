package com.example.gpstrackingtest;

import android.location.Location;

public class Waypoint {

    private String title;
    private String longitude;
    private String latitude;
    private String notes;

    public Waypoint(String title, String longitude, String latitude, String notes)
    {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
        this.notes = notes;
    }


    public String getTitle()
    {
        return title;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public String getNotes()
    {
        return notes;
    }
}
