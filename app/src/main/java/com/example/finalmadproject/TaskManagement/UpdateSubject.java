package com.example.finalmadproject.TaskManagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.finalmadproject.TaskManagement.Subject.SubjectEntry.SUBJECT_ID;
import static com.example.finalmadproject.TaskManagement.Subject.SubjectEntry.SUBJECT_NAME;

public class UpdateSubject extends AppCompatActivity {

    //Declare buttons
    private Button delete;
    private Button update;
    private EditText name;

    //Create Local variables for data comming through the intent.
    private String old_name;
    private int old_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);
        //Database Configuration
        //Creating a Database Instance
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //Register all the elements
        delete = findViewById(R.id.btnDeleteSub);
        update = findViewById(R.id.btnUpdateSub);
        name = findViewById(R.id.SubjectName);

        //Getting data from the intent
        Intent receivingIntent = getIntent();

        //Assign intent values to the local variables
        old_name = receivingIntent.getStringExtra("name");
        //Getting the subject ID of the name
        Cursor id = databaseHelper.readSubjectsName(old_name, database);
        id.moveToNext();
        try{
            old_id = Integer.parseInt(id.getString(id.getColumnIndex(SUBJECT_ID)));
        } catch(java.lang.NumberFormatException e){
            e.printStackTrace();
        }


        //send the text to the UI
        name.setText(old_name);

        //Create Onclick Listeners to Update and Delete method
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a Database Instance
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                //Getting the new name from the UI
                String new_name = name.getText().toString();
                //Duplication Validation  for updating.
                Cursor obj = databaseHelper.readSubjects(database);
                //Duplication validation
                String database_name = "";
                int database_id = -9;
                int flag = 0;
                try{
                    for(obj.moveToFirst(); !obj.isAfterLast(); obj.moveToNext()){
                        database_name = obj.getString(obj.getColumnIndex(SUBJECT_NAME));
                        database_id = Integer.parseInt(obj.getString(obj.getColumnIndex(SUBJECT_ID)));
                        if(database_name.contentEquals(new_name)){
                            flag++;
                            break;
                        }
                    }
                } catch(CursorIndexOutOfBoundsException e){
                    e.printStackTrace();
                }

                boolean status = false;

                //Calling the update query. -- Check this one again.
                if((flag == 1 && old_id != database_id)){
                    status = false;
                    System.out.println("1-------------------------------");
                }else if((flag == 1 && old_id == database_id)){
                    //Update the same value with the same name.
                    status = databaseHelper.UpdateSubject(new_name, old_name);
                    System.out.println("2-------------------------------");
                } else if(flag == 0) {
                    obj.moveToFirst();
                    database_name = obj.getString(obj.getColumnIndex(SUBJECT_NAME));
                    if(database_name.contentEquals(new_name)){
                        System.out.println("3E-------------------------------");
                    } else {
                        System.out.println("3-------------------------------");
                        //Safe Update when such value is not there.
                        status = databaseHelper.UpdateSubject(new_name, old_name);
                        System.out.println(new_name + "   " + database_name);
                    }

                }

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
                    builder.setMessage("Subject is already there!")
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

        //Validations should be made to the update function and the All insert functions
        //Delete function make sure when there's no ID then show a error message to the user.
        //Instead using dialog validation use Hint validations for forms
        //Change the animation transition when open the application make it happen only one time.
        //Move the animation triggering method from onCreateView to onCreate method.
    }
}
