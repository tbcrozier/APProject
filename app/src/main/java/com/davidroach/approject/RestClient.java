package com.davidroach.approject;

import android.util.Log;

import java.net.URL;

/**
 * Created by droach-dev on 4/17/17.
 */

public class RestClient {

    //constructor
    public RestClient(){

    }

    protected void setupGetConnection(String urlIn){
        try {
            URL getEndpoint = new URL(urlIn);


        }
        catch(Exception e){
            Log.i("ERROR:", e.toString());
        }

    }

    protected void executeRequest(){
        
    }

    protected void setupPostConnection(String urlIn){
        try {
            URL postEndpoint = new URL(urlIn);



        }
        catch(Exception e){
            Log.i("ERROR:", e.toString());
        }

    }

    protected void getUserInfo(String userNameIn){

    }

    protected void createNewUser(String userNameIn, String passwordIn, String firstNameIn, String lastNameIn, String emailIn){

    }

    protected void getAllEvents(){

    }

    protected void createNewEvent(String sportNameIn, String locationIn, String dateIn, String timeIn, String eventNameIn, String userNameIn){

    }


    protected void getUserRSVP(String userNameIn){

    }

    protected void createNewRSVP(String userNameIn, String eventNameIn){

    }

    protected void getEventRSVP(String eventNameIn){

    }

    protected void getAvailableSports(){

    }

    protected void getFutureEventsBySport(String sportNameIn){

    }

    protected void getFutureEventsByLocation(String locationNameIn){

    }


}
