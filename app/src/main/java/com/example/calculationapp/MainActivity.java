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

public class MainActivity extends AppCompatActivity {
    Spinner spView;
    ListView listView;
    LinearLayout cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spView = findViewById(R.id.spView);
        listView = findViewById(R.id.listView);
        cardView = findViewById(R.id.cardView);

        ActivityItem[] activitiesArray = {
            new ActivityItem("Calculator", R.drawable.ic_calculator),
            new ActivityItem("Multiplication Table", R.drawable.ic_multispeed),
            new ActivityItem("Weight Converter", R.drawable.ic_weight),
        };

        ActivityAdapter adapter = new ActivityAdapter(this, activitiesArray);
        listView.setAdapter(adapter);

        setSpinnerListeners();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (activitiesArray[position].getText()) {
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
                    cardView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
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