package com.example.finalmadproject.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.finalmadproject.R;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;

public class AddActivity extends AppCompatActivity {

    EditText title_input, description_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHandler = new DatabaseHelper(AddActivity.this);

                if(!title_input.getText().toString().matches("[a-z,A-Z]*")){
                    title_input.setError("Enter only characters");
                    return;
                }
                else if(!description_input.getText().toString().matches("[a-z,A-Z]*")){
                    description_input.setError("Enter only characters");
                    return;
                }

                dbHandler.addList(title_input.getText().toString().trim(),
                        description_input.getText().toString().trim());



            }
        });


    }
}