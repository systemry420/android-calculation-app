package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditNote extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText noteTitleEditText, noteContentEditText;
    private String formattedDate;
    private String ID;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        db = openOrCreateDatabase("notesDB", Context.MODE_PRIVATE, null);
        alert = new AlertDialog.Builder(this);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        // Log.i("EDITNOTE", "onCreate: " + ID);
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
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM, dd, yyyy HH:mm", Locale.getDefault());
        formattedDate = df.format(now);

        if (!noteTitleEditText.getText().toString().trim().equals("") ||
            !noteContentEditText.getText().toString().trim().equals("")) {
            if (ID != null) {
                sqlString  = "UPDATE notes SET title='" + noteTitleEditText.getText().toString().trim() + "', "
                        + "date='" + formattedDate + "', "
                        + "content='"+ noteContentEditText.getText().toString() + "' "
                        + "WHERE ID='" + Integer.parseInt(ID) +"';";
            }
            else {
                sqlString = "INSERT INTO notes(title, date, content)"
                        + " VALUES ('" + noteTitleEditText.getText().toString().trim()
                        +"', '" + formattedDate + "', '"
                        + noteContentEditText.getText().toString() + "');";
            }
            db.execSQL(sqlString);
            Toast.makeText(this, "Note was saved successfully!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You can't save empty notes!", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void deleteNote() {
        alert.setTitle("Delete")
                .setMessage("Are you sure you want to delete this note?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM notes WHERE id=" + ID;
                        try {
                            db.execSQL(sql);
                        } catch (Exception e) {}
                        Toast.makeText(EditNote.this, "Note has been deleted", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        }).show();
    }
}