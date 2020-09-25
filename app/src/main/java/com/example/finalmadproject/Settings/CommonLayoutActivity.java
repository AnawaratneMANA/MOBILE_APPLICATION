//This class is temporary class should be removed afterwards
package com.example.finalmadproject.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.List.MainActivity_List;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;
import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;
//Register all the elements

public class CommonLayoutActivity extends AppCompatActivity {

    //Declare elements
    private Button settings;
    private Button home;
    private Button list;
    private Button tempList;
    private TextView txt_na;
    public static String string_name;

    // initializing variable
    //implemented by tandin
    DrawerLayout drawerLayout;
    //end of implementation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonlayout);

        //implemented by tandin
        //Getting data from login
        Intent i = getIntent();
        String variable = i.getStringExtra("name");
        //used for testing purpose
        //System.out.println(variable);

        //opening the navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        //end of implementation

        //Register elements
        settings = findViewById(R.id.button_settings);
        home = findViewById(R.id.button_task_manager);
        list = findViewById(R.id.button_list);
        tempList = findViewById(R.id.button_LIst);

        txt_na = findViewById(R.id.txt_name);
        //getting the value of FN from UN
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();


        Cursor name = db.getName(database, variable);
        name.moveToNext();
        string_name = name.getString(name.getColumnIndex("FN"));
        txt_na.setText("Hello, "+ String.valueOf(string_name));
       //used for testing purpose
       // System.out.println(string_name);



        //set on click listener to the buttons
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateTask = new Intent(CommonLayoutActivity.this, MainActivity_sl.class);
                startActivity(updateTask);
            }
        });

        //set on click listener to the home button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navtask = new Intent(CommonLayoutActivity.this, MainActivity.class);
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                startActivity(navtask);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navtask = new Intent(CommonLayoutActivity.this, MainActivity_List.class);
                startActivity(navtask);
            }
        });

        //Create a temporary Listener for connecting the List view
        tempList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempList = new Intent(CommonLayoutActivity.this, ReadTaksSelectable.class);
                startActivity(tempList);
            }
        });

    }

    //tandin implementation
    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout

        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //closing the layout
        //check conditon
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed(){

    }
}