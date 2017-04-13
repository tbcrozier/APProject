package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by TBCJr on 4/6/17.
 */

public class CreateEvent extends About {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);



        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to about
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });





    }
}
