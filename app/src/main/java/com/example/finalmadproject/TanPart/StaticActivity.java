package com.example.finalmadproject.TanPart;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class StaticActivity extends AppCompatActivity {
    String variable;
    public static String string_name;
    private DatabaseHelper database;
    private SQLiteDatabase db;
    Button b1;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2;
    public PaageAdapter pageAdapter;



    private TextView un,settime,setdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);



        //getting current date
        String currentDate = new SimpleDateFormat("MMMM,yyyy", Locale.getDefault()).format(new Date());

        b1 = (Button) findViewById(R.id.home);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CommonLayoutActivity.class);
                startActivity(intent);
            }
        });
        //Create Database Instancesd
        database = new DatabaseHelper(this);
        db = database.getReadableDatabase();

        //Getting data from home page
        Intent i = getIntent();
        variable = i.getStringExtra("name");

        un = findViewById(R.id.uname);
        settime = findViewById(R.id.timeset);
        setdate = findViewById(R.id.Dateset);

        //getting the value of FN from UN --- Once these method are uncommented program do not open.
        Cursor name = database.getName(db, variable);
        name.moveToNext();
        try {
            string_name = name.getString(name.getColumnIndex("FN"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        //inserting the user name
        un.setText(String.valueOf(string_name));
        //getting current time
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                settime.setText(new SimpleDateFormat("hh:mm a").format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        setdate.setText(String.valueOf(currentDate));


        //page tab changer
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        viewPager = findViewById(R.id.viewpager);

        pageAdapter = new PaageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        System.out.println("table layout count =============================="+ tablayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                System.out.println("tab count =============================="+ tab.getPosition());

                if(tab.getPosition() == 0){
                    pageAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 1){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        System.out.println("tab layout ================="+ tablayout);
    }
}