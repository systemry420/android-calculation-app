package com.example.calculationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.button.MaterialButton;

public class Calculator extends AppCompatActivity {
    GridLayout gridLayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        gridLayout = findViewById(R.id.buttonsGrid);

        for (int i = 9; i >= 0; i--) {
            MaterialButton btn = new MaterialButton(this);
            btn.setText(String.valueOf(i));
            btn.setId(i);
            GridLayout.LayoutParams params;
            if(i == 0) {
//              params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 2f));
                continue;
            } else {
                params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
            }
            params.height = 0;
            params.width = 0;
            btn.setTextSize(24);
            btn.setLayoutParams(params);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            btn.setTextColor(Color.rgb(88, 88, 88));
            gridLayout.addView(btn);
        }
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