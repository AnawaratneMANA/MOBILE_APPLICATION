package com.example.finalmadproject.TanPart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;

public class profile extends AppCompatActivity {
    Button de,re;
    EditText reset_password;
    String variable,variable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        variable = i.getStringExtra("name");




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        de = findViewById(R.id.delete);
        re = (Button) findViewById(R.id.rpw);


        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                reset_password = (EditText) findViewById(R.id.npwd);
                variable2 =  reset_password.getText().toString();

                if(variable2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"cannot have null password", Toast.LENGTH_SHORT).show();
                }else {
                    confirmDialog_resetpwd();
                }
                System.out.println("inetial reset_password"+variable2);
            }
        });

    }


    //when click delete button popup Dialog box
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Are you sure you want to delete!");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHandler = new DatabaseHelper(profile.this);
                boolean result =  dbHandler.deleteUser(variable);
                if(result == true){
                    System.out.println("this is in profile:"+variable);
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(profile.this, T_MainActivity.class);
                    startActivity(intent);
                }else{

                    System.out.println(variable);
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


    void confirmDialog_resetpwd(){
        System.out.println("this is in profile:"+variable+" sdkjfh"+ variable2);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Are you sure you want to change your password?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHandler = new DatabaseHelper(profile.this);
                boolean result =  dbHandler.updateUserpwd(variable,variable2);
                if(result == true){

                    Toast.makeText(getApplicationContext(), "Password Reset", Toast.LENGTH_SHORT).show();

                }else{

                    //System.out.println(variable);
                    Toast.makeText(getApplicationContext(),"Failed to Reset your password", Toast.LENGTH_SHORT).show();

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