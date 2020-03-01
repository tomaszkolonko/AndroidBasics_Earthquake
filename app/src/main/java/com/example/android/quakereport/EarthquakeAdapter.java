package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Earthquake earthquake = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_earthquake_element,
                    parent, false);
        }

        // Lookup view for data population
        TextView magnitudeView = (TextView) convertView.findViewById(R.id.earthquake_element_magnitude);
        magnitudeView.setText(String.valueOf(earthquake.getMagnitude()));

        TextView locationView = (TextView) convertView.findViewById(R.id.earthquake_element_city);
        locationView.setText(earthquake.getLocationCity());

        TextView dateView = (TextView) convertView.findViewById(R.id.earthquake_element_date);
        dateView.setText(earthquake.getDate());

        // Return the list item view that is now showing the appropriate data
        return convertView;
    }
}
