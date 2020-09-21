package com.example.homeworktackingsoftware;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter {
    private Context context;
    ArrayList<SongList> songList;
    //added
    TextView textName , textID;
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
            //added
            textID = (TextView) view.findViewById(R.id.textID);
            textName = (TextView) view.findViewById(R.id.textName);
            btnPlay = (Button) view.findViewById(R.id.playButton);
            btnPause = (Button) view.findViewById(R.id.pauseButton);
            btnDelete = (Button) view.findViewById(R.id.deleteButton);
            mydb = new DatabaseHelper(this.context);
            btnAssignToAlarm = (Button) view.findViewById(R.id.assignToButton);
            final SongList songLists = songList.get(i);
            String songname2 = songLists.getSongName();
            String songname = songname2.substring(songname2.length() - 10);
            textName.setText(songname);
            //added
            textID.setText(songLists.getSongID());
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(songLists.getPath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                    } catch (IOException e) {
                        System.out.println("not workinng");
                        e.printStackTrace();
                    }

                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //added
                    System.out.println("Why not deleting");
                    System.out.println(songLists.getSongID());
                    int result = mydb.deleteAudios(songLists.getSongID());
                    Toast.makeText(context , "data deleted" , Toast.LENGTH_LONG).show();
                    view.getContext().startActivity(new Intent(context, Sounds.class));
                }
            });


        }


        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();

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
