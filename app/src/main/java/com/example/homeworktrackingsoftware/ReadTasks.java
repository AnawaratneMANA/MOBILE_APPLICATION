package com.example.homeworktrackingsoftware;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_DATE;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_DESCRIPTION;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_ID;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_NAME;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_SUBJECT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadTasks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadTasks extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReadTasks() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReasTasks.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadTasks newInstance(String param1, String param2) {
        ReadTasks fragment = new ReadTasks();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_read_tasks, container, false);
        //showing_task = view.findViewById(R.id.task_display_fragment);
        listView = (ListView) view.findViewById(R.id.listView);
        readContacts();
        return view;
    }

    public void readContacts()
    {
        final DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        //Create this process in a separate thread.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = databaseHelper.readTasks(database);

        //Loop to iterate the entire cursor object
        String string = "";
        ArrayList<String> listName = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList();
        while(cursor.moveToNext())
        {
            //String id = Integer.toString(cursor.getColumnIndex(TASK_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));
            //String description = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION));
            //subject = cursor.getString(cursor.getColumnIndex(TASK_SUBJECT));
            //String date = cursor.getString(cursor.getColumnIndex(TASK_DATE));
            //Add files to the ArrayList
            listName.add(name);
            //listDescription.add(description);

            //concatenate the text into a variable
            /*string = string + "\n\n" + "TASK ID - " +id+  "\nTASK NAME - " +name+
                    "\nTASK DESCRIPTION - " + description+ "\nTASK SUBJECT - " +subject+ "\nTASK DATE - " +date;*/
        }

        //Creating a ListAdapter and pass the arrayList
        ListAdapter adapter = new ArrayAdapter<>(requireActivity(),android.R.layout.simple_list_item_1,listName);
        listView.setAdapter(adapter); //Creating Update function on DialogFragment.

        //Creating a OnClick Listener to the List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: Item Clicked " + name);

                //Get the ID of the data that we click on
                Cursor data = databaseHelper.getFromItemID(name);
                int pointer = -1;
                //Declare All String variables
                String old_name = "";
                String ID = "";
                String description = "";
                String subject = "";
                String date = "";

                //Testing
                int iD = 9;

                while(data.moveToNext()){
                    //pointer = data.getInt(data.getColumnIndex(TASK_ID));
                    //Get Existing data from the Cursor Object

                    old_name = data.getString(data.getColumnIndex(TASK_NAME));
                    ID = data.getString(data.getColumnIndex(TASK_ID));
                    description = data.getString(data.getColumnIndex(TASK_DESCRIPTION));
                    subject = data.getString(data.getColumnIndex(TASK_SUBJECT));
                    date = data.getString(data.getColumnIndex(TASK_DATE));

                }

                if(iD > -1){
                    Log.d(TAG, "onItemClick: ID is " + pointer);
                    //When navigating from Fragment to Activity use this instead of the Typical Notation.
                    Intent updateTask = new Intent(getActivity(), UpdateTask.class);
                    updateTask.putExtra("id", ID);
                    updateTask.putExtra("name", old_name);
                    updateTask.putExtra("description", description);
                    updateTask.putExtra("subject", subject);
                    updateTask.putExtra("date", date);
                    startActivity(updateTask);
                } else {
                    System.out.println("Such date is not in the database.");
                }
            }
        });

    }
}