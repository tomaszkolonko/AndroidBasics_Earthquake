package com.example.android.quakereport;

import java.util.ArrayList;
import java.util.List;

public class Earthquake {

    /** Magnitude of the earthquake */
    private float mMagnitude;

    /** Location of the earthquake */
    private String mLocationCity;

    /** Date of the earthquake */
    private String mDate;

    private ArrayList<Earthquake> earthquakeList = new ArrayList<>();

    public Earthquake() {

    }

    public Earthquake(float magnitude, String locationCity, String date) {
        this.mMagnitude = magnitude;
        this.mLocationCity = locationCity;
        this.mDate = date;
    }

    /**
     * Gets the Magnitude of the earthquake
     *
     * @return
     */
    public float getMagnitude() {
        return mMagnitude;
    }

    /**
     * Gets the location name of the earthquake
     *
     * @return
     */
    public String getLocationCity() {
        return mLocationCity;
    }

    /**
     * Gets the date on which the earthquake occured
     *
     * @return
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Creates dummy data for the adapter and view
     */
    public void createEarthquakeList() {
        if(earthquakeList.isEmpty()) {
            earthquakeList.add(new Earthquake((float) 7.2, "San Francisco", "Feb 2, 2016"));
            earthquakeList.add(new Earthquake((float) 6.1, "London", "July 20, 2015"));
            earthquakeList.add(new Earthquake((float) 3.6, "Tokyo", "Nov 10, 2014"));
            earthquakeList.add(new Earthquake((float) 5.4, "Mexico City", "May 3, 2014"));
            earthquakeList.add(new Earthquake((float) 2.8, "Moscow", "Jan 31, 2013"));
            earthquakeList.add(new Earthquake((float) 4.9, "Rio de Janeiro", "Aug 19, 2012"));
            earthquakeList.add(new Earthquake((float) 1.6, "Paris", "Oct 30, 2011"));
        }
    }

    /**
     * Returns the dummy data which was saved in an ArrayList
     *
     * @return
     */
    public ArrayList<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }
}
