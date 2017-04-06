package com.davidroach.approject;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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

        //SetUp for Simple Buttons
        findViewById(R.id.about_button).setOnClickListener(new AboutActivity());
        findViewById(R.id.quit_button).setOnClickListener(new QuitActivity());

    GeoThread testThread = new GeoThread();
        testThread.addressFromCoords(this, 36.533206,-87.353863); //should return 601 college street
    }



    //Class do display Dialog About Page from Main Menu
    class AboutActivity implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String message = "<html>\n" +
                    "<h1>Play App!</h1>\n" +
                    "<h2>About</h2>\n" +
                    "<p>Created by:</br></p>\n" +
                    "<p>Blake Crozier</br></p>\n" +
                    "<p>David Roach</br></p>\n" +
                    "<p>Thanks for visiting our app</p>\n" +
                    "</html>";

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage(Html.fromHtml(message));
            builder.setPositiveButton("Close", null);

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    //method for quitting app
    class QuitActivity implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Quit App
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}




