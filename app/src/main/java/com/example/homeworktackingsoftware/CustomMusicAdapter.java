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
    //added
    TextView textName , textID;
    Button btnPlay , btnPause , btnDelete , btnAssignToAlarm;
    private int layout;
    DatabaseHelper mydb;
    private MediaPlayer mediaPlayer;

    /**
     *
     *public CustomAdapter(Context context, int custom_adapter, ArrayList<Model> mModel) {
     *     this.context = context;
     *     this.mModel = mModel;
     *     this.custom_adapter = custom_adapter;
     *     dba = new DatabaseHelper(context);
     * }
     */
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

            textName.setText(songLists.getSongName());
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
                    /**
                     if (status == true){
                     AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
                     builder.setMessage("Item Successfully Deleted")
                     .setCancelable(false)
                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                     //Navigate back to the MainActivity.
                     Intent headingback = new Intent(getApplication(), MainActivity.class);
                     startActivity(headingback);
                     }
                     });
                     AlertDialog alert = builder.create();
                     alert.show();
                     } else {
                     AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                     builder.setMessage("Error in Deleting the Item!")
                     .setCancelable(false)
                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                     //Navigate back to the MainActivity.
                     Intent headingback = new Intent(getApplication(), MainActivity.class);
                     startActivity(headingback);
                     }
                     });
                     AlertDialog alert = builder.create();
                     alert.show();
                     //
                     }**/
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
