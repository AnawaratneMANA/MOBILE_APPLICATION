package com.example.finalmadproject.TaskManagement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.finalmadproject.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    //Register variables
    private ImageView back;
    private Animation floweranim, frombot, buttonFX;

    private ImageView flower;
    private TextView welcome;
    private LinearLayout Headinglo, menu;

    //Button Registration
    private ImageView Addtask, Addsub, Showtask;

    //Creating a instance from the interface
    ControlOpListener controlOpListener;


    public HomeFragment() {
        // Required empty public constructor
    }

    public interface ControlOpListener{
        public void ControlPerformed(int method);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        //UI Controlling
            back = (ImageView) view.findViewById(R.id.backg);
            flower = (ImageView) view.findViewById(R.id.flower);
            welcome = (TextView) view.findViewById(R.id.welcome);
            Headinglo = (LinearLayout) view.findViewById(R.id.headinglo);
            menu = (LinearLayout) view.findViewById(R.id.menu);
            floweranim =  AnimationUtils.loadAnimation(getActivity(),R.anim.floweranim);
            frombot = AnimationUtils.loadAnimation(getActivity(),R.anim.frombotop);
            buttonFX = AnimationUtils.loadAnimation(getActivity(),R.anim.button);
            flower.startAnimation(floweranim);
            //Animations
            back.animate().translationY(-400).setDuration(500).setStartDelay(800);
            welcome.animate().alpha(0).setDuration(900).setStartDelay(100);
            //menu.animate().alpha(100).setDuration(800).setStartDelay(1200);

            //LinearLayout Animation
            Headinglo.startAnimation(frombot);
            menu.startAnimation(buttonFX);

            //Controls
        //Initializing the buttons
        Addtask = view.findViewById(R.id.buttontask);
        Addsub = view.findViewById(R.id.buttonsub);
        Showtask = view.findViewById(R.id.buttonshow);

       //Add Onclick listeners to the buttons
        Addsub.setOnClickListener(this);
        Addtask.setOnClickListener(this);
        Showtask.setOnClickListener(this);



        return view;
    }
    @Override
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.buttonsub:
                controlOpListener.ControlPerformed(0);
                break;
            case R.id.buttontask: // Added
                controlOpListener.ControlPerformed(1);
                break;
            case R.id.buttonshow:
                controlOpListener.ControlPerformed(2);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context){
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