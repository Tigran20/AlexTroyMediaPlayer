package com.alextroy.mediaplayeralextroy.activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alextroy.mediaplayeralextroy.R;

public class SongCardActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private ImageView playBtn;
    private ImageView prevBtn;
    private ImageView nextBtn;
    private TextView songTitle;
    private TextView songAuthor;
    private Toolbar toolbar;
    private Uri songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_card);

        init();
        toolBar();
        setData();
        audioManager();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        songTitle = findViewById(R.id.song_title_card);
        songAuthor = findViewById(R.id.song_author_card);

        prevBtn = findViewById(R.id.prev_btn);
        playBtn = findViewById(R.id.play_btn);
        nextBtn = findViewById(R.id.next_btn);
    }

    private void toolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        Uri id = intent.getParcelableExtra("songId");

        songTitle.setText(title);
        songAuthor.setText(author);
        songId = id;
    }

    private void audioManager() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(SongCardActivity.this, songId);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SongCardActivity.this, "In developing", Toast.LENGTH_SHORT).show();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playBtn.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                } else {
                    mediaPlayer.start();
                    playBtn.setImageResource(R.drawable.ic_pause_white_48dp);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SongCardActivity.this, "In developing", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(SongCardActivity.this, songId);
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
