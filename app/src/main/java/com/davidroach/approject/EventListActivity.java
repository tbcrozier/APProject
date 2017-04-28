package com.davidroach.approject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends ListActivity {

    private ListView listView;
    private ArrayList<EventListModel> eventsReturned;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_activity_view);

        RestClient eventObj = new RestClient();
       eventsReturned = eventObj.getAllEvents();


       // ArrayAdapter<EventListModel> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, eventsReturned);

        ArrayAdapter<EventListModel> adapter = new EventInfoAdapter(this,0, eventsReturned );


        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        //set listener to go to Create event
        findViewById(R.id.new_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);

            }
        });


        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EventListModel itemObj = eventsReturned.get(position);


                Toast.makeText(EventListActivity.this,"selected", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), EventInfoActivity.class);
                intent.putExtra("EventName", itemObj.getEventName());
                startActivity(intent);





            }
        };
//set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);




    }








    /*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), EventInfoActivity.class);
        intent.putExtra("EventName", item);
        startActivity(intent);

        */




}
