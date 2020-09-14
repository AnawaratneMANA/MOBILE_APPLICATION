package com.example.homeworktackingsoftware;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.homeworktackingsoftware.RingingTones.song.SONG_NAME;
import static com.example.homeworktackingsoftware.RingingTones.song.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "salitha_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " (SONG_ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG_NAME BLOB)";
    //"INSERT INTO  " + TABLE3_NAME +  " ( " + COL2_3 +"  )  VALUES (  " + name  + " )" ;
    //public static final ;

    //Creating the query - Table - 1
    public static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public boolean insertData(String audio){
        SQLiteDatabase db = this.getWritableDatabase();

        //String INSERT_TABLE = "INSERT INTO " + TABLE_NAME + " (" +SONG_NAME+") VALUES ( " + audio + ")";
        ContentValues contentValues = new ContentValues();
        contentValues.put(SONG_NAME , audio);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    /**public ArrayList<String> getAllSubjects() {

     //Creating the ArrayList and start the database connection
     ArrayList<String> list = new ArrayList<String>();
     SQLiteDatabase db = this.getReadableDatabase();
     db.beginTransaction();

     //Create the select query to get all the subject names from the database
     try {
     String select = "SELECT * FROM " + TABLE_NAME;
     Cursor cursor = db.rawQuery(select, null);
     if (cursor.getCount() > 0) { //Check Items are available.
     //Loop to fill the ArrayList
     while (cursor.moveToNext()) {
     String subject_name = cursor.getString(cursor.getColumnIndex(SUBJECT_NAME));
     list.add(subject_name);
     }
     db.setTransactionSuccessful();
     }

     } catch (Exception e) {
     e.printStackTrace();
     } finally {
     db.endTransaction();
     db.close();
     }
     return list;
     }**/
}
