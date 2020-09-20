package com.mad_2020_g08.tasktrackerversion2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,description;
    Button update_button,del_button;

    String id,title,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description = findViewById(R.id.description2);
        update_button = findViewById(R.id.update_button);
        del_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        // set actionbar title after getAndSetIntentData method(Title name display upper corner)
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler dbHandler = new DbHandler(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                des = description.getText().toString().trim();
                dbHandler.updateData(id,title,des);
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
                DbHandler dbHandler = new DbHandler(UpdateActivity.this);
                dbHandler.deleteOneRow(id);
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