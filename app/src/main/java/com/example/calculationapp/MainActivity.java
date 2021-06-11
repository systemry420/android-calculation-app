package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculationapp.adapters.ActivityAdapter;
import com.example.calculationapp.data.ActivityItem;

public class MainActivity extends AppCompatActivity {
    private Spinner spView;
    private ListView listView;
    private LinearLayout cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spView = findViewById(R.id.spView);
        listView = findViewById(R.id.listView);
        cardView = findViewById(R.id.cardView);

        ActivityItem[] activitiesArray = {
            new ActivityItem("Weight Converter", R.drawable.ic_weight),
            new ActivityItem("Multiplication Table", R.drawable.ic_multispeed),
            new ActivityItem("Calculator", R.drawable.ic_calculator),
            new ActivityItem("Notepad", R.drawable.ic_note),
        };

        ActivityAdapter adapter = new ActivityAdapter(this, activitiesArray);
        listView.setAdapter(adapter);

        setSpinnerListeners();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (activitiesArray[position].getText()) {
                    case "Notepad":
                        openNotepadActivity();
                        break;
                    case "Calculator":
                        openCalculatorActivity();
                        break;
                    case "Multiplication Table":
                        openMultiplicationActivity();
                        break;
                    case "Weight Converter":
                        openConverterActivity();
                        break;
                }
            }
        });
    }


    private void setSpinnerListeners() {
        spView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    cardView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Notepad":
                openNotepadActivity();
                break;
            case "Calculator":
                openCalculatorActivity();
                break;
            case "Multiplication Table":
                openMultiplicationActivity();
                break;
            case "Weight Converter":
                openConverterActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openActivity(View view) {
        TextView tv = (TextView) view;
        switch (tv.getText().toString()) {
            case "Notepad":
                openNotepadActivity();
                break;
            case "Calculator":
                openCalculatorActivity();
                break;
            case "Multiplication Table":
                openMultiplicationActivity();
                break;
            case "Weight Converter":
                openConverterActivity();
                break;
        }
    }


    private void openNotepadActivity() {
        Intent intent = new Intent();
        intent.setClass(this, Notepad.class);
        startActivity(intent);
    }

    public void openCalculatorActivity() {
        Intent intent = new Intent();
        intent.setClass(this, Calculator.class);
        startActivity(intent);
    }

    public void openMultiplicationActivity() {
        Intent intent = new Intent();
        intent.setClass(this, Multiplication.class);
        startActivity(intent);
    }

    public void openConverterActivity() {
        Intent intent = new Intent();
        intent.setClass(this, WeightConverter.class);
        startActivity(intent);
    }
}