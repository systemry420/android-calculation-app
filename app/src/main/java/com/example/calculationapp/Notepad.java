package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calculationapp.adapters.NotesAdapter;
import com.example.calculationapp.data.NoteData;

import java.util.ArrayList;

public class Notepad extends AppCompatActivity {
    SQLiteDatabase db;
    private TextView display, countDisplay;
    EditText searchEditText;
    ArrayList<NoteData> notesList, filteredList;
    ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        display = findViewById(R.id.display);
        countDisplay = findViewById(R.id.countDisplay);
        notesListView = findViewById(R.id.notesListView);
        searchEditText = findViewById(R.id.searchEditText);

        notesList = new ArrayList<>();
        filteredList = new ArrayList<>();

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create table if not exists notes(ID INTEGER Primary key, title VARCHAR, date VARCHAR, content VARCHAR);");

        fetchNotes();

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noteID, noteTitle, noteDate, noteContent;
                if (filteredList.size() > 0) {
                    noteID = filteredList.get(position).ID;
                    noteTitle =  filteredList.get(position).title;
                    noteContent =  filteredList.get(position).content;
                } else {
                    noteID = notesList.get(position).ID;
                    noteTitle =  notesList.get(position).title;
                    noteContent =  notesList.get(position).content;
                }
                Intent intent = new Intent(Notepad.this, EditNote.class);
                intent.putExtra("ID", noteID);
                intent.putExtra("title", noteTitle);
                intent.putExtra("content", noteContent);
                startActivity(intent);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredList.clear();
                for (int i = 0; i < notesList.size(); i++) {
                    if (notesList.get(i).title.contains(s)) {
                        filteredList.add(new NoteData(
                                notesList.get(i).ID,
                                notesList.get(i).title,
                                notesList.get(i).date,
                                notesList.get(i).content
                        ));
                    }
                }
                countDisplay.setText(String.valueOf(filteredList.size()));
                NotesAdapter adapter = new NotesAdapter(Notepad.this, 0, filteredList);
                notesListView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchNotes();
    }

    private void fetchNotes() {
        Cursor c = db.rawQuery("Select * from notes", null);
        countDisplay.setText(String.valueOf(c.getCount()));
        notesList.clear();
        filteredList.clear();
        if(c.moveToFirst()) {
            do {
                String ID = c.getString(0);
                String title = c.getString(1);
                String date = c.getString(2);
                String content = c.getString(3);
                notesList.add(new NoteData(ID, title, date, content));
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
            case R.id.deleteAllNotes:
                deleteAllNotes();
                break;

            case R.id.deleteTable:
                deleteNotesTable();
                break;
        }
        return true;
    }

    private void deleteNotesTable() {
        String sql = "DROP TABLE notes";
        db.execSQL(sql);
        fetchNotes();
    }

    private void deleteAllNotes() {
//        TODO: show dialogs
        String sql = "Delete From notes";
        db.execSQL(sql);
        fetchNotes();
    }

    public void openEditNoteActivity(View view) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }
}