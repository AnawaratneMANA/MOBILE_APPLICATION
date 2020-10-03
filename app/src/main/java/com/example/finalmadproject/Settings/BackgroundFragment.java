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

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.T_MainActivity;

import static com.example.finalmadproject.Settings.Notification.notification.NOTIFICATION;
import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;


public class BackgroundFragment extends Fragment {

    Switch notification;
    Button profile;
    Button signout;
    ImageView settingsTravel;
    DatabaseHelper mydb;
    ImageView logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v2 = inflater.inflate(R.layout.fragment_background, container, false);
        profile = v2.findViewById(R.id.ProfilesettingsButton);
        signout = v2.findViewById(R.id.Signout);
        settingsTravel = v2.findViewById(R.id.setIcon);
        notification = v2.findViewById(R.id.notification);

        mydb = new DatabaseHelper(this.getContext());
        logo = v2.findViewById(R.id.company_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , CommonLayoutActivity.class);
                startActivity(intent);
            }
        });
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean enableTrue) {
                if(enableTrue){
                    String enable = "enable";
                    boolean value = mydb.updateEnableStatus(enable);
                    if(value == true){
                        Toast.makeText(getContext() , "Status is updating" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext() , "error occured" , Toast.LENGTH_LONG).show();
                    }
                }else{
                    boolean value = mydb.updateDisableStatus();
                    if(value == true){
                        Toast.makeText(getContext() , "wow" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext() , "error occured" , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        settingsTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , MainActivity_sl.class);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , T_MainActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                Intent intent = new Intent(getActivity() , MainActivity_sl.class);
                startActivity(intent);
                 */
            }
        });


        return v2;
    }
    //not working -------------------

}