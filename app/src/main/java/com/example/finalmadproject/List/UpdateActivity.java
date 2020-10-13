package com.example.finalmadproject.List ;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;
import com.example.finalmadproject.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalmadproject.R;
public class UpdateActivity extends AppCompatActivity {

    EditText title_input,description;
    Button update_button,del_button , taskTravelButton;

    String id,title,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //passing ids
        title_input = findViewById(R.id.title_input2);
        description = findViewById(R.id.description2);
        update_button = findViewById(R.id.update_button);
        del_button = findViewById(R.id.delete_button);
        taskTravelButton = findViewById(R.id.add_task);

        taskTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, ReadTaksSelectable.class);
                startActivity(intent);
            }
        });

        getAndSetIntentData();

        // set actionbar title after getAndSetIntentData method(Title name display upper corner)
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHandler = new DatabaseHelper(UpdateActivity.this);
                //passing the string value of edit test title_input
                title = title_input.getText().toString().trim();
                //passing the string value of edit text description
                des = description.getText().toString().trim();
                boolean result = dbHandler.updateData(id,title,des);

                if(result == true){
                    Toast.makeText(getApplicationContext(),"Updated Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        //After click delete button delete one row data
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        //getAndSetIntentData();
    }

    //Display selected data field title and description after entered the update interface
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description")){

            //Getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            des = getIntent().getStringExtra("description");

            //Setting Intent Data
            title_input.setText(title);
            description.setText(des);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    //when click delete button popup Dialog box
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHandler = new DatabaseHelper(UpdateActivity.this);
               boolean result =  dbHandler.deleteOneRow(id);
               if(result == true){
                   Toast.makeText(getApplicationContext(), "Successfully delete", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(getApplicationContext(),"Failed to delete", Toast.LENGTH_SHORT).show();
               }
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}