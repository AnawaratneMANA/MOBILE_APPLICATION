package com.example.finalmadproject.TanPart;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;

public class UserAction extends AppCompatActivity {
String variable;
int taskvar, addtaskvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_action);

        //getting values from listview
        Intent i = getIntent();
        taskvar = i.getIntExtra("taskPassingID", -1);
        addtaskvar = taskvar + 1;
        variable = i.getStringExtra("taskPassingUN");

        System.out.println("after the link id  :"+ addtaskvar);
        System.out.println("after the link name  :"+ variable);


    }
}