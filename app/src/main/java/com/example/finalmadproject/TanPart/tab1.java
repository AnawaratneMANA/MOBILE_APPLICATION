package com.example.finalmadproject.TanPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalmadproject.Database.DatabaseHelper;
import com.example.finalmadproject.R;
import com.example.finalmadproject.Settings.CommonLayoutActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab1 extends Fragment {
    private DatabaseHelper database;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ListView ListPanel;
    private SQLiteDatabase db;
    String list_name, string_tit,string_fla;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
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
//Create Database Instancesd

        database = new DatabaseHelper(getContext());
        db = database.getReadableDatabase();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        ListPanel = view.findViewById(R.id.ListPanel);
        System.out.println(ListPanel);
        final Cursor cursor = database.readAllFlag();
        System.out.println(cursor);
        //Creating an ArrayList
        arrayList = new ArrayList<>();
        //Loop

            while (cursor.moveToNext()) {
                list_name = cursor.getString(1);
                System.out.println(list_name);

                //getting the value title
                Cursor tit = database.getdescription(db, Integer.parseInt(list_name));
                Cursor fla = database.getdescriptionflag(db, Integer.parseInt(list_name));

                tit.moveToNext();
                fla.moveToNext();
                try {
                    string_tit = tit.getString(tit.getColumnIndex("Task_name"));
                    string_fla =  fla.getString(fla.getColumnIndex("T_ti"));
                } catch (CursorIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                System.out.println(string_tit);
                System.out.println(string_fla);

                if(string_fla.equals("FLAG")){
                    arrayList.add(string_tit);
                }

            }

            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, arrayList);
            System.out.println(adapter);
            ListPanel.setAdapter(adapter);


            ListPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    confirmDialog();
                }
            });
        return view;
    }
    //when click delete button popup Dialog box
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete Flag");
        builder.setMessage("Do you want to delete!");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHandler = new DatabaseHelper(getContext());
                boolean result =  dbHandler.deleteflag(Integer.parseInt(list_name));
                if(result == true){
                    System.out.println("this is in profile:"+list_name);
                    Toast.makeText(getContext(), "Flag Deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), StaticActivity.class);
                    Bundle bundle = new Bundle();
                    startActivity(intent);

                }else{

                    System.out.println(list_name);
                    Toast.makeText(getContext(),"Failed to delete", Toast.LENGTH_SHORT).show();

                }
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });


        builder.create().show();
    }
}