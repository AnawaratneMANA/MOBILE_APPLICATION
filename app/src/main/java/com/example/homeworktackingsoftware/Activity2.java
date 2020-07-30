package com.example.homeworktackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View v){
                                          openActivity3();

                                      }
                                  }
        );
    }
    public void openActivity3(){
        Intent intent1 = new Intent(this,Activity3.class);
        startActivity(intent1);
    }
}