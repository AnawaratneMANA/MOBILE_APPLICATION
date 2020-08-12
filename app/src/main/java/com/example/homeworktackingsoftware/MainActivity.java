package com.example.homeworktackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    private Ringtone ringtone;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
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


        protected void onResume() {
            super.onResume();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String path = preferences.getString("Ringtone" , "");
            Log.v(TAG , "path : " + path);
            /**if(!path.isEmpty()){
                ringtone = RingtoneManager.getRingtone(this , Uri.parse(path));
                ringtone.play();
            }
             **/
        }

}