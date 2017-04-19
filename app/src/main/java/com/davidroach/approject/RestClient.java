package com.davidroach.approject;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by droach-dev on 4/17/17.
 */




public class RestClient {


    String sportName;

    JsonReader returnedJson = null;
    String result = null;

    //constructor
    public RestClient(){


    }


    protected ArrayList<String> getUserInfo(String userNameIn){
        String appendUrl = "/user/getinfo/" + userNameIn ;
        ArrayList<String> retList = new ArrayList<String>();


        GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add sport names to array list to be returned.
                retList.add(jsonobject.getString("UserName"));
                retList.add(jsonobject.getString("FirstName"));
                retList.add(jsonobject.getString("LastName"));
                retList.add(jsonobject.getString("Email"));
                int x = 1+1;
                x++;
            }

            //Log.i("JSONOUT2:",jsonString);
        }
        catch (Exception e){
            //Log.i("ERROR:", e.toString());
        }


        return retList;

    }

    protected void createNewUser(String userNameIn, String passwordIn, String firstNameIn, String lastNameIn, String emailIn){

    }

    protected ArrayList<String> getAllEvents(){
        String appendUrl = "/events";
        ArrayList<String> retList = new ArrayList<String>();


        GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add sport names to array list to be returned.
                retList.add(jsonobject.getString("EventName"));
                int x = 1+1;
                x++;
            }

            //Log.i("JSONOUT2:",jsonString);
        }
        catch (Exception e){
            //Log.i("ERROR:", e.toString());
        }


        return retList;

    }

    protected void createNewEvent(String sportNameIn, String locationIn, String dateIn, String timeIn, String eventNameIn, String userNameIn){

    }


    protected ArrayList<String> getUserRSVP(String userNameIn){
        userNameIn = userNameIn.replaceAll(" ","%20");
        String appendUrl = "/rsvp/" + userNameIn;
        ArrayList<String> retList = new ArrayList<String>();


        GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add event names to array list to be returned.
                retList.add(jsonobject.getString("eventname"));
                int x = 1+1;
                x++;
            }

            //Log.i("JSONOUT2:",jsonString);
        }
        catch (Exception e){
            //Log.i("ERROR:", e.toString());
        }


        return retList;

    }

    protected void createNewRSVP(String userNameIn, String eventNameIn){

    }

    protected ArrayList<String> getEventRSVP(String eventNameIn){
        eventNameIn = eventNameIn.replaceAll(" ","%20");
        String appendUrl = "/rsvp/get/" + eventNameIn;
        ArrayList<String> retList = new ArrayList<String>();


        GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add sport names to array list to be returned.
                retList.add(jsonobject.getString("username"));
                int x = 1+1;
                x++;
            }

            //Log.i("JSONOUT2:",jsonString);
        }
        catch (Exception e){
            //Log.i("ERROR:", e.toString());
        }


        return retList;


    }

    protected ArrayList<String> getAvailableSports(){
        String appendUrl = "/sports";
        ArrayList<String> retList = new ArrayList<String>();


       GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add sport names to array list to be returned.
                retList.add(jsonobject.getString("SportName"));
                int x = 1+1;
                x++;
            }

            //Log.i("JSONOUT2:",jsonString);
        }
        catch (Exception e){
            //Log.i("ERROR:", e.toString());
        }


        return retList;
    }



    protected void getFutureEventsBySport(String sportNameIn){

    }

    protected void getFutureEventsByLocation(String locationNameIn){

    }


    //Returns Json as string array to be parsed by methods;
    private class GetConnection extends AsyncTask<String,Void, String>{


        String resultString = null;
        JsonReader result = null;
        @Override
        protected String doInBackground(String... urls){


            String baseURL = "http://10.247.204.86:8080";
            String mergeURL = baseURL + urls[0]; //made for debugging
            Log.i("DEBUG", mergeURL);
            StringBuilder jsonOut = null;

            try {
                URL getEndpoint = new URL(mergeURL);
                HttpURLConnection GetConnection = (HttpURLConnection) getEndpoint.openConnection();
                GetConnection.setRequestProperty("User-Agent", "approject-rest-v0.1");

                InputStream is = GetConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //to string for return
                jsonOut = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonOut.append(line).append('\n');
                }

            }
            catch(MalformedURLException e){
                Log.i("ERROR:"  + mergeURL, e.toString() );
                resultString = "ERROR: Bad URL.";
            }
            catch (IOException e){
                Log.i("ERROR:", e.toString());
                resultString = "ERROR: Problem reading data.";
            }
            finally{
                Log.i("JSON out: ",jsonOut.toString());
                return jsonOut.toString();


            }

        }

        @Override
        protected void onPostExecute(String output){


        }


    }


    private class PostConnection extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls){

            try {
                String result = null;
                String baseURL = "http://192.168.0.3:8080";
                String mergeURL = baseURL + urls[0]; //made for debugging
                Log.i("DEBUG", mergeURL);

            }
            catch(Exception e){
                Log.i("ERROR:", e.toString());
            }
            return null;
        }


        @Override
        protected void onPostExecute(String message){

        }

    }



}
