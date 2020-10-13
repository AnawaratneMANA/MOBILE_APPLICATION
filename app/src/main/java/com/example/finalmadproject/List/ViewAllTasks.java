package com.example.finalmadproject.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.TaskManagement.MainActivity;
import com.example.finalmadproject.TaskManagement.ReadTaksSelectable;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalmadproject.TaskManagement.Task.TaskEntry.TASK_NAME;

public class ViewAllTasks extends AppCompatActivity {

    //DatabaseHelper dbHandler;
    Button addView;
    TextView taskSelected;
    DatabaseHelper mydb;
    private String message;
    String[] list2; //Testing
    boolean[] checkedItems;
    private ListView layout;
    ArrayList<Integer> userItems = new ArrayList<>();
    int count ;
    private Intent intent;
    private String[] list3;
    //int count5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_tasks);
        mydb = new DatabaseHelper(this);

        //passing ids
        addView = findViewById(R.id.btnaddTask);
        layout = findViewById(R.id.listView);
        ListPopulate();
        setSelectedTasks();
        checkedItems = new boolean[count];

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ViewAllTasks.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(list2, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            userItems.add(position);
                            //pass intent
                            intent = getIntent();
                            //take the relevent string value passed by the customerAdapter
                            message = intent.getStringExtra(CustomAdapter.EXTRA_ID);
                            //take relevent string value of the relevent position from list2 string array declared above
                            String tasks = list2[position];
                            //here we check the relevent task id of relevent string value initialized above
                            String task_2 = mydb.getTaskID(tasks);
                            //insert method
                            boolean result = mydb.insertListTaskData(message , task_2);
                        }else{
                            userItems.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ListPopulate();
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            userItems.clear();
                            taskSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    //Method to populate the List.
    public void setSelectedTasks(){
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();
        //Cursor object1 = db.displayListed("1");
        Cursor object = db.readTasks(database);
        int count2 = 0;
        //Testing
        while (object.moveToNext()){

            count2++;
        }
        list2 = new String[count2];
        count = 0;
        for(object.moveToFirst(); !object.isAfterLast(); object.moveToNext()){
            list2[count] = object.getString(object.getColumnIndex(TASK_NAME));
            //System.out.println(list2[count]);
            count++;
        }


    }

    public void ListPopulate(){
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();
        intent = getIntent();
        message = intent.getStringExtra(CustomAdapter.EXTRA_ID);
        Cursor object = db.displayListed(message);

        int count1 = 0;
        //Testing loop
        while(object.moveToNext()){
            count1++;
        }
        list3 = new String[count1];
        try {
            int count2 = 0;
            for(object.moveToFirst(); !object.isAfterLast(); object.moveToNext()){
                list3[count2] = object.getString(object.getColumnIndex(TASK_NAME));
                //System.out.println(list2[count]);
                count2++;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }


        ListAdapter adapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,list3);
        layout.setAdapter(adapter);

    }


   //Comment - Remove the statement given. in the dialog box ok button and then add them to the database.
}