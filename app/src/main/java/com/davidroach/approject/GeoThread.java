package com.davidroach.approject;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by droach-dev on 4/5/17.
 */

public class GeoThread {

    protected Geocoder geocoder;
    List<Address> fromCoords;
    List<Address> fromStreet;

    public GeoThread(Context appContext){
         geocoder = new Geocoder(appContext, Locale.getDefault());
         fromCoords = null;
         fromStreet = null;
    }


    protected List<Address> addressFromCoords(Context appContext, double latitude, double longitude){
        try {
            fromCoords = geocoder.getFromLocation(latitude, longitude, 1);
            int x = 1;
            return fromCoords;
        }
        catch (IOException e){
            Log.i("ERROR",e.toString());
        }
        return  fromCoords;

    }


    //will return latitude and longitude to be added to the database.
    protected List<Address> coordsFromAddress(Context appContext, String addressIn){
        try {
            fromStreet = geocoder.getFromLocationName(addressIn,1);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return fromStreet;

    }
}
