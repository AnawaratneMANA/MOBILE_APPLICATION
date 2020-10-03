package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;

public class Project_panel extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bt = findViewById(R.id.AddProject);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_panel);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put List view destination.
                Intent newI = new Intent(Project_panel.this, MainActivity.class);
                startActivity(newI);
            }
        });
    }
}