package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditNote extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText noteTitleEditText, noteContentEditText;
    Date now; SimpleDateFormat df; String formattedDate;
    String ID;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        now = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault());
        formattedDate = df.format(now);

        intent = getIntent();
        ID = intent.getStringExtra("ID");
        Log.i("EDITNOTE", "onCreate: " + ID);
        if(ID != null) {
            noteTitleEditText.setText(intent.getStringExtra("title"));
            noteContentEditText.setText(intent.getStringExtra("content"));
        }
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
                saveNote();
                break;
            case R.id.deleteNote:
                deleteNote();
                break;
        }
        return true;
    }

    private void saveNote() {
        String sqlString = "";
        if (ID != null) {
            sqlString  = "UPDATE notes"
                    + " SET title='" + noteTitleEditText.getText().toString().trim()
                    +"', date=' " + formattedDate + "', content='"
                    + noteContentEditText.getText().toString()
                    + "' WHERE ID='" + Integer.parseInt(ID) +"';";
        }
        else {
            sqlString = "INSERT INTO notes(title, date, content)"
                    + " VALUES ('" + noteTitleEditText.getText().toString().trim()
                    +"',' " + formattedDate + "', '"
                    + noteContentEditText.getText().toString() + "');";
        }

        db.execSQL(sqlString);
        finish();
    }

    private void deleteNote() {
        String sql = "DELETE FROM notes WHERE id=" + ID;
        db.execSQL(sql);
        finish();
    }
}