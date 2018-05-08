package com.alextroy.mediaplayeralextroy.model;

import android.net.Uri;

public class Songs {

    private String artist;
    private String title;
    private Uri audioResourceId;
    private String duration;

    public Songs(String title, String artist, String duration, Uri audioResourceId) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.audioResourceId = audioResourceId;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public Uri getAudioResourceId() {
        return audioResourceId;
    }
}
