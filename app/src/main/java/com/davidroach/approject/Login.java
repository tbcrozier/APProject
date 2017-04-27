package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by TBCJr on 4/26/17.
 */

public class Login extends AppCompatActivity {

    //Variable that holds save instance state
    String label1, label2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Logic to check the state of save instance state. Currently the Data only exists through
        //   one rotation, if the user rotates back then the data disappears
        if(savedInstanceState == null){
            label1 = " ";
            label2 = " ";
        }else{
            label1 = savedInstanceState.getString("text1");
            label2 = savedInstanceState.getString("text2");
        }

        final TextView tv1 = (TextView) findViewById(R.id.login_uname_TV);
        final EditText et1 = (EditText) findViewById(R.id.login_uname_ET);
        tv1.setText("text1");

        final TextView tv2 = (TextView) findViewById(R.id.login_pword_TV);
        final EditText et2 = (EditText) findViewById(R.id.login_pword_ET);
        tv2.setText("text2");




        //Login button listener
        findViewById(R.id.login_enter_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                tv1.setText(et1.getText());
                tv2.setText(et2.getText());

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

    //Method for saving instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        EditText et1 = (EditText) findViewById(R.id.login_uname_ET);
        outState.putString("text1", et1.getText().toString());

        EditText et2 = (EditText) findViewById(R.id.login_pword_ET);
        outState.putString("text2", et2.getText().toString());

    }
}
