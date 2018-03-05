package com.alextroy.mediaplayeralextroy.model;

import android.net.Uri;

public class Songs {

    private String mArtist;
    private String mTitle;
    private Uri audioResourceId;
    private String mDuration;

    public Songs(String title, String artist, String duration, Uri audioResourceId) {
        mArtist = artist;
        mTitle = title;
        mDuration = duration;
        this.audioResourceId = audioResourceId;
    }

    public String getArtist() {
        return mArtist;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getDuration() { return mDuration; }

    public Uri getAudioResourceId() {
        return audioResourceId;
    }
}
