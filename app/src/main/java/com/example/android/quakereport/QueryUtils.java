package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.nio.charset.Charset;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** LOG_TAG */
    private static final String LOG_TAG = "QueryUtils";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Excepts a string and turns it into an URL
     *
     * @param stringUrl
     * @return
     */
    public static URL getURL(String stringUrl) {
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch(MalformedURLException exception) {
            Log.e(LOG_TAG, "Error occurd creating URL", exception);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonStringResponse = "";

        // If the URL is null or empty, then return early.
        if(url == null || url.toString().isEmpty()) {
            return jsonStringResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonStringResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG,
                        "urlConnectionResponseCode was: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException in makeHTTPRequest occured", e);
            // To print the whole stacktrace you could use this line:
            // Log.e(LOG_TAG, Log.getStackTraceString(e));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonStringResponse;
    }

    /**
     * Takes in an inputStream and parsed it with a bufferedReader into a StringBuilder
     * and return the String representation of it.
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return builder.toString();
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String jsonStringResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Convert SAMPLE_JSON_RESPONSE String into a JSONObject
            JSONObject rootObject = new JSONObject(jsonStringResponse);
            // Extract “features” JSONArray
            JSONArray features = rootObject.getJSONArray("features");

            // Loop through each feature in the array
            for(int i = 0; i < features.length(); i++) {
                // Get earthquake JSONObject at position i
                JSONObject earthquake = features.getJSONObject(i);
                // Get “properties” JSONObject
                JSONObject properties = earthquake.getJSONObject("properties");

                // Extract “mag” for magnitude
                double mag = properties.getDouble("mag");
                // Extract “place” for location
                String place = properties.getString("place");
                // Extract “time” for time
                long time = properties.getLong("time");
                // Extract "url" for web-url
                String url = properties.getString("url");

                // Create and add new earthquake object to list of earthquakes
                earthquakes.add(new Earthquake(mag, place, time, url));
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}