package com.example.calculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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