package com.davidroach.approject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by TBCJr on 4/6/17.
 */

public class CreateEvent extends AppCompatActivity {

    //Variable that holds save instance state
    private int x = 0;
    String label1, label2,label3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);


        //Logic to check the state of save instance state. Currently the Data only exists through
        //   one rotation, if the user rotates back then the data disappears
        if(savedInstanceState == null){
            label1 = " ";
            label2 = " ";
            label3 = " ";
        }else{
            label1 = savedInstanceState.getString("text1");
            label2 = savedInstanceState.getString("text2");
            label3 = savedInstanceState.getString("text3");
        }

        final TextView tv1 = (TextView) findViewById(R.id.enter_your_name_TV);
        final EditText et1 = (EditText) findViewById(R.id.enter_your_name_ET);
        tv1.setText("text1");

        final TextView tv2 = (TextView) findViewById(R.id.enter_game_TV);
        final EditText et2 = (EditText) findViewById(R.id.enter_game_ET);
        tv2.setText("text2");

        final TextView tv3 = (TextView) findViewById(R.id.enter_location_TV);
        final EditText et3 = (EditText) findViewById(R.id.enter_location_ET);
        tv3.setText("text3");

        findViewById(R.id.enter_event_info_button).setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               tv1.setText(et1.getText());
               tv2.setText(et2.getText());
               tv3.setText(et3.getText());
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

    //Method for saving instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        EditText et1 = (EditText) findViewById(R.id.enter_your_name_ET);
        outState.putString("text1", et1.getText().toString());

        EditText et2 = (EditText) findViewById(R.id.enter_game_ET);
        outState.putString("text2", et2.getText().toString());

        EditText et3 = (EditText) findViewById(R.id.enter_location_ET);
        outState.putString("text3", et3.getText().toString());
    }


}
