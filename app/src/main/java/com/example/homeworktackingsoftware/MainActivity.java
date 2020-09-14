package com.example.homeworktackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;


//i have added "implementation 'com.karumi:dexter:6.2.1'" this one to Gradle Scripts build.grade please add it



public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    private Ringtone ringtone;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonAddAudio);
        button2 = (Button) findViewById(R.id.button3);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackground();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotification();
            }
        });

    }

    public void openNotification(){
        Intent intent = new Intent(this , Notification.class);
        startActivity(intent);
    }
    public void openBackground(){
        Intent intent = new Intent(this , Background.class);
        startActivity(intent);
    }
    public void onClick(View view) {
            Intent i = new Intent(this , Sounds.class);
            startActivity(i);
    }



}