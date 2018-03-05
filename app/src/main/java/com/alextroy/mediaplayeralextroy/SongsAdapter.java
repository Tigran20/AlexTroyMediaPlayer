package com.alextroy.mediaplayeralextroy;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alextroy.mediaplayeralextroy.activities.SongCardActivity;
import com.alextroy.mediaplayeralextroy.model.Songs;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private Activity activity;
    private List<Songs> songsList;

    public SongsAdapter(Activity activity, List<Songs> songsList) {
        this.activity = activity;
        this.songsList = songsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_song, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.songTextView.setText(songsList.get(position).getTitle());
        viewHolder.artistTextView.setText(songsList.get(position).getArtist());
        viewHolder.durationTextView.setText(songsList.get(position).getDuration());
        viewHolder.songId = songsList.get(position).getAudioResourceId();
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView songTextView;
        private TextView artistTextView;
        private TextView durationTextView;
        private Uri songId;

        public ViewHolder(View view) {
            super(view);

            songTextView = view.findViewById(R.id.song);
            artistTextView = view.findViewById(R.id.artist);
            durationTextView = view.findViewById(R.id.duration);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SongCardActivity.class);
                    intent.putExtra("title", songTextView.getText().toString());
                    intent.putExtra("author", artistTextView.getText().toString());
                    intent.putExtra("songId", songId);
                    activity.startActivity(intent);
                }
            });
        }
    }
}


