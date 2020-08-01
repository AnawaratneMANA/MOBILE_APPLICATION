package com.mad_2020_g08.tasktrackerversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,description;
    Button update_button;

    String id,title,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description = findViewById(R.id.description2);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler dbHandler = new DbHandler(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                des = description.getText().toString().trim();
                dbHandler.updateData(id,title,des);
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
}