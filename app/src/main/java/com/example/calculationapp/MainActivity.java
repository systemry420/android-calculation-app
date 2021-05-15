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
                Intent intent = new Intent();
                intent.setClass(this, Calculator.class);
                startActivity(intent);
                break;
            case "Multiplication Table":
                intent = new Intent();
                intent.setClass(this, Multiplication.class);
                startActivity(intent);
                break;
            case "Weight Converter":
                intent = new Intent();
                intent.setClass(this, WeightConverter.class);
                startActivity(intent);
                break;
        }

        return true;
    }


    public void openCalculatorActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, Calculator.class);
        startActivity(intent);
    }

    public void openMultiplicationActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, Multiplication.class);
        startActivity(intent);
    }

    public void openConverterActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, WeightConverter.class);
        startActivity(intent);
    }
}