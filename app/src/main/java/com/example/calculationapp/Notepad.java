package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.calculationapp.adapters.NotesAdapter;
import com.example.calculationapp.data.NoteData;

import java.util.ArrayList;

public class Notepad extends AppCompatActivity {
    private SQLiteDatabase db;
    private TextView display, countDisplay;
    private EditText searchEditText;
    private ArrayList<NoteData> notesList, filteredList;
    private ListView notesListView;
    private AlertDialog.Builder alert;

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

        alert = new AlertDialog.Builder(this);

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create table if not exists notes(ID INTEGER Primary key, title VARCHAR, date VARCHAR, content VARCHAR);");

        fetchNotes("Select * from notes order by date desc");

        setListViewListener();

        setSearchEditTextListener();
    }

    private void fetchNotes(String query) {
        try {
            Cursor c = db.rawQuery(query, null);
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
        } catch (Exception e) { }

        NotesAdapter adapter = new NotesAdapter(this, 0, notesList);
        notesListView.setAdapter(adapter);
    }

    private void deleteAllNotes() {
        alert.setTitle("Delete")
         .setMessage("Are you sure you want to delete all notes?")
         .setCancelable(false)
         .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sql = "Delete From notes";
                try {
                    db.execSQL(sql);
                } catch (Exception e) {}
                Toast.makeText(Notepad.this, "All notes have been deleted!", Toast.LENGTH_LONG).show();
                fetchNotes("Select * from notes order by date desc");
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void setSearchEditTextListener() {
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

    private void setListViewListener() {
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noteID, noteTitle, noteContent;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchNotes("Select * from notes order by date desc");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortDate:
                fetchNotes("Select * from notes order by date");
                break;

            case R.id.sortTitle:
                fetchNotes("Select * from notes order by title");
                break;
            case R.id.deleteAllNotes:
                deleteAllNotes();
                break;
        }
        return true;
    }

    public void openEditNoteActivity(View view) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }

}