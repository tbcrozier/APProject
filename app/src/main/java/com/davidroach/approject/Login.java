package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by TBCJr on 4/26/17.
 */

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        //Return to Main Menu button listener
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to main menu
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });

        //Sign-up button listener
        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to main menu
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}
