package com.example.android.quakereport;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Earthquake {

    /** Magnitude of the earthquake */
    private double mMagnitude;

    /** Location of the earthquake */
    private String mLocationCity;

    /** Date of the earthquake in Unix Time */
    private long mDateInUnixTime;

    private ArrayList<Earthquake> earthquakeList = new ArrayList<>();

    public Earthquake() {

    }

    public Earthquake(double magnitude, String locationCity, long dateInUnixTime) {
        this.mMagnitude = magnitude;
        this.mLocationCity = locationCity;
        this.mDateInUnixTime = dateInUnixTime;
    }

    /**
     * Gets the Magnitude of the earthquake
     *
     * @return
     */
    public double getMagnitude() {
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
    public long getDateInUnixTime() {
        return mDateInUnixTime;
    }

    public String getDate() {
        Date dateObject = new Date(mDateInUnixTime);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, YYYY");
        String dateToDisplay = dateFormatter.format(dateObject);

        return dateToDisplay;
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
