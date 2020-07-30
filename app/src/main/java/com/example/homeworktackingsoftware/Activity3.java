package com.example.homeworktackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v){
                                           openActivity4();

                                       }
                                   }
        );
    }
    public void openActivity4(){
        Intent intent2 = new Intent(this,MainActivity4.class);
        startActivity(intent2);
    }
}