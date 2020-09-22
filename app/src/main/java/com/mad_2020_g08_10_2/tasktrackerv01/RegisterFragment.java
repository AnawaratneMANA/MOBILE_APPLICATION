package com.mad_2020_g08_10_2.tasktrackerv01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegisterFragment extends Fragment {


    //Variable declaration
    private EditText s1;
    private Button registration;

    //Variables
    private String name;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        s1 =  view.findViewById(R.id.et_name);
        registration = view.findViewById(R.id.btn_register);
        //Get the value
        name = s1.getText().toString();

        //Set a onclick listener to the button
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(name);
            }
        });



        return view;

        //When getting values for the registration you have to get them from the fragment not the MainActivity.

    }

}