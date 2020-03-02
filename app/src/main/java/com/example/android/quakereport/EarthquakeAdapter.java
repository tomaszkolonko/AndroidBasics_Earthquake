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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

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

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_earthquake_element,
                    parent, false);
        }




        // Lookup view for data population
        TextView magnitudeView = (TextView) convertView.findViewById(R.id.earthquake_element_magnitude);
        magnitudeView.setText(String.valueOf(currentEarthquake.getMagnitude()));

        TextView locationView = (TextView) convertView.findViewById(R.id.earthquake_element_city);
        locationView.setText(currentEarthquake.getLocationCity());

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
}
