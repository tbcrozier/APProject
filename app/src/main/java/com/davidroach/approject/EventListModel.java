package com.davidroach.approject;

/**
 * Created by droach-dev on 4/27/17.
 */

public class EventListModel {
    private String sportName;
    private String eventName;
    private String date;
    private String time;

    public EventListModel(String sportNameIn, String eventNameIn, String dateIn, String timeIn){
        this.sportName = sportNameIn;
        this.eventName = eventNameIn;
        this.date = dateIn;
        this.time = timeIn;
    }

    public String getSportName(){
        return sportName;

    }
    public String getEventName(){
        return eventName;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }


}
