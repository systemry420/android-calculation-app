package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditNote extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText noteTitle, noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);

        noteTitle = findViewById(R.id.noteTitleEditText);
        noteContent = findViewById(R.id.noteContentEditText);
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
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault());
        String formattedDate = df.format(now);

        String sql = "INSERT INTO notes(title, date, content)"
                    + " VALUES ('" + noteTitle.getText().toString().trim()
                    +"',' " + formattedDate + "', '"
                    + noteContent.getText().toString() + "');";
        db.execSQL(sql);

        finish();
    }

    private void deleteNote() {
        String sql = "DELETE FROM notes WHERE id=" + 1;
        db.execSQL(sql);
        finish();
    }
}