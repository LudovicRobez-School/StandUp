package com.event.models;

import com.user.models.User;

import java.util.Date;

public class Event {

    private int id;
    private String name;
    private User user;
    private Date date;
    private long longitude;
    private long latitude;

    public Event(String name, User user, Date date, long longitude, long latitude) {
        this.name = name;
        this.user = user;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Event(int id, String name, User user, Date date, long longitude, long latitude) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
