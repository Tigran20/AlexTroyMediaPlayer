package com.alextroy.mediaplayeralextroy.activities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.alextroy.mediaplayeralextroy.R;
import com.alextroy.mediaplayeralextroy.adapter.SongsAdapter;
import com.alextroy.mediaplayeralextroy.model.Songs;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongsAdapter adapter;
    private ContentResolver contentResolver;
    private Cursor cursor;
    private Uri uri;
    private ArrayList<Songs> listSongs;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_activity);

        init();
        toolBar();
        adapter();
        getAllMediaMp3Files();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        listSongs = new ArrayList<>();
        recyclerView = findViewById(R.id.list);
    }

    private void toolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void adapter() {
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new SongsAdapter(this, listSongs);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getAllMediaMp3Files() {
        contentResolver = getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentResolver.query(
                uri, // Uri
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            Toast.makeText(getApplicationContext(), "Something Went Wrong.", Toast.LENGTH_LONG).show();
        } else if (!cursor.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "No Music Found on SD Card.", Toast.LENGTH_LONG).show();
        } else {

            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int Artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

            do {

                int SongID = cursor.getInt(id);
                Uri finalSuccessfulUri = Uri.withAppendedPath(uri, "" + SongID);

                String SongTitle = cursor.getString(Title);
                String SongArtist = cursor.getString(Artist);
                int SongDuration = cursor.getInt(duration);

                listSongs.add(new Songs(SongTitle, SongArtist, getDuration(SongDuration), finalSuccessfulUri));

            } while (cursor.moveToNext());
        }
    }

    private String getDuration(int msecs) {
        int seconds = msecs / 1000 % 60;
        String correctSecs;
        if (seconds < 10) {
            correctSecs = "0" + Integer.toString(seconds);
        } else {
            correctSecs = Integer.toString(seconds);
        }
        return (msecs / (1000 * 60)) % 60 + ":" + correctSecs;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
