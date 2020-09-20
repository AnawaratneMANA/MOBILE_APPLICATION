package com.example.homeworktackingsoftware;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
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