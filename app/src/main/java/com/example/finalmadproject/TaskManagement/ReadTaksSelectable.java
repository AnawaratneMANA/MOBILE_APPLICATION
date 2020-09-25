package com.example.finalmadproject.TaskManagement;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;

//This file will be used to select task and add them to the List.
public class ReadTaksSelectable extends AppCompatActivity {
    //Interface Items
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_taks_selectable);
        //Call the method
        readTasks();
        //Register Items
        list = findViewById(R.id.listView);
    }

    //Creating method to read the Tasks and add them to the list
    public void readTasks(){
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //Create this process in a separate thread.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = databaseHelper.readTasks(database);
        list = findViewById(R.id.listView);

        //Loop to iterate the entire cursor object
        String string = "";
        ArrayList<String> listName = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList();
        while(cursor.moveToNext())
        {
            //String id = Integer.toString(cursor.getColumnIndex(TASK_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));

            listName.add(name);
            System.out.println(name); //Testing
        }

        //Creating a ListAdapter and pass the arrayList
        ListAdapter adapter = new ArrayAdapter<>(ReadTaksSelectable.this, android.R.layout.simple_list_item_1,listName);
        list.setAdapter(adapter); //Creating Update function on DialogFragment.
    }
}