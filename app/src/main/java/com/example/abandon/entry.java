package com.example.abandon;

public class entry {
    private String activty;
    private String mood;
    private String date;
    private String time;
    private String id;
    public entry(String activty, String mood, String date, String time, String id)
    {
        this.activty = activty;
        this.mood = mood;
        this.date = date;
        this.time = time;
        this.id = id;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getActivty() {
        return activty;
    }

    public void setActivty(String activty) {
        this.activty = activty;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
