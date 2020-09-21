package com.example.homeworktrackingsoftware;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
            + TASK_DATE+ " text);";

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

    //Creating a Method to get the ID when a String name is Given.
    public Cursor getFromItemID(String name){
        //Getting writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a SQL query
        String SQL = "SELECT * " + " FROM " + TABLE2_NAME +
                " WHERE " + TASK_NAME + " = " + " '"+name +"' "; //if Not working then format the String.
        //this was not work because I was only asking for ID, Try to fetch all

        //Declare the cursor object
        Cursor data = db.rawQuery(SQL, null);
        return data;
    }

    //Create a Update method
    public boolean UpdateDetails(String old_name, String name , String Description, String subject, String date){
        //Getting Writable database.
        SQLiteDatabase db = getReadableDatabase();
//
//        //Embed new values to ContentValue
//        ContentValues values = new ContentValues();
//        values.put(TASK_NAME, name);
//        values.put(TASK_SUBJECT, subject);
//        values.put(TASK_DESCRIPTION, Description);
//        values.put(TASK_DATE, date);
//
//        //Select the row, should be updated.
//        String selection = TASK_ID

        //String formatting.
        old_name = " '"+old_name+"' ";
        name = " '"+name+"' ";
        subject = " '"+subject+"' ";
        date = " '"+date+"' ";
        Description = " '"+Description+"' ";


        //Creating the Query
        String SQL = "UPDATE " + TABLE2_NAME +
                " SET " +  TASK_NAME + " = " + name +" , " +
                TASK_DESCRIPTION + " = " + Description +" , " +
                TASK_SUBJECT + " = " + subject +" , " +
                TASK_DATE + " = " + date + " WHERE " + TASK_NAME + " = " + old_name;
        try{
            db.execSQL(SQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    //Create a Method to update the Subject Details.

    //Create a Method to delete the data
    public boolean deleteData(String name){
        //Getting Writable database.
        SQLiteDatabase db = getReadableDatabase();
        //String formatting
        name = " '"+name+"' ";
        //SQL
//        String SQL = "DELETE FROM " + TABLE2_NAME + " WHERE " + TASK_NAME + " = " + name;
//
//        try {
//            db.execSQL(SQL);
//            return true;
//        } catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }
        //Executing the delete method.
        return db.delete(TABLE2_NAME, TASK_NAME + "=" + name, null) > 0;
    }

    //Subject related update method and Delete method
    public boolean UpdateSubject(String new_name, String old_name){
        //Get a readable database
        SQLiteDatabase db = getReadableDatabase();
        //String formatting
        new_name = " '"+new_name+"' ";
        old_name = " '"+old_name+"' ";
        //SQL
        String SQL = "UPDATE " + TABLE_NAME +
                " SET " + SUBJECT_NAME + " = " + new_name +
                " WHERE " + SUBJECT_NAME + " = " + old_name;

        //Excute the statement
        try{
            db.execSQL(SQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Create a Method to delete the subject
    public boolean DeleteSubject(String name){
        //Getting Writable database.
        SQLiteDatabase db = getReadableDatabase();
        //String formatting
        name = " '"+name+"' ";
        //Create a Delete Statement and Execute.
        return db.delete(TABLE_NAME, SUBJECT_NAME + "=" + name, null) > 0;

    }

    //Validation method Insert tasks - Already exist
}
