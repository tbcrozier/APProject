package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by droach-dev on 4/24/17.
 */

public class EventInfoActivity extends FragmentActivity implements OnMapReadyCallback {

    private String eventNameFromIntent;
    private RestClient restObj, restObj2;
    private TextView nameTV;
    private TextView sportTV;
    private TextView timeTV;
    private TextView dateTV;
    private TextView locationTV;
    private TextView rsvpTV;
    private GoogleMap mMap;
    private double locationLatitude;
    private double locationLongitude;
    ArrayList<String> eventData;
    private String currentUser;
    private int numberOfRSVP;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info);

        restObj = new RestClient();
        restObj2 = new RestClient();
             /*
            0: Event Name
            1: Time
            2: Date
            3: Location Name
            5: Sport
            */

        //Get event name to be used with RestClient methods.
        eventNameFromIntent = getIntent().getStringExtra("EventName");

        //Query Rest Client

        eventData = restObj.getEventInfo(eventNameFromIntent);
        numberOfRSVP = restObj2.getEventRSVP(eventNameFromIntent).size();

        //get logged in user
        currentUser = ((MyApplication) this.getApplication()).getCurrentUser();


        //Set TextViews
        //Bind TextViews
        nameTV = (TextView)findViewById(R.id.event_name_tv);
        timeTV = (TextView)findViewById(R.id.event_time_tv);
        dateTV = (TextView)findViewById(R.id.event_date_tv);
        locationTV = (TextView)findViewById(R.id.event_location_tv);
        rsvpTV = (TextView)findViewById(R.id.num_rsvp_tv);
        sportTV = (TextView)findViewById(R.id.event_sport_tv);

        nameTV.setText(eventData.get(0));
        timeTV.setText("Time:  " + eventData.get(1));
        dateTV.setText("Date:    " + eventData.get(2));
        locationTV.setText("Where:  " + eventData.get(3));
        sportTV.setText("Sport:  " + eventData.get(4));
        rsvpTV.setText(Integer.toString(numberOfRSVP) + " People Attending");
        locationLatitude = Double.parseDouble(eventData.get(5));
        locationLongitude = Double.parseDouble(eventData.get(6));


        //Map Setup
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        //get data for map and set



        //setup back button
        findViewById(R.id.event_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToEventList();
            }
        });


        //setup rsvp button
        findViewById(R.id.event_join_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  //send rsvp to db
                  restObj = new RestClient();
                  restObj.createNewRSVP(currentUser,eventNameFromIntent);


                Toast.makeText(getApplicationContext(), "RSVP Created", Toast.LENGTH_LONG).show();
                backToEventList();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap map) {
                LatLng locationLL = new LatLng(locationLatitude, locationLongitude);
                map.addMarker(new MarkerOptions()
                .position(locationLL)
                .title(eventData.get(0)));

                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                //map.moveCamera(CameraUpdateFactory.newLatLng(locationLL));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationLL,15f));
    }


    private void backToEventList(){
        Intent MainActivityIntent = new Intent(EventInfoActivity.this, EventListActivity.class);
        startActivity(MainActivityIntent);
    }





}
