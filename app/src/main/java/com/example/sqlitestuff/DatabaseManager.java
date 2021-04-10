package com.example.sqlitestuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    Context mContext;
    SQLiteDatabase database;
    SQLiteOpenHelper dbOpenHelper;
    NotesDAO notesDAO;

    public DatabaseManager(Context context){
        this.mContext=context;
        dbOpenHelper = new DatabaseHelper(mContext); //this is the helper that WE CREATED! -> DatabaseHelper class.
        database = dbOpenHelper.getWritableDatabase();
        notesDAO = new NotesDAO(database);

        //if database exists -> gets a reference to it
        //if doesn't exist -> onCreate is called - the one in the helper.
        //if version is old or not aligned-> onUpgrade is called
    }

    public NotesDAO getNotesDAO() {
        return notesDAO;
    }
}
