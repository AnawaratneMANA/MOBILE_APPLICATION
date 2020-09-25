package com.example.finalmadproject.List;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;

import java.util.ArrayList;

public class ViewAllTasks extends AppCompatActivity {

    DatabaseHelper dbHandler;
    ArrayList<String> titleTask,desTask;
    Button addView;
    ReadTaksSelectable readTaksSelectable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_tasks);

        dbHandler = new DatabaseHelper(ViewAllTasks.this);
        //titleTask = new ArrayList<>();
        //desTask = new ArrayList<>();

        //new one
        storeTaskInArrays();

        addView = findViewById(R.id.btnView);

        //readTaksSelectable = new ReadTaksSelectable(ViewAllTasks.this, titleTask,desTask);

    }

    public void moveToTaskApplication(View view){
        Intent intent = new Intent(ViewAllTasks.this, ReadTaksSelectable.class);
        startActivity(intent);
    }

    //new Method
    void storeTaskInArrays(){
        Cursor cursor = dbHandler.readAlltasks();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
               // id.add(cursor.getString(0));
                //title.add(cursor.getString(1));
                //description.add(cursor.getString(2));
                titleTask.add(cursor.getString(0));
                desTask.add(cursor.getString(1));
            }
        }
    }
}