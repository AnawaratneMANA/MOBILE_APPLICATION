package com.example.homeworktrackingsoftware;

public final class Subject {
    //Constructor
    private Subject(){

    }

    //Define the database schema
    public static class SubjectEntry
    {
        public static final String TABLE_NAME = "Subject_info";
        public static final String SUBJECT_ID = "Subject_id";
        public static final String SUBJECT_NAME = "Subject_name";
    }
}
