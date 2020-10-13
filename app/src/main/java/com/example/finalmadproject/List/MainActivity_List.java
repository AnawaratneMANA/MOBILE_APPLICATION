package com.example.finalmadproject.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;
import com.example.finalmadproject.Settings.Sounds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.os.Build.ID;

public class MainActivity_List extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button , home_button;
    Button viewButton;

    DatabaseHelper dbHandler;
    //private ImageView back;
    ArrayList<String> id, title, description;
    CustomAdapter customAdapter;

    //public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        home_button = findViewById(R.id.add_buttonHome);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_List.this, CommonLayoutActivity.class);
                startActivity(intent);
            }
        });
        //back = (ImageView)findViewById(R.id.backg);
        //back.animate().translationY(-400).setDuration(500).setStartDelay(800);
        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_List.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbHandler = new DatabaseHelper(MainActivity_List.this);
        id = new ArrayList<>();
        System.out.println(id);
        String ID = id.toString();
        System.out.println(ID);
        title = new ArrayList<>();
        description = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity_List.this,this,id,title,description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_List.this));

        viewButton = findViewById(R.id.btnView);

        //nevigated all the tsk details page
       /* viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity_List.this, ViewAllTasks.class);
                    startActivity(intent);
            }
        });*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = dbHandler.readAllData(); //Method
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                description.add(cursor.getString(2));
            }
        }
    }

    //Search The item in recycle view
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create getMenuInflater and provide resourse main menu and pass the menu parameter
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //get menu item
        MenuItem item = menu.findItem(R.id.action_search);
        //get search view
        SearchView searchView = (SearchView) item.getActionView();
        //run search view here
        //here create search view after implement this method
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //call the Customer Adapter filter method
                customAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }







   /* public void viewButtonClick(View view){
        Intent intent = new Intent(MainActivity_List.this, ViewAllTasks.class);
        //intent.putExtra(EXTRA_ID, ID);
        //startActivity(intent);

    }*/
}