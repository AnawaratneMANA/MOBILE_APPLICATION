package com.example.finalmadproject.List;

import android.content.Intent;
import android.os.Bundle;
import com.example.finalmadproject.R;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(Splash.this, MainActivity_List.class));
    }
}