package com.davidroach.approject;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by droach-dev on 4/27/17.
 */

public class EventInfoAdapter extends ArrayAdapter<EventListModel> {
    private Context context;
    private List<EventListModel> listItems;

    public EventInfoAdapter(Context context, int resource, ArrayList<EventListModel> objects){
        super(context, resource, objects);
        this.context = context;

        this.listItems = objects;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        EventListModel eventListModel = listItems.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate((R.layout.list_item),null);

        TextView eventName = (TextView) view.findViewById(R.id.event_title);
        TextView eventTime = (TextView) view.findViewById(R.id.event_time_tv);
        TextView eventDate = (TextView) view.findViewById(R.id.event_date_tv);
        ImageView sportImage = (ImageView)view.findViewById(R.id.item_icon);

        String sportString = eventListModel.getSportName();
        sportString = sportString.trim();
        Drawable myDrawable = null;

        if(sportString.equals("Touch Football")){
            myDrawable = context.getResources().getDrawable(R.drawable.football32);


        }
        else if(sportString.equals("Basketball")){
            myDrawable = context.getResources().getDrawable(R.drawable.basketball32);

        }
        else if(sportString.equals("BaseBall")){
            myDrawable = context.getResources().getDrawable(R.drawable.baseball32);

        }
        else if (sportString.equals("Soccer")){
            myDrawable = context.getResources().getDrawable(R.drawable.soccer32);

        }
        else if(sportString.equals("Pool")){
            myDrawable = context.getResources().getDrawable(R.drawable.eightball32);

        }
        else if(sportString.equals("Ping Pong")){
            myDrawable = context.getResources().getDrawable(R.drawable.pingpong32);

        }
        else if(sportString.equals("Hockey")){
            myDrawable = context.getResources().getDrawable(R.drawable.goal);

        }
        else{
            myDrawable = context.getResources().getDrawable(R.drawable.helmet32);
        }
        sportImage.setImageDrawable(myDrawable);



        eventName.setText(eventListModel.getEventName());
        eventTime.setText(eventListModel.getTime());
        eventDate.setText(eventListModel.getDate());







        return view;
    }
}
