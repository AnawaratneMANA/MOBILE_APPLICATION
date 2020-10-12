package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;
import com.example.finalmadproject.Settings.SongList;

import java.util.ArrayList;

public class reminderapp extends AppCompatActivity {
    private EditText hr, mi;
    Button b5;
    String variableInfo, variableDate;
    private MediaPlayer mediaPlayer;
    DatabaseHelper mydb;


    int hrs,mis;

    public reminderapp() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminderapp);
        mydb = new DatabaseHelper(this);
        final String path = mydb.getAudiofilepathSelected();

        //songLists = songList.get();

        hr = (EditText)findViewById(R.id.hour_set_text);
        mi = (EditText)findViewById(R.id.min_set_text);


        b5 = findViewById(R.id.button5);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hrs = Integer.parseInt(hr.getText().toString());
                mis = Integer.parseInt(mi.getText().toString());

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

                intent.putExtra(AlarmClock.EXTRA_RINGTONE , path);
                intent.putExtra(AlarmClock.EXTRA_HOUR,hrs);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,mis);

                if(hrs <= 24 && mis <=60){
                    Toast.makeText(reminderapp.this, "Marked", Toast.LENGTH_SHORT).show();


                    Intent jk = new Intent(reminderapp.this , CommonLayoutActivity.class);
                    jk.putExtra("hr", hrs);
                    jk.putExtra("min",mis);
                    startActivity(jk);
                    startActivity(intent);
                }


            }
        });
    }

}