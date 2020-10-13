//This class is temporary class should be removed afterwards
package com.example.finalmadproject.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalmadproject.AlarmandNotification.Alarm;
import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.List.MainActivity_List;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TanPart.StaticActivity;
import com.example.finalmadproject.TanPart.T_MainActivity;
import com.example.finalmadproject.TanPart.Task_panel;
import com.example.finalmadproject.TanPart.UserAction;
import com.example.finalmadproject.TanPart.profile;
import com.example.finalmadproject.TaskManagement.HomeFragment;
import com.example.finalmadproject.TaskManagement.MainActivity;

import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;

import java.util.ArrayList;

import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_ID;
import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;





//Register all the elements
public class CommonLayoutActivity extends AppCompatActivity {

    //Declare elements
    private TextView txt_na;
    public static String string_name,string_fval;
    private Button bt,bt1,bt3;
    private ListView TaskPanel , ListPanel;
    private DatabaseHelper database;//declare databasehelper
    private SQLiteDatabase db;
    int hr,mi;
    String variable;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    // initializing variable
    //implemented by tandin
    DrawerLayout drawerLayout;
    //end of implementation



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonlayout);

        //Create Database Instancesd
        database = new DatabaseHelper(this);
        db = database.getReadableDatabase();

        //implemented by tandin
        //Getting data from login
        Intent i = getIntent();
        variable = i.getStringExtra("name");
        //used for testing purpose
        //System.out.println(variable);


        //getting the hr n min from the fflag
        hr = i.getIntExtra("hr",0);
        mi = i.getIntExtra("min",0);

        //opening the navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        bt = findViewById(R.id.AddProject);//add tanish akki link
        bt1 =findViewById(R.id.AddTask);//add akash link
        TaskPanel = findViewById(R.id.taskPanel);
        ListPanel = findViewById(R.id.listPanel);


        //Panel populating methods
        createView(database, db);
        readListName();
        //Link to List Management.
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newI = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newI);
            }
        });


        //Link to Task Manager
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newI = new Intent(getApplicationContext(), MainActivity_List.class);
                startActivity(newI);
            }
        });

        //Register elements
        txt_na = findViewById(R.id.txt_name);
        //getting the value of FN from UN --- Once these method are uncommented program do not open.
        Cursor name = database.getName(db, variable);
        name.moveToNext();
        try {
            string_name = name.getString(name.getColumnIndex("FN"));
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        txt_na.setText("Hello, "+ String.valueOf(string_name));
      
       //used for testing purpose
       // System.out.println(string_name);
       // Call the method from the database to populate the list. -- Akash Testing.
       //Task_panel panel = new Task_panel();
       //panel.createView(db, database);


        //linking to stat activity
        bt3 = (Button) findViewById(R.id.stat);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), StaticActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("name", variable);
                startActivity(intent);
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
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed(){

    }
    public void openProfile(View view){
        Intent st = new Intent(getApplicationContext(),profile.class);
        //sending data to make it a session
        Bundle bundle = new Bundle();
        st.putExtra("name", variable);
        System.out.println("inside the link :"+ variable);
        startActivity(st);
        //redirectProfile(this , profile.class);
    }
    //----------------------- added by salitha------------------------------------------
    //onclick method for alarm activity through button in blanckground_settings
    public void openAlarm(View view){
        confirmDialog();
    }
    //onclick method for signout activity through button in blanckground_settings
    public void signout(View view){
        redirectProfile(this , T_MainActivity.class);
    }
    //onclick method for settings fragment through button in blanckground_settings
    public void openSettings(View view){
        redirectProfile(this , MainActivity_sl.class);
    }
    //onclick method for profile activity through button in blanckground_settings
    public static void redirectProfile(Activity activity , Class aclass){
        Intent intent = new Intent(activity , aclass);
        activity.startActivity(intent);
    }

    //Get List Items

    public void createView(DatabaseHelper database, SQLiteDatabase db){
        //Calling the database method.
        Cursor cursor = database.readTasks(db);

        //ArrayList.
        final ArrayList<String> listName = new ArrayList<String>();
        int incr = 1;
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));
            //getting the value of flag
            Cursor fval = database.getfv(db, incr);
            fval.moveToNext();
            try {
                string_fval = fval.getString(fval.getColumnIndex("T_ti"));
            }catch (CursorIndexOutOfBoundsException e){
                e.printStackTrace();
            }

                listName.add(name + " (" + string_fval+")");
                System.out.println(name);



            incr++;
        }
        //Setting the Adapter

        Task_panel task = new Task_panel();
        ListAdapter adapter = new ArrayAdapter<>(CommonLayoutActivity.this,android.R.layout.simple_list_item_1,listName);
        TaskPanel.setAdapter(adapter);

        TaskPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CommonLayoutActivity.this , UserAction.class);
                //sending data to make it a session
                Bundle bundle = new Bundle();
                intent.putExtra("taskPassingID", position);
                intent.putExtra("taskPassingUN", variable);
                System.out.println("inside the link id  :"+ id);
                System.out.println("inside the link name :"+ variable);
                startActivity(intent);
            }
        });

    }

    public void readListName(){
        final Cursor cursor = database.readAllData();
        //Creating an ArrayList
        arrayList = new ArrayList<>();
        String list_name;
        //Loop
        while(cursor.moveToNext()){
            list_name = cursor.getString(1);
            arrayList.add(list_name);
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
            System.out.println("==============================="+adapter);
            ListPanel.setAdapter(adapter);
        }

        //Creating an ArrayAdapter



    }
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alarm");
        builder.setMessage("Do you want a instant alarm!");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent jklk = new Intent(AlarmClock.ACTION_SET_ALARM);
                startActivity(jklk);
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent jklk = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(jklk);
            }
        });


        builder.create().show();
    }

}

