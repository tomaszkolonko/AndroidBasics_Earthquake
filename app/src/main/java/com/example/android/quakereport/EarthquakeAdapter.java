package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    private String primaryLocation;
    private String offsetLocation;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Earthquake currentEarthquake = getItem(position);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getDateInUnixTime());

        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);
        retrieveLocationSplit(currentEarthquake.getLocationCity());

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_earthquake_element,
                    parent, false);
        }

        // Lookup view for data population
        TextView magnitudeView = (TextView) convertView.findViewById(R.id.earthquake_element_magnitude);
        magnitudeView.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        TextView primaryLocationView = (TextView) convertView.findViewById(R.id.earthquake_offset_location);
        primaryLocationView.setText(offsetLocation);

        TextView offsetLocationView = (TextView) convertView.findViewById(R.id.earthquake_primary_location);
        offsetLocationView.setText(primaryLocation);

        TextView dateView = (TextView) convertView.findViewById(R.id.earthquake_element_date);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) convertView.findViewById(R.id.earthquake_element_time);
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return convertView;
    }

    /**
     * Return the formatted date string from a Date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted time string from a Date object
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private void retrieveLocationSplit(String currentLocationString) {
        if(currentLocationString.contains(LOCATION_SEPARATOR)) {
            String[] parts = currentLocationString.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_of);
            primaryLocation = currentLocationString;
        }
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place from a
     * decimal magnitude value
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }
}
