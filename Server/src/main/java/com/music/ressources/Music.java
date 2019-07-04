package com.music.ressources;

import com.user.ressources.User;

public class Music {

    private int id;
    private String title;
    private long duration;
    private User artist;
    private int musicType;
    private String uri;

    public Music(int id, String title, long duration, User artist, int musicType, String uri) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.musicType = musicType;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMusicType() {
        return musicType;
    }

    public void setMusicType(int musicType) {
        this.musicType = musicType;
    }
}
