package com.example.homeworktackingsoftware;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Environment;
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
    String[] item;
    DatabaseHelper myDB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_audio_list,container,false);
        myListViewforSongs = v.findViewById(R.id.mySongListView);
        myDB = new DatabaseHelper(getActivity());
        runtimePermission();
        return v;
    }
    public void runtimePermission()
    {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
    public ArrayList<File> findSong(File file){
        ArrayList<File> arrayList = new ArrayList<>() ;
        File[] files = file.listFiles();
        for (File singleFile: files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }else{
                if(singleFile.getName().endsWith(".mp3") ||
                        singleFile.getName().endsWith(".wav")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
    void display(){
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());
        item = new String[mySongs.size()];
        for (int i = 0; i < mySongs.size(); i++){
            item[i] = mySongs.get(i).getName().toString().replace(".mp3","").replace(".mp3","");

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,item);
        myListViewforSongs.setAdapter(myAdapter);
        myListViewforSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //try {
                String songName = myListViewforSongs.getItemAtPosition(i).toString();
                boolean inserted = myDB.insertData(songName);
                if (inserted == true) {
                    Toast.makeText(getActivity(), "data inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "data declined", Toast.LENGTH_LONG).show();
                }
            /**}
            else(Exception ){

            }**/
        }
        });
    }




}