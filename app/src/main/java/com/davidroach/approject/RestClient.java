package com.davidroach.approject;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
    String baseURL = "http://107.170.31.121";

    //constructor
    public RestClient(){


    }


protected ArrayList<String> getEventInfo(String eventNameIn){
    eventNameIn = eventNameIn.replaceAll(" ","%20");
    String appendUrl = "/event/getinfo/" + eventNameIn ;
    ArrayList<String> retList = new ArrayList<String>();

    GetConnection gconnect = new GetConnection();
    try{
        String jsonString = gconnect.execute(appendUrl).get();

        //parse json array.
        JSONArray jsonarray = new JSONArray(jsonString);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            retList.add(jsonobject.getString("eventname"));
            retList.add(jsonobject.getString("time"));
            retList.add(jsonobject.getString("date"));
            retList.add(jsonobject.getString("locationname"));
            retList.add(jsonobject.getString("SportName"));
            retList.add(jsonobject.getString("Latitude"));
            retList.add(jsonobject.getString("Longitude"));

        }

    }
    catch (Exception e){
        Log.i("ERROR:", e.toString());
    }
    return retList;

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


    protected ArrayList<EventListModel> getAllEvents(){
        String appendUrl = "/events";

        String eventName, sportName, time, date;

       //ArrayList<String> retList = new ArrayList<String>();
        ArrayList<EventListModel> retList = new ArrayList<EventListModel>();


        GetConnection gconnect = new GetConnection();
        try {
            //get returned json string
            String jsonString = gconnect.execute(appendUrl).get();

            //parse json array.
            JSONArray jsonarray = new JSONArray(jsonString);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                //add sport names to array list to be returned.
                //retList.add(jsonobject.getString("EventName"));
                retList.add(new EventListModel(jsonobject.getString("SportName"),jsonobject.getString("EventName"),jsonobject.getString("Date"),jsonobject.getString("Time")));

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

    protected String createNewRSVP(String userNameIn, String eventNameIn){
        String outMessage = null;

        try {
            PostConnection postConnection = new PostConnection();
            String appendUrl = "/rsvp/new/";
            String urlParams = "username="+userNameIn+"&eventname="+eventNameIn;
            outMessage = postConnection.execute(appendUrl,urlParams,"RSVP").get();
        }
        catch (Exception e){
            Log.i("POST ERROR: ", e.toString());
        }



        return outMessage;

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



    protected ArrayList<String> getFutureEventsBySport(String sportNameIn){
        sportNameIn = sportNameIn.replaceAll(" ","%20");
        String appendUrl = "/sports/eventlist/" + sportNameIn;
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
                retList.add(jsonobject.getString("Date"));
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

    protected ArrayList<String> getFutureEventsByLocation(String locationNameIn){
        locationNameIn = locationNameIn.replaceAll(" ","%20");
        String appendUrl = "/location/eventlist/" + locationNameIn;
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
                retList.add(jsonobject.getString("eventname"));
                retList.add(jsonobject.getString("date"));
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


    //Returns Json as string array to be parsed by methods;
    private class GetConnection extends AsyncTask<String,Void, String>{


        String resultString = null;
        JsonReader result = null;
        @Override
        protected String doInBackground(String... urls){


            String baseURL = "http://107.170.31.121";
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





    protected String createNewUser(final String userNameIn, final String passwordIn, final String firstNameIn, final String lastNameIn, final String emailIn){
        String outMessage = null;

        try {
            PostConnection postConnection = new PostConnection();
            String appendUrl = "/user/new/";
            String urlParams = "username="+userNameIn+"&password="+passwordIn+"&firstname="+firstNameIn+"&lastname="+lastNameIn+"&email="+emailIn;
            outMessage = postConnection.execute(appendUrl,urlParams,"User").get();
        }
        catch (Exception e){
            Log.i("POST ERROR: ", e.toString());
        }



        return outMessage;
    }






    private class PostConnection extends AsyncTask<String, Void, String>{

        String resultString = null;
        String result = null;


        @Override
        protected String doInBackground(String... urls){


            String baseURL = "http://107.170.31.121";
            String mergeURL = baseURL + urls[0]; //made for debugging

            Log.i("DEBUG", mergeURL);

            try {
                URL postEndpoint = new URL(mergeURL);
                HttpURLConnection postConnection = (HttpURLConnection) postEndpoint.openConnection();
                postConnection.setRequestProperty("User-Agent", "approject-rest-v0.1");
                postConnection.setRequestMethod("POST");
                postConnection.setFixedLengthStreamingMode(urls[1].getBytes().length);
                postConnection.setDoOutput(true);

                DataOutputStream dStream = new DataOutputStream(postConnection.getOutputStream());
                dStream.writeBytes(urls[1]); //Writes out the string to the underlying output stream as a sequence of bytes<br />
                dStream.flush(); // Flushes the data output stream.<br />
                dStream.close(); // Closing the output stream.<br />






            }
            catch(Exception e){
                Log.i("ERROR:", e.toString());
                return ("Error Creating "+urls[2]);
            }
            return (urls[2] + " Created Successfully");
        }


        @Override
        protected void onPostExecute(String message){

        }

    }



}
