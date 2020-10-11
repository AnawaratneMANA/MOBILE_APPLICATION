package com.example.finalmadproject.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sounds extends AppCompatActivity {

    private Button btn;
    ListView songList;
    CustomMusicAdapter adapter;
    ArrayList<SongList> array;
    DatabaseHelper databaseHelper;
    FloatingActionButton logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
        songList = (ListView) findViewById(R.id.DatabaseSongListView);
        databaseHelper = new DatabaseHelper(this);
        array = new ArrayList<>();
        viewAudio();
        logo = findViewById(R.id.add_buttonHome);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sounds.this, CommonLayoutActivity.class);
                startActivity(intent);
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
    private void viewAudio() {
        array = databaseHelper.getAllAudios();
        adapter = new CustomMusicAdapter(this , array);
        songList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}