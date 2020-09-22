package com.example.finalmadproject.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;

import java.util.ArrayList;

public class Sounds extends AppCompatActivity {

    private Button btn;
    ListView songList;
    CustomMusicAdapter adapter;
    ArrayList<SongList> array;
    DatabaseHelper databaseHelper;
    Button refreshInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
        refreshInterface = findViewById(R.id.refresh);
        songList = (ListView) findViewById(R.id.DatabaseSongListView);
        databaseHelper = new DatabaseHelper(this);
        array = new ArrayList<>();
        viewAudio();
        refreshInterface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshInterface();
            }
        });
        btn = findViewById(R.id.buttonAddAudio);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioListFragment audioListFragment = new AudioListFragment();
                audioListFragment.show(getSupportFragmentManager(),"myFragment");
            }
        });
    }

    public void refreshInterface() {
        Intent intent = new Intent(this , Sounds.class);
        startActivity(intent);
    }

    private void viewAudio() {
        array = databaseHelper.getAllAudios();
        adapter = new CustomMusicAdapter(this , array);
        songList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}