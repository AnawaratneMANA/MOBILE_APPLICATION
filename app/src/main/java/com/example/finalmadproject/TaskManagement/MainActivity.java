package com.example.finalmadproject.TaskManagement;

import android.os.Bundle;
import com.example.finalmadproject.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements HomeFragment.ControlOpListener, addSubject.ControlOpListener {

    //Creating variables
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting a object from the fragment manager
        fragmentManager = getSupportFragmentManager();

        //Fragment validation
        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            //Adding the fragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //Creating a Object from the MainControlPanel fragment
            HomeFragment controlPanel = new HomeFragment();
            fragmentTransaction.add(R.id.fragment_container, controlPanel, null );
            fragmentTransaction.commit();
        }
    }

    @Override
    public void ControlPerformed(int method) {

        switch (method)
        {
            case 0:
                //Testing - System.out.println(method);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addSubject()).addToBackStack(null).commit();
                break;
            case 1:
                //Added
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addTask()).addToBackStack(null).commit();
                break;
            case 2:
                //Navigate to the Showing page.
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadTasks()).addToBackStack(null).commit();
                break;
            case 3:
                //Navigate to the Subject Showing page
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadSubjects()).addToBackStack(null).commit();
                break;
        }
    }
}