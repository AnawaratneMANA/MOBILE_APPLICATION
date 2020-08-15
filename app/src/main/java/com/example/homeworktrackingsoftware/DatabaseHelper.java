package com.example.homeworktrackingsoftware;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Table - 1 - Subject Table Information.
import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.homeworktrackingsoftware.Subject.SubjectEntry.SUBJECT_ID;
import static com.example.homeworktrackingsoftware.Subject.SubjectEntry.SUBJECT_NAME;
import static com.example.homeworktrackingsoftware.Subject.SubjectEntry.TABLE_NAME;

//Table - 2 - Task Table Information.
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TABLE2_NAME;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_DATE;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_DESCRIPTION;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_ID;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_NAME;
import static com.example.homeworktrackingsoftware.Task.TaskEntry.TASK_SUBJECT;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Variables
    public static final String DATABASE_NAME = "homework_db";
    public static final int DATABASE_VERSION = 1;

    //TODO: SQL Queries
    //Creating the query - Table - 1
    public static final String CREATE_TABLE = "create table "+ TABLE_NAME + "(" +
            SUBJECT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + SUBJECT_NAME+ " text);"; //Made a change to the key attribute

    //Creating the query - Table - 1
    public static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;

    //Creating the query - Table - 2
    public static final String CREATE_TABLE2 = "create table "+ TABLE2_NAME + "(" +
            TASK_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK_NAME+ " text," + TASK_DESCRIPTION+ " text," + TASK_SUBJECT+ " text,"
            + TASK_DATE+ " text);"; //Partially Updated Query 2 and Added Task Class. Working on the date.

    //Creating the query - Table - 2
    public static final String DROP_TABLE2 = "drop table if exists "+ TABLE2_NAME;

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
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE2); //Testing
        Log.d("Database Operation", "Table is created");
    }

    //Update tables
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(DROP_TABLE2);
        onCreate(sqLiteDatabase);
    }

    //Create a method to put information to the table - Attributes are the columns.
    public void addSubject(String name, SQLiteDatabase database)
    {
        //First we have to create a object from ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_NAME, name);

        //Creating the database inserting method.
        database.insert(TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "One Subject inserted");
    }

    //Add Task Method to put task to the database.
    public void addTasks(String name, String description, String subject, String date, SQLiteDatabase database){

        //Creating a Object from the ContentValues
        ContentValues contentValues = new ContentValues();
        //contentValues.put(TASK_ID,id);
        contentValues.put(TASK_NAME,name);
        contentValues.put(TASK_DESCRIPTION,description);
        contentValues.put(TASK_SUBJECT,subject);
        contentValues.put(TASK_DATE,date);

        //Creating the database inserting method.
        database.insert(TABLE2_NAME, null, contentValues);
        Log.d("Database Operations", "One Task inserted");
    }
    //Adding Another method to populate the spinner
    public ArrayList<String> getAllSubjects() {

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
    }

    //Date creating function
    public String createDate(String Date)  {
        if(Date == null){
            return null;
        }
        //ParsePosition parsePosition = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date Corrected_date = (java.sql.Date) simpleDateFormat.parse(Date);
            simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String newdate = simpleDateFormat.format(Corrected_date);
            System.out.println("Corrected date - " + newdate);
            return newdate;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //Create a query to display task details from the database
    public Cursor readTasks(SQLiteDatabase database)
    {
        //String Array with all the attribute names
        String [] tasks = {TASK_ID,TASK_NAME,TASK_DESCRIPTION,TASK_SUBJECT,TASK_DATE,};

        //Creating a Cursor Object
        Cursor cursor = database.query(TABLE2_NAME,tasks,null,null,null,null,null);
        return cursor;
    }

    //Crate a query to display subject details from the database
    public Cursor readSubjects(SQLiteDatabase database){

        //String Array
        String [] subject = {SUBJECT_ID,SUBJECT_NAME};

        //Creating a Cursor object
        Cursor cursor = database.query(TABLE_NAME,subject,null,null,null,null,null);
        return cursor;
    }
}
