package com.example.finalmadproject.Settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.T_MainActivity;
import com.example.finalmadproject.TanPart.profile;
public class BlankgroundSettings extends Fragment {


    Button profilee;
    Button Alarm;
    Button signout;
    ImageView settingsTravel;
    DatabaseHelper mydb;
    ImageView logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v2 = inflater.inflate(R.layout.fragment_background, container, false);
        profilee = v2.findViewById(R.id.ProfilesettingsButton2);
        Alarm = v2.findViewById(R.id.AlarmsettingsButton2);
        signout = v2.findViewById(R.id.Signout);
        settingsTravel = v2.findViewById(R.id.setIcon);

        //mydb = new DatabaseHelper(this.getContext());
        logo = v2.findViewById(R.id.company_logo);

        return v2;
    }
}