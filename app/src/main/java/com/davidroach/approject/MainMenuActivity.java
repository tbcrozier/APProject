package com.davidroach.approject;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


    GeoThread testThread = new GeoThread();
        //testThread.addressFromCoords(this, 36.533206,-87.353863); //should return 601 college street


        findViewById(R.id.create_event_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.view_schedule_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to about
                Intent intent = new Intent(getApplicationContext(), Schedule.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.about_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to about
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.quit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quit App
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

}




