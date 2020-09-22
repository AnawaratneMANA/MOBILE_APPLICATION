package com.mad_2020_g08_10_2.tasktrackerv01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




//Table - 1 - Subject Table Information.
//Table - 2 - Task Table Information.

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_Login = "create table user (UID INTEGER PRIMARY KEY AUTOINCREMENT, FN text, UN text, PW text)";

    public static final String DROP_TABLE_Login = "drop table if exists user";
    //Variables
    public static final String DATABASE_NAME = "homework_db";
    public static final int DATABASE_VERSION = 1;


    //Create the constructor
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Creating a Massage
        Log.e("Database Operations", "Database Created");
    }

    //Create new tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_Login);
        Log.d("Database Operation", "Table is created");
    }

    //Update tables
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_Login);
        onCreate(sqLiteDatabase);
    }

    //Create a method to put information to the table - Attributes are the columns.
    public void addSubject(String name, SQLiteDatabase database)
    {

        Log.d("Database Operations", "One Subject inserted");
    }

    //inserting in database
    public boolean insert(String FN, String UN, String PW){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FN",FN );
        contentValues.put("UN",UN );
        contentValues.put("PW",PW );
        long ins = db.insert("user", null,contentValues);

        if(ins == -1) return false;
        else return true;
    }
    //checking if UN exists
    public Boolean chkUN(String UN){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where UN = ?",new String[]{UN});
        if(cursor.getCount()>0) return false;
        else return true;
    }

}
