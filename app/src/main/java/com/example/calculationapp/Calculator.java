package com.example.calculationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

public class Calculator extends AppCompatActivity {
    LinearLayout buttonsLayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.btnAddARoom:
//                roomName = etAddARoom.getText().toString();
//                Button createdButton = new Button(this);
//                createdButton.setText(roomName);
//                createdButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,         LayoutParams.WRAP_CONTENT));
//                layout.addView(createdButton);
//                break;
//        }
//    }
}