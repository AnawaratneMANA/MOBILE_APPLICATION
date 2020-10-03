//This class is temporary class should be removed afterwards
package com.example.finalmadproject.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.List.MainActivity_List;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.T_MainActivity;
import com.example.finalmadproject.TaskManagement.MainActivity;
import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;
//Register all the elements

public class CommonLayoutActivity extends AppCompatActivity {

    //Declare elements
    private TextView txt_na;
    public static String string_name;
    private Button bt,bt1;

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
        bt = findViewById(R.id.AddProject);//add tanish akki link
        bt1 =findViewById(R.id.AddTask);//add akash link

        //Link to List Management.
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newI = new Intent(getApplicationContext(), MainActivity_List.class);
                startActivity(newI);
            }
        });

        //Link to Task Manager
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newI = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newI);
            }
        });



        //end of implementation

        //Register elements

        txt_na = findViewById(R.id.txt_name);
        //getting the value of FN from UN
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();


        Cursor name = db.getName(database, variable);
        name.moveToNext();
        try {
            string_name = name.getString(name.getColumnIndex("FN"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        txt_na.setText("Hello, "+ String.valueOf(string_name));
       //used for testing purpose
       // System.out.println(string_name);



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
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed(){

    }
    public void openProfile(View view){
        //redirectProfile(getActivity() , MainActivity_sl.class);
    }
    public void signout(View view){
        redirectProfile(this , T_MainActivity.class);
    }
    public void openSettings(View view){
        redirectProfile(this , MainActivity_sl.class);
    }
    //not working -------------------
    public static void redirectProfile(Activity activity , Class aclass){
        Intent intent = new Intent(activity , aclass);
        activity.startActivity(intent);
    }

}