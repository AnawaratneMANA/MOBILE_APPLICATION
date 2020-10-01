package com.example.finalmadproject;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.finalmadproject.Database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.finalmadproject.TaskManagement.Subject.SubjectEntry.SUBJECT_NAME;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //Database Instances and Context instance declaration.
    DatabaseHelper database;
    Context appContext;
    //Readable and writable databases
    SQLiteDatabase db1;
    SQLiteDatabase db2;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.finalmadproject", appContext.getPackageName());

    }
    @Before
    public void setUp(){
        database = new DatabaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        //Get readable and writable databases
        db1 = database.getReadableDatabase();
        db2 = database.getWritableDatabase();
    }
    @After
    public void finish(){
        //Closed open databases
        db1.close();
        db2.close();
    }
    @Test
    public void testNotNullDB(){
        //Initial testing for Null databases.
        assertNotNull(database);
    }
    @Test
    public void addRetriveData() throws CursorIndexOutOfBoundsException {
        //Create data
        String name = "Testing Subject";
        //Calling the method Add to database
        database.addSubject(name, db2);
        //Getting the data for testing
        String name2 = " '"+name+"' ";
        Cursor obj = database.readSubjectsName(name,db1);

        //Testing
        obj.moveToNext();
        assertEquals(name, obj.getString(obj.getColumnIndex(SUBJECT_NAME)));

    }
}