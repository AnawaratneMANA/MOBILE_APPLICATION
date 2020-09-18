package com.example.homeworktackingsoftware;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;


public class AudioListFragment extends DialogFragment {
    ListView myListViewforSongs;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    private static final int MY_PERMISSION_REQUEST = 1;
    DatabaseHelper myDB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_audio_list,container,false);
        myListViewforSongs = v.findViewById(R.id.mySongListView);
        myDB = new DatabaseHelper(getActivity());
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(getActivity(),
                        new String []{Manifest.permission.READ_EXTERNAL_STORAGE} , MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }else{
            dostuff();
        }
        return v;
    }
    public void dostuff() {
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri,null,null,null,null);
        if(songCursor != null && songCursor.moveToFirst()){
            int songtitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            int songLocation = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do {
                String currentTitle = songCursor.getString(songtitle);
                String currentLocation = songCursor.getString(songLocation);
                //arrayList2.add("Title : " + currentTitle);
                arrayList.add("Location : " + currentLocation);
                adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1 , arrayList);
                myListViewforSongs.setAdapter(adapter);
                myListViewforSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String title = myListViewforSongs.getItemAtPosition(i).toString();
                        String location = myListViewforSongs.getItemAtPosition(i).toString();
                        boolean inserted = myDB.insertData(title , location);
                        if (inserted == true) {
                            Toast.makeText(getActivity() , "data added" , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "data declined", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }while (songCursor.moveToNext());
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getActivity(), "Permission Granted!", Toast.LENGTH_SHORT).show();
                        dostuff();
                    }else{
                        Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                    return;
                }
            }
        }
    }






}