package com.example.homeworktrackingsoftware;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainControlPanel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainControlPanel extends Fragment implements View.OnClickListener {

    // TODO: Creating Variable to store the buttons
    private  Button Addtask, Addsub, Showtask;

    //Creating a instance from the interface
    ControlOpListener controlOpListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainControlPanel() {
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
     * @return A new instance of fragment MainControlPanel.
     */
    // TODO: Rename and change types and number of parameters
    public static MainControlPanel newInstance(String param1, String param2) {
        MainControlPanel fragment = new MainControlPanel();
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
        View view =  inflater.inflate(R.layout.fragment_main_control_panel, container, false);

        //Initializing the buttons
       Addtask = view.findViewById(R.id.Add_Task_Button);
       Addsub = view.findViewById(R.id.Add_Sub_Button);
       Showtask = view.findViewById(R.id.Show_Button);

       //Add Onclick listeners to the buttons
        Addsub.setOnClickListener(this);
        Addtask.setOnClickListener(this);
        Showtask.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Add_Sub_Button:
                controlOpListener.ControlPerformed(0);
                break;
            case R.id.Add_Task_Button: // Added
                controlOpListener.ControlPerformed(1);
                break;
            case R.id.Show_Button:
                controlOpListener.ControlPerformed(2);
                break;
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