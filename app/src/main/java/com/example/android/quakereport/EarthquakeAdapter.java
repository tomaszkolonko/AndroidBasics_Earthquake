package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

        // TODO -> check further below on method setOnClickListenerOnView();
        // Set the onClickListener in a private method
        // setOnclickListenerOnView(magnitudeView, currentEarthquake);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        // Set the text on the magnitude circle
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

    /**
     * Splits the location string into an offset location and a primary location
     *
     * @param currentLocationString
     */
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

    private int getMagnitudeColor(double magnitude) {

        // That is only the id (int) of the resource. It is not the color itself
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch(magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        // the resource id of the color needs to be converted to an actual color
        // with the help of ContextCompat.getColoer();
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * TODO -> understand this
     * For me this is intuitively the better place to set the onClicklistener than in
     * the activity itself... why isn't it?
     * The only hassle is to get the activity context into here...
     * @param view
     * @param currentEarthquake
     */
//    private void setOnclickListenerOnView(TextView view, final Earthquake currentEarthquake) {
//        view.setOnClickListener(new TextView.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Uri webpage = Uri.parse(currentEarthquake.getURL());
//                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                if(intent.resolveActivity(getPackageManaer()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}
