package com.example.sqlitestuff;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

//The queries go here -> DAO is the one that lets you carry out queries
public class NotesDAO {
    private SQLiteDatabase db;

    public NotesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    //return long cos we say we wanna get the id which is autoincremented
    public long save(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_SUBJECT, note.getSubject());
        values.put(NotesTable.COLUMN_TEXT, note.getText());
        //ContentValues is a key-value thing. Key is the column name, value is the value you wanna insert

        return db.insert(NotesTable.TABLE_NAME,null, values); //THIS IS THE INSERT QUERY!
        //db.insert returns the row ID of newly inserted row, or -1 if there was an error - according to documentation
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_SUBJECT, note.getSubject());
        values.put(NotesTable.COLUMN_TEXT,note.getText());

        //update -> has a where clause.
        return db.update(NotesTable.TABLE_NAME, values, NotesTable.COLUMN_ID+"=?",new String[]{String.valueOf(note.get_id())}) >0;
        //the '?' is to say that youre giving a column_id value in the next argument
        //next argument is an array of strings
        //NotesTable.COLUMN_ID+"=? AND "+<another column>+"= ?", new String[]{ }> this is how to do if more than one where clause

        //return works cos db.update returns the number of rows affected ie greater than 0 if at least one row was modified.
    }

    public boolean delete(Note note){
        return db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_ID+"=?",new String[]{String.valueOf(note.get_id())})>0;
        //cos same story as update

    }

    public boolean deleteById(long id){
        return db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_ID+"=?",new String[]{String.valueOf(id)})>0;
        //cos same story as update

    }


    public Note getNote(long id){
        Note note = null;

        //cursor -> pointer positioned before first entry.
        Cursor cursor = db.query(NotesTable.TABLE_NAME, //giving table name
                new String[]{NotesTable.COLUMN_ID, NotesTable.COLUMN_SUBJECT, NotesTable.COLUMN_TEXT}, //which columns you want
                NotesTable.COLUMN_ID+"=?", new String[]{String.valueOf(id)}, //the WHERE part of sql query
                null,null,null); //some stuff


        //moveToFirst returns boolean -> if the row exists, true otherwise it says false. If it exists, after moving to first it'll have values.
        //how to get the note from cursor is in the buildNoteFromCursor method
        if(cursor.moveToFirst()){
            note = buildNoteFromCursor(cursor);
        }

        return note;
    }

    public ArrayList<Note> getAll(){
        ArrayList<Note> notes = new ArrayList<>();

        Cursor cursor = db.query(NotesTable.TABLE_NAME, //giving table name
                new String[]{NotesTable.COLUMN_ID, NotesTable.COLUMN_SUBJECT, NotesTable.COLUMN_TEXT}, //which columns you want
                null, null, //the WHERE part of sql query
                null,null,null); //some stuff

        //move to next will also return a boolean
        while (cursor.moveToNext()){
            Note note = buildNoteFromCursor(cursor);
            notes.add(note);
        }

        return notes;
    }

    private Note buildNoteFromCursor(Cursor cursor){
        Note note = new Note();
        note.set_id(cursor.getLong(0));
        note.setSubject(cursor.getString(1));
        note.setText(cursor.getString(2));
        //get(TYPE) -> has to match what kind of data is in the database, the argument is the column number (_id is column 0, subj is col 1)

        return note;
    }

}
