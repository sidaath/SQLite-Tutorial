package com.example.sqlitestuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseManager = new DatabaseManager(this); //calls the onCreate in the DatabaseHelper class

//        databaseManager.getNotesDAO().save(new Note("Subject 1 ", "Text 1"));
//        databaseManager.getNotesDAO().save(new Note("Subject 2", "Text 2"));
//        databaseManager.getNotesDAO().save(new Note("Subject 3", "Text 3"));
//        databaseManager.getNotesDAO().save(new Note("Subject 4", "Text 4"));
//        databaseManager.getNotesDAO().deleteById(2);
//        databaseManager.getNotesDAO().deleteById(4);

        Note note = databaseManager.getNotesDAO().getNote(3);

        //important to do the null check, cos if no note comes from database, app will break! If no note comes back, note will be null cos
        //in the DAO note is initialized as null, then assigned values. If value assigning fails, it stays null, and thats what we get here
        if(note != null){
            note.setText("New Subject 3");
            databaseManager.getNotesDAO().update(note);
        }



        Log.d("MainAC", databaseManager.getNotesDAO().getAll().toString());



    }
}