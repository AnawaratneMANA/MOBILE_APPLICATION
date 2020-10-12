package com.example.finalmadproject.TaskManagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;

import static android.app.DownloadManager.COLUMN_TITLE;
import static com.example.finalmadproject.TaskManagement.Subject.SubjectEntry.SUBJECT_NAME;


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
    EditText Name;
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
        Submit = view.findViewById(R.id.btnUpdateSub);
        Name = view.findViewById(R.id.SubjectName);
        viewsubject = view.findViewById(R.id.btnDeleteSub);

        //Create a click listener
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the data
                String name = Name.getText().toString();

                //Empty text field validation
                if(name.contentEquals("")){
                    Name.setError("Enter the Subject name");
                } else {
                    //Database operation should come in this section.
                    //Duplication avoiding method.
                    //Create a object from the SQLiteOpenHelper Class
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                    SQLiteDatabase database = databaseHelper.getWritableDatabase();
                    Cursor subjectsSet = databaseHelper.readSubjects(database);

                    String name_from_DB= "";
                    int flag = 0;
                    try{
                        while(subjectsSet.moveToNext()){
                            name_from_DB = subjectsSet.getString(subjectsSet.getColumnIndex(SUBJECT_NAME));

                            System.out.println(name_from_DB);
                            System.out.println(subjectsSet.getColumnIndex(SUBJECT_NAME));

                            if(name_from_DB.contentEquals(name)){
                                flag++;
                                break;
                            }
                        }
                    } catch(CursorIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }


                    //If condition to check the name
                    if(flag == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Subject is already in the database.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Create a Instruction Message
                                        Toast.makeText(getActivity(), "Enter the values again", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        //Calling the database if there's no error
                        databaseHelper.addSubject(name,database);
                        Toast.makeText(getActivity(), "Subject saved Successfully...", Toast.LENGTH_SHORT).show();
                        databaseHelper.close();
                    }

                }
                //Reset the form after adding the information
                Name.setText("");


            }
        });

        //Create a Listener for View button
        viewsubject.setOnClickListener(this);
        //return the view
        return view;
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btnDeleteSub) {
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