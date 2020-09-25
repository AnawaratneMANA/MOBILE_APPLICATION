package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;

public class Task_panel extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bt = findViewById(R.id.AddTask);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_panel);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newI = new Intent(Task_panel.this, MainActivity.class);
                startActivity(newI);
            }
        });

    }

}