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
     * Returns the dummy data which was saved in an ArrayList
     *
     * @return
     */
    public ArrayList<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }
}
