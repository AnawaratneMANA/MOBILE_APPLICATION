package com.example.finalmadproject.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.T_MainActivity;


public class BackgroundFragment extends Fragment {

    Switch darkMode ;
    Button profile;
    Button signout;
    ImageView settingsTravel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v2 = inflater.inflate(R.layout.fragment_background, container, false);
        profile = v2.findViewById(R.id.ProfilesettingsButton);
        signout = v2.findViewById(R.id.Signout);
        settingsTravel = v2.findViewById(R.id.setIcon);

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
         darkMode = (Switch)  v2.findViewById(R.id.switchDarkMode);
        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean turnToDark) {
                if(turnToDark){
                    /**AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeS**/
                }else{

                }
            }
        });

        return v2;
    }
    //not working -------------------


}