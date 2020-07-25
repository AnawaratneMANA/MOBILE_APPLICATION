package com.example.homeworktrackingsoftware;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addSubject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addSubject extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Button Submit;
    EditText Id, Name;
    private Button viewsubject;

    //Creating instance from the interface
    ControlOpListener controlOpListener;

    public addSubject() {
        // Required empty public constructor
    }

    //Create a interface to communicate between fragments
    public interface ControlOpListener{
        public void ControlPerformed(int method);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addSubject.
     */
    // TODO: Rename and change types and number of parameters
    public static addSubject newInstance(String param1, String param2) {
        addSubject fragment = new addSubject();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_subject, container, false);

        //Initialize the buttons
        Submit = view.findViewById(R.id.btn_sub);
        Id = view.findViewById(R.id.SubjectCode);
        Name = view.findViewById(R.id.SubjectName);
        viewsubject = view.findViewById(R.id.viewSubject);

        //Create a click listener
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the data
                String id  = Id.getText().toString();
                String name = Name.getText().toString();

                //Create a object from the SQLiteOpenHelper Class
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

                //Create a object from the SQLiteDatabase
                //TODO: warning do not perform database operation with the main thread, Use a background thread to perform DB operations (Async task)
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                databaseHelper.addSubject(Integer.parseInt(id),name,database);
                databaseHelper.close();

                //Reset the form after adding the information
                Id.setText("");
                Name.setText("");

                Toast.makeText(getActivity(), "Subject saved Successfully...", Toast.LENGTH_SHORT).show();
            }
        });

        //Create a Listener for View button
        viewsubject.setOnClickListener(this);
        //return the view
        return view;
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.viewSubject) {
            controlOpListener.ControlPerformed(3);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        Activity activity = (Activity) context;
        try{
            controlOpListener = (ControlOpListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + "Interface must be implemented");
        }
    }
}