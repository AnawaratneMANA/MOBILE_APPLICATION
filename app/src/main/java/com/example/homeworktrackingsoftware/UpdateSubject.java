package com.example.homeworktrackingsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateSubject extends AppCompatActivity {

    //Declare buttons
    private Button delete;
    private Button update;
    private EditText name;

    //Create Local variables for data comming through the intent.
    private String old_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        //Register all the elements
        delete = findViewById(R.id.btnDeleteSub);
        update = findViewById(R.id.btnUpdateSub);
        name = findViewById(R.id.SubjectName);

        //Getting data from the intent
        Intent receivingIntent = getIntent();

        //Assign intent values to the local variables
        old_name = receivingIntent.getStringExtra("name");

        //send the text to the UI
        name.setText(old_name);

        //Create Onclick Listeners to Update and Delete method
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a Database Instance
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
                //Getting the new name from the UI
                String new_name = name.getText().toString();
                //Calling the update query.
                boolean status = databaseHelper.UpdateSubject(new_name, old_name);

                //Confirmation
                if (status == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSubject.this);
                    builder.setMessage("Subject Successfully Updated")
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSubject.this);
                    builder.setMessage("Error in Updating the Subject!")
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

        //Create a Onclick Listener for the Delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a Database Instance
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
                //Getting the new name from the UI
                String new_name = name.getText().toString();
                //Calling Delete method from the DatabaseHelper class
                boolean status = databaseHelper.DeleteSubject(old_name);
                //Confirmation.
                if (status == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSubject.this);
                    builder.setMessage("Subject Successfully Deleted")
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSubject.this);
                    builder.setMessage("Error in Deleting the Subject!")
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
}