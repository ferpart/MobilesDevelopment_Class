package com.example.activity2_1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// AsyncTask - a logical flow that runs "concurrently"
// can be triggered / executed and it will run asynchronouslyç
// asynchronously - request something to be done but don't wait for it to finish
public class RequestTask extends AsyncTask<String, Void, JSONObject>{

    private RequestListener listener;

    public RequestTask(RequestListener listener){
        this.listener = listener;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        /*for(int i = 0; i < strings.length; i++){
            Log.wtf("STRINGS", strings[i]);
        }*/

        // the actual request
        // receive data
        // parse data
        JSONObject result = null;


        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){

                // information can be retrieved from a stream
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder builder = new StringBuilder();
                String currentLine = "";

                while((currentLine = br.readLine()) != null){

                    //Log.wtf("HTTP RESPONSE", currentLine);
                    builder.append(currentLine);
                }

                String jsonContent = builder.toString();
                result = new JSONObject(jsonContent);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        listener.requestDone(jsonObject);
    }

    public interface RequestListener{

        public void requestDone(JSONObject jsonObject);
    }
}
