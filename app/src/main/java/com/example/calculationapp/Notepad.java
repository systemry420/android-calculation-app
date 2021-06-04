package com.example.calculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculationapp.data.NoteContract;

public class Notepad extends AppCompatActivity {
    SQLiteDatabase db;
    private TextView display, countDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        display = findViewById(R.id.display);
        countDisplay = findViewById(R.id.countDisplay);

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create table if not exists notes(id VARCHAR, title VARCHAR, date VARCHAR, content VARCHAR);");

        fetchNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchNotes();
    }

    private void fetchNotes() {
        Cursor c = db.rawQuery("Select * from " + NoteContract.TABLE, null);
        if(c.moveToFirst()) {
            String select = "";
            while(c.moveToNext()) {
                select += c.getString(1) + "\n";
            }

            display.setText(select.toString());
            countDisplay.setText(String.valueOf(c.getCount()));
        }
    }


    public void openEditNoteActivity(View view) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }
}