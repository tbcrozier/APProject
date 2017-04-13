package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by TBCJr on 4/6/17.
 */

public class CreateEvent extends About {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);




        findViewById(R.id.enter_your_name_button).setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               TextView tv = (TextView) findViewById(R.id.enter_your_name_TV);
               EditText et = (EditText) findViewById(R.id.enter_your_name_ET);
               tv.setText(et.getText());
           }
       });

        findViewById(R.id.enter_game_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.enter_game_TV);
                EditText et = (EditText) findViewById(R.id.enter_game_ET);
                tv.setText(et.getText());
            }
        });

        findViewById(R.id.enter_location_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.enter_location_TV);
                EditText et = (EditText) findViewById(R.id.enter_location_ET);
                tv.setText(et.getText());
            }
        });









//Return to Main Menu button listener
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to main menu
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });





    }
}
