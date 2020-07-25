package com.example.homeworktrackingsoftware;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addTask extends Fragment implements DatePickerDialog.OnDateSetListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //My Variables
    private EditText id,name,description;
    private Spinner subject;
    private TextView date;

    public addTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addTask.
     */
    // TODO: Rename and change types and number of parameters
    public static addTask newInstance(String param1, String param2) {
        addTask fragment = new addTask();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_task, container, false);

        //Filling the spinner
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity()); //Check this.
        ArrayList<String> subject_list = databaseHelper.getAllSubjects();
        final Spinner subjectSpinner = (Spinner) view.findViewById(R.id.SubjectSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_layout,R.id.txt,subject_list);
        subjectSpinner.setAdapter(adapter);

        //Getting Date and initialize the TextView
        date = view.findViewById(R.id.datetextview);

        //Create a OnClick Listener for the button - All Date related Methods
        view.findViewById(R.id.OpenDate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                showDatePickerDialog();
            }
        });

        //Implementing the method to insert task to the database
        id = view.findViewById(R.id.TaskId);
        name =view.findViewById(R.id.TaskName);
        description = view.findViewById(R.id.DescriptionId);
        //subject = view.findViewById(R.id.SubjectSpinner);

        //Create a click listener for the task submit
        view.findViewById(R.id.taskSubmit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){

                //Getting String values from the interface
                String Id = id.getText().toString();
                String Name = name.getText().toString();
                String Description = description.getText().toString();
                String Date = date.getText().toString();
                String Spinner_Sub = subjectSpinner.getSelectedItem().toString();

                //Create a object from the SQLiteOpenHelper Class
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

                //Getting writable database from the DBHelper class
                SQLiteDatabase database = databaseHelper.getWritableDatabase();

                //Calling the Method in the DBClass
                databaseHelper.addTasks(Integer.parseInt(Id),Name,Description,Spinner_Sub,Date,database);

                //Close the DB Connection
                databaseHelper.close();

                //Reset the Input fields
                id.setText("");
                name.setText("");
                description.setText("");
                date.setText("");
                //subjectSpinner.setSelected(null); How to reset the Spinner?

                //Create Massage for the user
                Toast.makeText(getActivity(), "Task saved Successfully...", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        String string_date = year+ "-" + month + "-" + dayOfMonth;
        date.setText(string_date);
    }
}