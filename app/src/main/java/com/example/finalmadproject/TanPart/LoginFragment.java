package com.example.finalmadproject.TanPart;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;
<<<<<<< HEAD
=======
import com.example.finalmadproject.TaskManagement.MainActivity;
>>>>>>> origin/temp_final_1

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    EditText UN,PW;
    Button b1;
    DatabaseHelper db;
    private int count = 5;
    private TextView Info;



    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        //for login
        //db = new DatabaseHelper(this);

        UN = view.findViewById(R.id.et_email);
        PW = view.findViewById(R.id.et_password);
        b1 = view.findViewById(R.id.btn_login);
        Info = view.findViewById(R.id.tvInfo);

        //function called to disable the button if values are not entered.
        UN.addTextChangedListener(loginTextWatcher);
        PW.addTextChangedListener(loginTextWatcher);


        //sending values
        

        Info.setText(" ");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db= new DatabaseHelper(getActivity());
                String e1 = UN.getText().toString();
                String e2 = PW.getText().toString();

                Boolean chk = db.UNPW(e1, e2);
                if(chk == true){
                    Toast.makeText(getContext(),"successfully Login", Toast.LENGTH_SHORT).show();

                    Intent st = new Intent(getActivity(), CommonLayoutActivity.class);
<<<<<<< HEAD
=======

>>>>>>> origin/temp_final_1
                    startActivity(st);


                }else{
                    Toast.makeText(getContext(),"Invalid Login", Toast.LENGTH_SHORT).show();
                    count --;

                    Info.setText("No of attempts remaining: "+ String.valueOf(count));

                    if(count <= 3) {
                        if(count == 3) {
                            Toast.makeText(getContext(), "Forgot username or password, click on forgot to redirect", Toast.LENGTH_LONG).show();
                        }
                        if (count == 0) {
                            b1.setEnabled(false);
                            b1.setBackgroundColor(1);
                            Info.setText("Button disable due to too many wrong attempts");

                        }
                    }
                    System.out.println(count);
                }

            }
        });
      return view;
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameinput = UN.getText().toString().trim();
            String passwordinput = PW.getText().toString().trim();

            b1.setEnabled(!usernameinput.isEmpty()  && !passwordinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}