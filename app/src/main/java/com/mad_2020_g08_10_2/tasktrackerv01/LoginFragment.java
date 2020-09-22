package com.mad_2020_g08_10_2.tasktrackerv01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    EditText UN,PW;
    Button b1;
    DatabaseHelper db;



    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //for login
        //db = new DatabaseHelper(this);

        UN = view.findViewById(R.id.et_email);
        PW = view.findViewById(R.id.et_password);

      return view;
    }
}