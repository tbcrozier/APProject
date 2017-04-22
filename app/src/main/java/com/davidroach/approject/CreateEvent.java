package com.davidroach.approject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.CalendarView;

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
    private Spinner spinner;
    private TextView spinnerTV;

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

        /* 888888------------------------------------------------------------------------*/
        spinnerTV = (TextView) findViewById(R.id.spinner_TV);
        //spinner = (Spinner) findViewById(R.id.spinner);

        final TextView tv2 = (TextView) findViewById(R.id.enter_game_TV);
        final EditText et2 = (EditText) findViewById(R.id.enter_game_ET);
        tv2.setText("text2");

        final TextView tv3 = (TextView) findViewById(R.id.enter_location_TV);
        final EditText et3 = (EditText) findViewById(R.id.enter_location_ET);
        tv3.setText("text3");

        CustomSpinner();

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

    //Time Button listener
        findViewById(R.id.btn_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Time Picker Code
                // Get Current time
                Calendar c = null;
                final TextView tv_time = (TextView) findViewById(R.id.tv_time);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                }
                int hour = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    hour = c.get(Calendar.HOUR_OF_DAY);
                }
                int minute = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    minute = c.get(Calendar.MINUTE);
                }

                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tv_time.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });
    //Date Button Listener
        findViewById(R.id.btn_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                }
                final TextView tv_date = (TextView) findViewById(R.id.tv_date);
                int year = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    year = c.get(Calendar.YEAR);
                }
                int month = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    month = c.get(Calendar.MONTH);
                }
                int day = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    day = c.get(Calendar.DAY_OF_MONTH);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tv_date.setText(year + " " + month + ", " + dayOfMonth);
                            }
                        },year, month, day);
                datePickerDialog.show();
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

    private void CustomSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Basketball");
        list.add("Football");
        list.add("Soccer");
        list.add("Golf");
        list.add("Frisbee");
        list.add("Vollyball");
        ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                spinnerTV.setText(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
