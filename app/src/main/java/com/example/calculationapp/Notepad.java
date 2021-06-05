package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calculationapp.adapters.NotesAdapter;
import com.example.calculationapp.data.NoteData;

import java.util.ArrayList;

public class Notepad extends AppCompatActivity {
    SQLiteDatabase db;
    private TextView display, countDisplay;
    ArrayList<NoteData> notesList;
    ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        display = findViewById(R.id.display);
        countDisplay = findViewById(R.id.countDisplay);
        notesListView = findViewById(R.id.notesListView);

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create table if not exists notes(id VARCHAR Primary key, title VARCHAR, date VARCHAR, content VARCHAR);");

        fetchNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchNotes();
    }

    private void fetchNotes() {
        Cursor c = db.rawQuery("Select * from notes", null);
        countDisplay.setText(String.valueOf(c.getCount()));
        notesList = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                String title = c.getString(1);
                String date = c.getString(2);
                String content = c.getString(3);
                notesList.add(new NoteData(title, date, content));
            } while(c.moveToNext());
        }

        NotesAdapter adapter = new NotesAdapter(this, 0, notesList);
        notesListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                deleteAllNotes();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        String sql = "Delete From notes";
        db.execSQL(sql);
        fetchNotes();
    }

    public void openEditNoteActivity(View view) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }
}