package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.calculationapp.data.NoteContract;

public class EditNote extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText noteTitle, noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);

        noteTitle = findViewById(R.id.noteTitle);
        noteContent = findViewById(R.id.noteContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editnote_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.saveNote:
                insertNote();
                break;
            case R.id.deleteNote:
                deleteNote();
                break;
        }
        return true;
    }

    private void insertNote() {
        String sql = "INSERT INTO " + NoteContract.TABLE +
                    "('" + NoteContract.COL_TITLE + "' , '" + NoteContract.COL_DATE + "' , '"
                    + NoteContract.COL_CONTENT + "') " +
                    "VALUES ('" + noteTitle.getText().toString().trim() +"','aaa', '"
                    + noteContent.getText().toString() + "');";
        db.execSQL(sql);

        finish();
    }

    private void deleteNote() {

    }
}