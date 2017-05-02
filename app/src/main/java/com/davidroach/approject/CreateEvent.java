package com.davidroach.approject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
//import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TBCJr on 4/6/17.
 */

public class CreateEvent extends AppCompatActivity {

    //Variable that holds save instance state
    private int x = 0;
    String label1, label2,label3;
    //Variables that control Spinner
    private Spinner sportSpinner;
    private Spinner locationSpinner;
    private String currentUser;
    private String datestring;
    private String timestring;
    private String eventName;
    private String sportString;
    private String locationString;

    //variables to control date picker
    EditText date, time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //get logged in user
        currentUser = ((MyApplication) this.getApplication()).getCurrentUser();



        setContentView(R.layout.create_event_activity);


        //Logic to check the state of save instance state. Currently the Data only exists through
        //   one rotation, if the user rotates back then the data disappears
        if(savedInstanceState == null){
            label1 = " ";
            label2 = " ";
            label3 = " ";
        }else{
            /*
            label1 = savedInstanceState.getString("text1");
            label2 = savedInstanceState.getString("text2");
            label3 = savedInstanceState.getString("text3");
            */
        }

        final TextView usernameTV = (TextView) findViewById(R.id.username_TV);
        usernameTV.setText(currentUser);



        final EditText eventNameTV = (EditText) findViewById(R.id.event_name_et);
        //final EditText locationTV = (EditText) findViewById(R.id.enter_location_ET);




        //populate selection spinners
        SportNameSpinner();
        LocationSpinner();

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               eventName = eventNameTV.getText().toString();


            //check for missing info
               if(currentUser == null || eventName.isEmpty()||datestring == null||timestring == null||sportString == null||locationString == null){
                   Toast.makeText(getApplicationContext(),"You must fill all fields.",Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(getApplicationContext(),"Saving Event",Toast.LENGTH_LONG).show();
                    RestClient eventRestObj = new RestClient();
                    String returnString =  eventRestObj.createNewEvent(sportString,locationString,datestring,timestring,eventName,currentUser);

                   Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                   startActivity(intent);
               }

           }
       });

    //Return to Main Menu button listener
        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to main menu
                Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                startActivity(intent);
            }
        });

        //Time EditText & Listener
        time = (EditText)findViewById(R.id.time);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c2 = Calendar.getInstance();

                int hour = c2.get(Calendar.HOUR_OF_DAY);
                int minute = c2.get(Calendar.MINUTE);

                //time picker dialog
                timePickerDialog = new TimePickerDialog(CreateEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                int h = hourOfDay;
                                int m = minute;

                                String timeSet = "";
                                if (h > 12) {
                                    h -= 12;
                                    timeSet = "PM";
                                } else if (h == 0) {
                                    h += 12;
                                    timeSet = "AM";
                                } else if (h == 12){
                                    timeSet = "PM";
                                }else{
                                    timeSet = "AM";
                                }

                                String min = "";
                                if (m < 10)
                                    min = "0" + m ;
                                else
                                    min = String.valueOf(m);

                                // Append in a StringBuilder
                                String t = new StringBuilder().append(h).append(':')
                                        .append(min ).append(" ").append(timeSet).toString();
                                time.setText(t);

                                timestring = hourOfDay + ":" + minute;
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });



        //Date EditText & Listener
        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //date picker dialog
                datePickerDialog = new DatePickerDialog(CreateEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text

                                String d = (monthOfYear+1) + "-" + dayOfMonth + "-" + year;
                                date.setText(d);

                                datestring = year + "-" + (monthOfYear+1) + "-" + dayOfMonth  ;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

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
        /*
        EditText et1 = (EditText) findViewById(R.id.enter_your_name_ET);
        outState.putString("text1", et1.getText().toString());

        EditText et2 = (EditText) findViewById(R.id.enter_game_ET);
        outState.putString("text2", et2.getText().toString());

        EditText et3 = (EditText) findViewById(R.id.enter_location_ET);
        outState.putString("text3", et3.getText().toString());
        */
    }

    private void SportNameSpinner(){
        sportSpinner = (Spinner) findViewById(R.id.sport_spinner);
        List<String> sportNameList = new ArrayList<String>();

        RestClient restObj = new RestClient();
        sportNameList = restObj.getSportNames();

        ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,sportNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(dataAdapter);

        sportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sportString = sportSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void LocationSpinner(){
        locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        List<String> sportNameList = new ArrayList<String>();

        RestClient restObj = new RestClient();
        sportNameList = restObj.getAvailableLocations();

        ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,sportNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(dataAdapter);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationString = locationSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void updateTime(int hourOfDay, int minuteOfHour){


    }


}
