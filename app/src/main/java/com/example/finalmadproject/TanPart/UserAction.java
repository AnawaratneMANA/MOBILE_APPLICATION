package com.example.finalmadproject.TanPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;
import com.example.finalmadproject.TaskManagement.MainActivity;

public class UserAction extends AppCompatActivity {
    String variable;
    private TextView textheader, textdis;
    int taskvar, addtaskvar;
    public static String string_name, string_dis, string_tit,string_fv;

    DatabaseHelper check = new DatabaseHelper(this);
    private DatabaseHelper database;
    private SQLiteDatabase db;
    Button b1,b2,b3,b4;
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

//        //getting the  flag value
//        Cursor fv = database.getfv(db, addtaskvar);
//        fv.moveToNext();
//        try {
//            string_fv = fv.getString(fv.getColumnIndex("T_ti"));
//        }catch (CursorIndexOutOfBoundsException e){
//            e.printStackTrace();
//        }
//        System.out.println("========================="+string_fv);

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

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "FLAG";
                check= new DatabaseHelper(UserAction.this);

                Boolean chkfl = check.chkfl(addtaskvar);

                if(chkfl == true){
                    Boolean insert = check.insert_flag(addtaskvar, value);

                    if (insert == true) {
                        Toast.makeText(UserAction.this, "Marked", Toast.LENGTH_SHORT).show();
                        Intent st = new Intent(UserAction.this, CommonLayoutActivity.class);
                        startActivity(st);
                    }
                }else{
                    Toast.makeText(UserAction.this, "Flag already exist", Toast.LENGTH_SHORT).show();
                    confirmDialog();

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "REMIND";
                check= new DatabaseHelper(UserAction.this);

                Boolean chkfl = check.chkfl(addtaskvar);

                if(chkfl == true){
                    Boolean insert = check.insert_flag(addtaskvar, value);

                    if (insert == true) {
                        Toast.makeText(UserAction.this, "Marked", Toast.LENGTH_SHORT).show();
                        Intent st = new Intent(UserAction.this, CommonLayoutActivity.class);
                        startActivity(st);
                    }
                }else{
                    Toast.makeText(UserAction.this, "Flag already exist", Toast.LENGTH_SHORT).show();
                    confirmDialog();

                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "TEMP";
                check= new DatabaseHelper(UserAction.this);

                Boolean chkfl = check.chkfl(addtaskvar);

                if(chkfl == true){
                    Boolean insert = check.insert_flag(addtaskvar, value);

                    if (insert == true) {
                        Toast.makeText(UserAction.this, "Marked", Toast.LENGTH_SHORT).show();
                        Intent st = new Intent(UserAction.this, CommonLayoutActivity.class);
                        startActivity(st);
                    }
                }else{
                    Toast.makeText(UserAction.this, "Flag already exist", Toast.LENGTH_SHORT).show();
                    confirmDialog();

                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent st = new Intent(UserAction.this, CommonLayoutActivity.class);
                startActivity(st);
            }
        });


    }

    //when click delete button popup Dialog box
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Flag");
        builder.setMessage("Do you want to delete!");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHandler = new DatabaseHelper(UserAction.this);
                boolean result =  dbHandler.deleteflag(addtaskvar);
                if(result == true){
                    System.out.println("this is in profile:"+addtaskvar);
                    Toast.makeText(getApplicationContext(), "Flag Deleted", Toast.LENGTH_SHORT).show();

                }else{

                    System.out.println(addtaskvar);
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