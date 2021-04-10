package com.example.sqlitestuff;

//THIS IS THE DAO CLASS (DataAccess Object)

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotesTable {
    //these are the columns of the notes table.
    static final String TABLE_NAME="notes";
    static final String COLUMN_ID ="_id";
    static final String COLUMN_SUBJECT="subject";
    static final String COLUMN_TEXT="note";

    //to create the table, not a row!
    static public void onCreate(SQLiteDatabase db){

        //CREATE TABLE notes(_id integer primary key autoincrement, subject text not null, note text not null);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE "+ TABLE_NAME+"(");
        stringBuilder.append(COLUMN_ID+" integer primary key autoincrement, ");
        stringBuilder.append(COLUMN_SUBJECT+" text not null, ");
        stringBuilder.append(COLUMN_TEXT+" text not null);");

        //stringBuilder.toString -> make the string from the buffer
        //db.execSQL(stringBuilder.toString()); //this can cause exception lu

        //so do this.
        try{
            db.execSQL(stringBuilder.toString());
        }catch (SQLException theExceptionWOOT){
            theExceptionWOOT.printStackTrace();
        }
    }


    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //just deleting the table.
        try{
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        }catch (SQLException exception){
            exception.printStackTrace();
        }


    }

}
