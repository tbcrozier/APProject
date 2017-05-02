package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TBCJr on 4/26/17.
 */

public class SignUp extends AppCompatActivity {

    //Variable that holds save instance state
    String label1, label2,label3, label4, label5;

    String firstName;
    String lastName;
    String userName;
    String password;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

/*
        //Logic to check the state of save instance state. Currently the Data only exists through
    //   one rotation, if the user rotates back then the data disappears
        if(savedInstanceState == null){
        label1 = " ";
        label2 = " ";
        label3 = " ";
        label4 = " ";
        label5 = " ";
    }else{
        label1 = savedInstanceState.getString("text1");
        label2 = savedInstanceState.getString("text2");
        label3 = savedInstanceState.getString("text3");
        label4 = savedInstanceState.getString("text4");
        label5 = savedInstanceState.getString("text5");
    }
    */

    final EditText firstnameET = (EditText) findViewById(R.id.signup_first_name_ET);

    final EditText lastNameET = (EditText) findViewById(R.id.signup_last_name_ET);

    final EditText userNameET = (EditText) findViewById(R.id.signup_username_ET);

    final EditText  passwordET = (EditText) findViewById(R.id.signup_password_ET);

    final EditText emailET = (EditText) findViewById(R.id.signup_email_ET);

    findViewById(R.id.sign_up_submit_button).setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            firstName = firstnameET.getText().toString();
            lastName = lastNameET.getText().toString();
            userName = userNameET.getText().toString();
            password = passwordET.getText().toString();
            email = emailET.getText().toString();

            if(firstName.isEmpty()||lastName.isEmpty()||userName.isEmpty()||password.isEmpty()||email.isEmpty()){
                Toast.makeText(getApplicationContext(),"You must complete all fields.",Toast.LENGTH_LONG).show();

            }else{
                //Toast.makeText(getApplicationContext(),"Creating User",Toast.LENGTH_LONG).show();
                RestClient restObj = new RestClient();

              //String returnString = restObj.createNewUser("JonJon","FMPasw0rd","Jon","Jonsophan","jonso@mail.com"); //works


               String returnString = restObj.createNewUser(userName,password,firstName,lastName,email);
                Toast.makeText(getApplicationContext(),returnString,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);


            }


        }
    });


    //Return to Main Menu button listener
    findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //go to main menu
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    });
}

    //Method for saving instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*

        EditText et1 = (EditText) findViewById(R.id.signup_first_name_ET);
        outState.putString("text1", et1.getText().toString());

        EditText et2 = (EditText) findViewById(R.id.signup_last_name_ET);
        outState.putString("text2", et2.getText().toString());

        EditText et3 = (EditText) findViewById(R.id.signup_username_ET);
        outState.putString("text3", et3.getText().toString());

        EditText et4 = (EditText) findViewById(R.id.signup_password_ET);
        outState.putString("text4", et4.getText().toString());

        EditText et5 = (EditText) findViewById(R.id.signup_email_ET);
        outState.putString("text5", et5.getText().toString());
        */
    }
}
