package com.example.homeworktrackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etname;

    //Variable to Assign data coming from the Intent
    String old_name;
    int Id_number;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        //Register Interface Elements
        etname = findViewById(R.id.TaskName);
        //Getting Date and initialize the TextView
        date = findViewById(R.id.datetextview);

        //Filling the spinner
        DatabaseHelper databaseHelper = new DatabaseHelper(this); //Check this.
        ArrayList<String> subject_list = databaseHelper.getAllSubjects();
        final Spinner subjectSpinner = (Spinner) findViewById(R.id.SubjectSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,subject_list);
        subjectSpinner.setAdapter(adapter);

        //Create a OnClick Listener for the button - All Date related Methods
        findViewById(R.id.OpenDate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                showDatePickerDialog();
            }
        });





        //Make sure the Drop down menu works. When connected.

        //Get the intent
        Intent recivingIntent = getIntent();

        //Assign values to local variables
        old_name = recivingIntent.getStringExtra("name");
//        Id_number = Integer.parseInt(recivingIntent.getStringExtra("id"));
        //Set the value to the Element
        etname.setText(old_name);

        //Create the ClickListener to Update the Data In the database.
        //Create a Update method in the DBHelper class.
        //Create a Delete method
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        String string_date = year+ "-" + month + "-" + dayOfMonth;
        date.setText(string_date);
    }

}