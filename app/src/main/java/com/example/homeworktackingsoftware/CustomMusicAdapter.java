package com.example.homeworktackingsoftware;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter {
    private Context context;
    ArrayList<SongList> songList;
    TextView textName;
    Button btnPlay , btnPause , btnDelete , btnAssignToAlarm;
    private int layout;
    DatabaseHelper mydb;
    private MediaPlayer mediaPlayer;

    public CustomMusicAdapter(Context context, ArrayList<SongList> songList) {
        this.context = context;
        this.songList = songList;
    }
    @Override
    public Object getItem(int i) {
        return songList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.customlayout , null);
            textName = (TextView) view.findViewById(R.id.textName);
            btnPlay = (Button) view.findViewById(R.id.playButton);
            btnPause = (Button) view.findViewById(R.id.pauseButton);
            btnDelete = (Button) view.findViewById(R.id.deleteButton);
            btnAssignToAlarm = (Button) view.findViewById(R.id.assignToButton);

            final SongList songLists = songList.get(i);

            textName.setText(songLists.getSongName());
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(songLists.getPath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnAssignToAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
    @Override
    public int getCount() {
        return songList.size();
    }
}
