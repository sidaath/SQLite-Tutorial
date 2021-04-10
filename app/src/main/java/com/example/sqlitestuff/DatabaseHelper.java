package com.example.sqlitestuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME="notes.db";
    final static int DATABASE_VERSION=7;

    public DatabaseHelper(Context mContext){
        super(mContext, DATABASE_NAME, null,DATABASE_VERSION);
    }

    //THIS IS CALLED WHEN THE DATABASE DOES NOT EXIST -> LIKE IN FIRST RUN
    //create the database inside this function -> use the DAO.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MainAC", "onCreate");
        NotesTable.onCreate(db);

    }

    //THIS IS CALLED IF THE VERSION IS NEWER -> CREATES THE NEWER VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MainAC", "onUpgrade");
        NotesTable.onUpgrade(db, oldVersion, newVersion);
        NotesTable.onCreate(db);
    }
}
