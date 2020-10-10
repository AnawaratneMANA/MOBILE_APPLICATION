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
    private TextView textheader, textdis;
    int taskvar, addtaskvar;
    public static String string_name, string_dis, string_tit;
    private DatabaseHelper database;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_action);

        //Create Database Instancesd
        database = new DatabaseHelper(this);
        db = database.getReadableDatabase();

        //getting values from listview
        Intent i = getIntent();
        taskvar = i.getIntExtra("taskPassingID", -1);
        addtaskvar = taskvar + 1;
        variable = i.getStringExtra("taskPassingUN");

        System.out.println("after the link id  :"+ addtaskvar);
        System.out.println("after the link name  :"+ variable);


        //getting the value of name
        Cursor name = database.getName(db, variable);
        name.moveToNext();
        try {
            string_name = name.getString(name.getColumnIndex("FN"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        //getting the value title
        Cursor tit = database.getdescription(db, addtaskvar);
        tit.moveToNext();
        try {
            string_tit = tit.getString(tit.getColumnIndex("Task_name"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.println(string_tit);

        //getting the value of discription
        Cursor dis = database.getdescription(db, addtaskvar);
        dis.moveToNext();
        try {
            string_dis = dis.getString(dis.getColumnIndex("Task_description"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.println(string_dis);

        //putting the values to the textview
        textheader = findViewById(R.id.textViewheadder);
        textheader.setText(String.valueOf(string_tit));

        textdis = findViewById(R.id.textView);
        textdis.setText(String.valueOf(string_dis));
    }
}