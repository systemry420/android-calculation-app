package com.example.calculationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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