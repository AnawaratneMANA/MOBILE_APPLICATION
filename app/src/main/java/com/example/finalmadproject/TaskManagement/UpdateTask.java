package com.example.finalmadproject.TaskManagement;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;

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
                boolean status;
                //Empty textbox validation.
                if(etname.getText().toString().contentEquals("")){
                    etname.setError("Enter the Name!");
                    return; //caution
                }
                if (etdescription.getText().toString().contentEquals("")){
                    etdescription.setError("Enter the Description!");
                    return;
                }

                 else {
                    DatabaseHelper dbhelper = new DatabaseHelper(getApplication());
                    //Get data from the database
                    String name_existing = "";
                    try {
                        Cursor resultSet = dbhelper.getFromItemID(etname.getText().toString());
                        resultSet.moveToNext();
                        name_existing = resultSet.getString(resultSet.getColumnIndex(TASK_NAME));
                    } catch (CursorIndexOutOfBoundsException e){
                        System.out.println("ResultSet is empty");
                    }

                    //Check the Cursor for the name value
                    if (!(name_existing.contentEquals(old_name))){
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                        builder.setMessage("Subject is already exist in the database.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Create a Instruction Message
                                        Toast.makeText(UpdateTask.this, "Enter the values again", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        //If no  any issues then write to the db
                        status = dbhelper.UpdateDetails(old_name, etname.getText().toString(),etdescription.getText().toString(), selected_subject,  date.getText().toString());
                    }
                }

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

                if (status == true){
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