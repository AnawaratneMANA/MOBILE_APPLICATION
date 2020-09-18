package com.example.homeworktrackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etname;
    EditText etdescription;
    private Button update;
    private Button delete;
    private Spinner subjectspinner;
    private String selected_subject;

    //Variable to Assign data coming from the Intent
    String old_name;
    String subject_name;
    String description;
    String Date;
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
        etdescription = findViewById(R.id.DescriptionId);

        //Register the Buttons
        update = findViewById(R.id.taskUpdate);
        delete = findViewById(R.id.taskDelete);
        subjectspinner = findViewById(R.id.SubjectSpinner);
        delete = findViewById(R.id.taskDelete);

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

        //Get the intent
        Intent recivingIntent = getIntent();

        //Assign values to local variables
        old_name = recivingIntent.getStringExtra("name");
        subject_name = recivingIntent.getStringExtra("subject");
        System.out.println(subject_name); //Testing
        description = recivingIntent.getStringExtra("description");
        System.out.println(description); //Testing
        Date = recivingIntent.getStringExtra("date");
        System.out.println(Date); //Testing

        //Set the value to the Element
        etname.setText(old_name);
        etdescription.setText(description);
        date.setText(Date);

        //Get New values from the fields
        selected_subject = (String)subjectspinner.getSelectedItem();

        //Create the ClickListener to Update the Data In the database.
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbhelper = new DatabaseHelper(getApplication()); //Check
                //Calling the Database method Update.
                boolean status = dbhelper.UpdateDetails(old_name, etname.getText().toString(),etdescription.getText().toString(), selected_subject,  date.getText().toString());

                //Confirmation.
                if (true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                    builder.setMessage("Item Successfully Updated")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Navigate back to the MainActivity.
                                    Intent headingback = new Intent(getApplication(), MainActivity.class);
                                    startActivity(headingback);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                    builder.setMessage("Error in Updating the Item!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Navigate back to the MainActivity.
                                    Intent headingback = new Intent(getApplication(), MainActivity.class);
                                    startActivity(headingback);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        //Create a Delete method
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Object from the DatabaseHelper
                DatabaseHelper dbhelper = new DatabaseHelper(getApplication()); //Check

                //Calling the delete method
                boolean status = dbhelper.deleteData(old_name);

                if (true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                    builder.setMessage("Item Successfully Deleted")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Navigate back to the MainActivity.
                                    Intent headingback = new Intent(getApplication(), MainActivity.class);
                                    startActivity(headingback);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                    builder.setMessage("Error in Deleting the Item!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Navigate back to the MainActivity.
                                    Intent headingback = new Intent(getApplication(), MainActivity.class);
                                    startActivity(headingback);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });
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