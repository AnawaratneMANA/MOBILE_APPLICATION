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
    CustomMusicAdapter adapter;//declaring the Custom adapter
    ArrayList<SongList> array;//declaring an array that has values in SONGLIST java file
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
    //displaying llistView rows through created adapter
    private void viewAudio() {
        //returning an array list from method getAllAUdios
        array = databaseHelper.getAllAudios();
        //assigning that array to the adapter
        adapter = new CustomMusicAdapter(this , array);//this is called in customMusicAdapter
        //then set that adapter to the listView
        songList.setAdapter(adapter);
        //mentioning to update the rows
        adapter.notifyDataSetChanged();
    }

}