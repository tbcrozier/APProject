package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by TBCJr on 4/26/17.
 */

public class Login extends AppCompatActivity {

    //Variable that holds save instance state
    String label1, label2;
    private EditText usernameET;

    private EditText passwordET;
    //private String usernameString,passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Logic to check the state of save instance state. Currently the Data only exists through
        //   one rotation, if the user rotates back then the data disappears
        /*

        if(savedInstanceState == null){
            label1 = " ";
            label2 = " ";
        }else{
            label1 = savedInstanceState.getString("text1");
            label2 = savedInstanceState.getString("text2");
        }
        */

        usernameET = (EditText) findViewById(R.id.login_uname_ET);

        passwordET = (EditText) findViewById(R.id.login_pword_ET);
        //usernameString = "NothingEntered";
        //passwordString = "NothingEntered";



        //Login button listener
        findViewById(R.id.login_enter_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //check if username and password exists.
                    if(usernameET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()){
                        Toast.makeText(Login.this,"You must enter a username and a password.", Toast.LENGTH_LONG).show();

                    }
                    else {
                        ArrayList<String> loginReturn;

                        RestClient loginObj = new RestClient();
                        loginReturn = loginObj.login(usernameET.getText().toString(), passwordET.getText().toString());

                        //check if username and password is correct
                        if(loginReturn.size()==0){
                            Toast.makeText(Login.this,"Invalid Username or Password", Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(Login.this,"Login Successful", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                            startActivity(intent);
                        }



                    }



                //If so go to main menu.
                //If not show Toast Message with invalid username and password.


            }
        });

        /*
        //Return to Main Menu button listener
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to main menu
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });
        */

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

        /*
        EditText et1 = (EditText) findViewById(R.id.login_uname_ET);
        outState.putString("text1", et1.getText().toString());

        EditText et2 = (EditText) findViewById(R.id.login_pword_ET);
        outState.putString("text2", et2.getText().toString());
        */

    }
}
