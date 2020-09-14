package com.example.homeworktrackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class UpdateTask extends AppCompatActivity {

    EditText etname;

    //Variable to Assign data coming from the Intent
    String old_name;
    int Id_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        //Register Interface Elements
        etname = findViewById(R.id.TaskName);

        //Filling the spinner
        DatabaseHelper databaseHelper = new DatabaseHelper(this); //Check this.
        ArrayList<String> subject_list = databaseHelper.getAllSubjects();
        final Spinner subjectSpinner = (Spinner) findViewById(R.id.SubjectSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,subject_list);
        subjectSpinner.setAdapter(adapter);

        //Make sure the Drop down menu works. When connected.

        //Get the intent
        Intent recivingIntent = getIntent();

        //Assign values to local variables
//        old_name = recivingIntent.getStringExtra("name");
//        Id_number = Integer.parseInt(recivingIntent.getStringExtra("id"));
//        //Set the value to the Element
//        etname.setText(Id_number);

        //Create the ClickListener to Update the Data In the database.
        //Create a Update method in the DBHelper class.
        //Create a Delete method
    }

}