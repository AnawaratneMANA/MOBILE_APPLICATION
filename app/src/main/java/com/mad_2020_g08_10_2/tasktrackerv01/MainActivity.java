package com.mad_2020_g08_10_2.tasktrackerv01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    //declaring objects for registration;
    EditText fN, uN, pW, pW2;
    Button b1;

    //Declare String variables
    String s1,s2,s3,s4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new LoginFragment());
        pagerAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
        //for registration
        db = new DatabaseHelper(this);
        fN = (EditText)findViewById(R.id.et_name);
        uN = (EditText)findViewById(R.id.et_email);
        pW = (EditText)findViewById(R.id.et_password);
        pW2 = (EditText)findViewById(R.id.et_repassword);
        b1 = (Button)findViewById(R.id.btn_register);

        //s1 = fN.getText().toString();


//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //Fetching data
//                //Updated - Akash
//                s1 = fN.getText().toString();
//                s2 = uN.getText().toString();
//                s3 = pW.getText().toString();
//                s4 = pW2.getText().toString();
//
//                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")){
//                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    if(s3.contentEquals(s4)){
//                        Boolean chkUN = db.chkUN(s2);
//                        if(chkUN==true){
//                            Boolean insert = db.insert(s1,s2,s3);
//                            if(insert == true){
//                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(getApplicationContext(),"User Name already exist", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//            }
//        });


    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragmentList = new ArrayList<>();
        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment){
            fragmentList.add(fragment);
        }
    }
}