package com.example.finalmadproject.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalmadproject.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.Settings.Sounds;

import static android.app.DownloadManager.COLUMN_TITLE;

public class AddActivity extends AppCompatActivity {
    //declare variable
    EditText title_input, description_input;//declaring edittext
    Button add_button;//declaring
    DatabaseHelper databaseHelper;//declaring databasehelper object
    SQLiteDatabase database;
    Cursor subjectsSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //passing ids
        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description);
        add_button = findViewById(R.id.add_button);

        //initialize database helper object
        databaseHelper = new DatabaseHelper(AddActivity.this);
        //write the data in db
        database = databaseHelper.getWritableDatabase();
        //onclick
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseHelper dbHandler = new DatabaseHelper(AddActivity.this);

                //validation
                /*if(!title_input.getText().toString().matches("[a-z,A-Z]*")){
                    title_input.setError("Enter only characters");
                    return;
                }
                else if(!description_input.getText().toString().matches("[a-z,A-Z]*")){
                    description_input.setError("Enter only characters");
                    return;
                }*/

                //passing the string value of edit text title_input
               String title_name = title_input.getText().toString();
                //passing the string value of edit text description_input
               String des_name = description_input.getText().toString();

                //null value passing value
                //check whether the title and des null
                //when no value entered it shows a error message
                if(title_name.contentEquals("")){
                    title_input.setError("Enter the Title name");
                }else if(des_name.contentEquals("")){
                    description_input.setError("Enter the Description");
                    //-------------------duplicate value validation-----------------------------
                } else {

                    //return a cursor object through the method readAllDataNew method in databaseHelper
                    subjectsSet = databaseHelper.readAllDataNew(database);
                    //initialize a null value to the string
                    String name_from_DB= "";
                    int flag = 0;// int value initialized for check the duplicate value
                    try{
                        //cursor object is incrementing one by one
                        while(subjectsSet.moveToNext()){
                            //passing the string value of  "id 1" in relevent cursor object
                            name_from_DB = subjectsSet.getString(1);
                            //duplicate value validation added here
                            //here we are checking the relevent name from db value is equal to the title name (passing value)
                            if(name_from_DB.contentEquals(title_name)){
                                flag++;//incrementing the flag value
                                break;
                            }
                        }
                    } catch(CursorIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }


                    //check whether flag value incremented by 1
                    if(flag == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                        builder.setMessage("List name is already in the database.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Create a Instruction Message
                                        Toast.makeText(getApplicationContext(), "Enter the values again", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        //duplication is not happened
                    } else {
                        //calling the inserting method in databasehelper
                        boolean result = databaseHelper.addList(title_input.getText().toString().trim(),
                                description_input.getText().toString().trim());

                        if(result == true){
                            Intent intent = new Intent(AddActivity.this, MainActivity_List.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Added Successfully!", Toast.LENGTH_SHORT).show();
                        }else{
                            //Intent intent = new Intent(AddActivity.this, MainActivity_List.class);
                            //startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }



                    }

                }



            }
        });


    }





                //-----------------------------------------------------------------------------------------------------------------

               /* boolean result = dbHandler.addList(title_input.getText().toString().trim(),
                        description_input.getText().toString().trim());

                if(result == true){
                    Intent intent = new Intent(AddActivity.this, MainActivity_List.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Added Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    //Intent intent = new Intent(AddActivity.this, MainActivity_List.class);
                    //startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }


                */
                //Intent intent = new Intent(AddActivity.this, MainActivity_List.class);
                //startActivity(intent);

            }