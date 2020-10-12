package com.example.finalmadproject.Settings;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.finalmadproject.AlarmandNotification.Alarm;
import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.Project_panel;
import com.example.finalmadproject.TanPart.T_MainActivity;
import com.example.finalmadproject.TanPart.profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.finalmadproject.Settings.Notification.notification.NOTIFICATION;
import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;


public class BackgroundFragment extends Fragment {

    Switch notification;//declare switch
    Button profilee , signout;//declare button
    DatabaseHelper mydb;//declare databasehelper element
    FloatingActionButton logo;//declare floatingButton

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v2 = inflater.inflate(R.layout.fragment_background, container, false);
        profilee = v2.findViewById(R.id.ProfilesettingsButton);
        signout = v2.findViewById(R.id.Signout);
        notification = v2.findViewById(R.id.notification);
        logo = v2.findViewById(R.id.add_buttonHome);
        mydb = new DatabaseHelper(this.getContext());

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , CommonLayoutActivity.class);
                startActivity(intent);
            }
        });
        //adding onchechchangeListner to toggle button
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean enableTrue) {
                //if signal enable
                if(enableTrue){
                    //initialize a string value
                    String enable = "enable";
                    //return a boolean value to the database helper method that return a boolean value
                    boolean value = mydb.updateEnableStatus(enable);
                    if(value == true){
                        Toast.makeText(getContext() , "Status is updating" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext() , "error occured" , Toast.LENGTH_LONG).show();
                    }
                    //if signal enable
                }else{
                    boolean value = mydb.updateDisableStatus();
                    if(value == true){
                        Toast.makeText(getContext() , "status disable" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext() , "error occured" , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , Alarm.class);
                startActivity(intent);
            }
        });
        profilee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity() , profile.class);
                startActivity(intent);

            }
        });


        return v2;
    }
    //not working -------------------

}