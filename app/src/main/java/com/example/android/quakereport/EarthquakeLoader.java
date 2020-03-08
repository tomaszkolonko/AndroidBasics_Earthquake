package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response and extract a list of earthquakes
        URL url = QueryUtils.getURL(mUrl);
        String stringResponse = "";
        try {
            stringResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException exception) {
            Log.e(LOG_TAG, "HTTP Request failed: " + exception);
        }

        return QueryUtils.extractEarthquakesFeatures(stringResponse);
    }
}
