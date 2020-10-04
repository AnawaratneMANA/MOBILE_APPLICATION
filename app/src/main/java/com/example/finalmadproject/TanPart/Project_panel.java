package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;
import com.example.finalmadproject.TaskManagement.UpdateSubject;

import java.util.ArrayList;

import static android.app.DownloadManager.COLUMN_TITLE;
import static android.content.ContentValues.TAG;
import static com.example.finalmadproject.TaskManagement.Subject.SubjectEntry.SUBJECT_NAME;

public class Project_panel extends AppCompatActivity {
    //Button bt;
    ListView listview;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_panel);
        //bt = (Button) findViewById(R.id.AddProject);
        listview = (ListView) findViewById(R.id.displayListMain);
        databaseHelper = new DatabaseHelper(Project_panel.this);
        readListName();
        /**
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put List view destination.
                Intent newI = new Intent(Project_panel.this, MainActivity.class);
                startActivity(newI);
            }
        });
         **/
    }
    public void readListName(){
        final Cursor cursor = databaseHelper.readAllData();
        //Creating an ArrayList
        arrayList = new ArrayList<>();
        String list_name;
        //Loop
        while(cursor.moveToNext()){
            list_name = cursor.getString(1);
            arrayList.add(list_name);
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
            listview.setAdapter(adapter);
        }

        //Creating an ArrayAdapter



    }

}