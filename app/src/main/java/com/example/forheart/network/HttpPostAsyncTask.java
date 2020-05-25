package com.example.forheart.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Class to handle http post request to send feedback
 * param 0 - url of server api
 * param 1 - content for feedback
 */
public class HttpPostAsyncTask extends AsyncTask<String, Void, Void> {

    private static final String TAG = "HttpPostAsyncTask";
    JSONObject postData;

    public HttpPostAsyncTask(Map<String, String> postData) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            // url from 1st parameter
            URL url = new URL(params[0]);
            // get connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");

            // Send the post body
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }
            int statusCode = urlConnection.getResponseCode();
            if (statusCode ==  200) {
                Log.d(TAG, "ok");
            } else {
                Log.d(TAG, "error");
            }
        }catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return null;
    }
}