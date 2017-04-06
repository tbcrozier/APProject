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


    protected void addressFromCoords(Context appContext, double latitude, double longitude){
        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());

        List<Address> returnedAddresses;

        try {
            returnedAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            int x = 1;
        }
        catch (IOException e){
            Log.i("ERROR",e.toString());
        }
    }


    //will return a street
    protected double[] coordsToAddress(Context appContext, String addressIn){
        double[] coordsOut = {1.0,1.0};
        return coordsOut;

    }
}
