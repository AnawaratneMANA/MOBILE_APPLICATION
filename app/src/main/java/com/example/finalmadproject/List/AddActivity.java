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

    EditText title_input, description_input;
    Button add_button;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    Cursor subjectsSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description);
        add_button = findViewById(R.id.add_button);
        databaseHelper = new DatabaseHelper(AddActivity.this);
        database = databaseHelper.getWritableDatabase();
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseHelper dbHandler = new DatabaseHelper(AddActivity.this);

                //validation
                if(!title_input.getText().toString().matches("[a-z,A-Z]*")){
                    title_input.setError("Enter only characters");
                    return;
                }
                else if(!description_input.getText().toString().matches("[a-z,A-Z]*")){
                    description_input.setError("Enter only characters");
                    return;
                }

                //Null value validation
               /* if(title_input.getText().toString() == null){
                    title_input.setError("Enter data");
                    return;
                }
                else if(description_input.getText().toString() == null){
                    description_input.setError("Enter Description");
                    return;
                }*/

               String title_name = title_input.getText().toString();
               String des_name = description_input.getText().toString();


                if(title_name.contentEquals("")){
                    title_input.setError("Enter the Title name");
                }else if(des_name.contentEquals("")){
                    description_input.setError("Enter the Description");
                }

                //----------------------------------------DUPLICATE VALUE VALIDATION---------------------------------------------

                else {
                    //Database operation should come in this section.
                    //Duplication avoiding method.
                    //Create a object from the SQLiteOpenHelper Class


                    subjectsSet = databaseHelper.readAllDataNew(database);

                    String name_from_DB= "";
                    int flag = 0;
                    try{
                        while(subjectsSet.moveToNext()){
                            name_from_DB = subjectsSet.getString(1);
                            //subjectsSet.getColumnIndex(COLUMN_TITLE)
                            System.out.println(name_from_DB);
                            System.out.println(subjectsSet.getColumnIndex(COLUMN_TITLE));

                            if(name_from_DB.contentEquals(title_name)){
                                flag++;
                                break;
                            }
                        }
                    } catch(CursorIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }


                    //If condition to check the name
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
                    } else {

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