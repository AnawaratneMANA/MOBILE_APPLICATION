package com.example.finalmadproject.Settings;

import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SettingsFragment extends Fragment {
    //declare buttons
    private Button button2 , button3;
    //declare floating buttons
    FloatingActionButton logo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1 = inflater.inflate(R.layout.fragment_settings, container, false);
        button2 = (Button) v1.findViewById(R.id.buttonBackground);//giving the relevent id
        button3 = (Button) v1.findViewById(R.id.buttonSounds) ;//giving the relevent id
        logo = v1.findViewById(R.id.add_buttonHome);//giving the relevent id
        //adding a onclick listner to to the floating button
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing intent to the cpmmon layout
                Intent intent = new Intent(getActivity() , CommonLayoutActivity.class);
                //navigating to the relevent activity
                startActivity(intent);
            }
        });
        //adding a onclick listner to to the button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating the fragment object
                BackgroundFragment backgroundFragment = new BackgroundFragment();
                //fragmenttransaction
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                //giving relevent fragment
                transaction1.replace(R.id.mainSettingsLayout , backgroundFragment).addToBackStack(null);
                transaction1.commit();
            }
        });
        //adding a onclick listner to to the button
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing intent to the sound layout
                Intent intent = new Intent(getActivity() , Sounds.class);
                //navigating to the relevent activity
                startActivity(intent);
            }
        });

        return v1;
    }
}