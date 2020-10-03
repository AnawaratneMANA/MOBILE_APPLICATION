package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;

import java.util.ArrayList;

import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;

public class Task_panel extends AppCompatActivity {
    private Button bt;
    private ListView list;

    //Create a constructor
    public Task_panel(){
    super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bt = findViewById(R.id.AddTask);
        list =findViewById(R.id.listView);
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

    //Create a method to recreate the list view
    public void createView(DatabaseHelper database, SQLiteDatabase db){
        //Calling the database method.
        Cursor cursor = database.readTasks(db);

        //ArrayList.
        ArrayList<String> listName = new ArrayList<String>();
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));
            listName.add(name);
        }
        //Setting the Adapter
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listName);
        list.setAdapter(adapter);
    }

}