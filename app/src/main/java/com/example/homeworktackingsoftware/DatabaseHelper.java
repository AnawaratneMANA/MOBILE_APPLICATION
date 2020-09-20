package com.example.homeworktackingsoftware;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.homeworktackingsoftware.RingingTones.song.SONG_ID;
import static com.example.homeworktackingsoftware.RingingTones.song.SONG_NAME;
import static com.example.homeworktackingsoftware.RingingTones.song.SONG_PATH;
import static com.example.homeworktackingsoftware.RingingTones.song.TABLE_SONG_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "salitha_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_SONG_TABLE = "create table " + TABLE_SONG_NAME + " (SONG_ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG_NAME BLOB , SONG_PATH String)";
    //"INSERT INTO  " + TABLE3_NAME +  " ( " + COL2_3 +"  )  VALUES (  " + name  + " )" ;
    //public static final ;

    //Creating the query - Table - 1
    public static final String DROP_TABLE = "drop table if exists "+ TABLE_SONG_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SONG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public boolean insertData(String audio , String path){
        SQLiteDatabase db = this.getWritableDatabase();

        //String INSERT_TABLE = "INSERT INTO " + TABLE_NAME + " (" +SONG_NAME+") VALUES ( " + audio + ")";
        ContentValues contentValues = new ContentValues();
        contentValues.put(SONG_NAME , audio);
        contentValues.put(SONG_PATH , path);
        Long result = db.insert(TABLE_SONG_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public ArrayList<SongList> getAllAudios() {

        //Creating the ArrayList and start the database connection
        ArrayList<SongList> list = new ArrayList<SongList>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SONG_NAME, null);

        while (cursor.moveToNext()) {
            //added
            String song_id = cursor.getString(0);
            String song_name = cursor.getString(1);
            String song_path = cursor.getString(2);
            //added
            SongList songList = new SongList(song_id , song_name , song_path);
            list.add(songList);
        }

        return list;
    }
    public int deleteAudios(String id){
        SQLiteDatabase db = getReadableDatabase();
        id = " '"+id+"' ";
        return db.delete(TABLE_SONG_NAME, SONG_ID + "=" + id, null) ;
    }
}
