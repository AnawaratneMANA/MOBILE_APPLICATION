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
    private Ringtone ringtone;
    private Button button2;
    private Button button3;
    FloatingActionButton logo;
    DatabaseHelper mydb;
    TextView ee;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1 = inflater.inflate(R.layout.fragment_settings, container, false);
        button2 = (Button) v1.findViewById(R.id.buttonBackground);
        button3 = (Button) v1.findViewById(R.id.buttonSounds) ;
        logo = v1.findViewById(R.id.add_buttonHome);
        ee = v1.findViewById(R.id.songee);
        mydb = new DatabaseHelper(getContext());
        String path = mydb.getAudiofilepathSelected();
        ee.setText(path);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , CommonLayoutActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundFragment backgroundFragment = new BackgroundFragment();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.mainSettingsLayout , backgroundFragment).addToBackStack(null);
                transaction1.commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , Sounds.class);
                startActivity(intent);
            }
        });

        return v1;
    }
}