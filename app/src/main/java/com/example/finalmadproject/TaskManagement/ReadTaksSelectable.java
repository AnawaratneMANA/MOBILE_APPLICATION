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

        //Register Items
        list = findViewById(R.id.listView);
    }

    //Creating method to read the takss and add them to the list
    public void readTasks(){
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //Create this process in a separate thread.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = databaseHelper.readTasks(database);

        //Loop to iterate the entire cursor object
        String string = "";
        ArrayList<String> listName = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList();
        while(cursor.moveToNext())
        {
            //String id = Integer.toString(cursor.getColumnIndex(TASK_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));
            //String description = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION));
            //subject = cursor.getString(cursor.getColumnIndex(TASK_SUBJECT));
            //String date = cursor.getString(cursor.getColumnIndex(TASK_DATE));
            //Add files to the ArrayList
            listName.add(name);
            //listDescription.add(description);

            //concatenate the text into a variable
            /*string = string + "\n\n" + "TASK ID - " +id+  "\nTASK NAME - " +name+
                    "\nTASK DESCRIPTION - " + description+ "\nTASK SUBJECT - " +subject+ "\nTASK DATE - " +date;*/
        }

        //Creating a ListAdapter and pass the arrayList
        ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,listName);
        list.setAdapter(adapter); //Creating Update function on DialogFragment.
    }
}