package com.example.homeworktackingsoftware;

import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SettingsFragment extends Fragment {
    private Ringtone ringtone;
    private Button button;
    private Button button2;
    private Button button3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1 = inflater.inflate(R.layout.fragment_settings, container, false);
        button = (Button) v1.findViewById(R.id.buttonNotification);
        button2 = (Button) v1.findViewById(R.id.buttonBackground);
        button3 = (Button) v1.findViewById(R.id.buttonSounds) ;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainSettingsLayout , notificationFragment).addToBackStack(null);
                transaction.commit();
                 //notificationFragment.show(getSupportFragmentManager() , "myFragmentNotifiaction");

            }
        });
        return v1;
    }
}