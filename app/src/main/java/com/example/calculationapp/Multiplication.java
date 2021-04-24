package com.example.calculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Random;

public class Multiplication extends AppCompatActivity {
    int product = 0;
    TextView tvNumber1, tvNumber2;
    EditText etAnswer;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        tvNumber1 = findViewById(R.id.tvNumber1);
        tvNumber2 = findViewById(R.id.tvNumber2);
        etAnswer = findViewById(R.id.etAnswer);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExercise();
            }
        });
        getExercise();

        etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!etAnswer.getText().toString().equals("")) {
                    int answer = Integer.parseInt(etAnswer.getText().toString());
                    if(answer == product) {
                        etAnswer.setBackgroundColor(Color.rgb(100, 200, 100));
                        next.setEnabled(true);
                    } else {
                        etAnswer.setBackgroundColor(Color.rgb(200, 100, 100));
                        next.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void getExercise() {
        Random r = new Random();
        int random1 = r.nextInt(10);
        int random2 = r.nextInt(10);
        product = random1 * random2;
        tvNumber1.setText(String.valueOf(random1));
        tvNumber2.setText(String.valueOf(random2));
        next.setEnabled(false);
        etAnswer.setBackgroundColor(Color.WHITE);
        etAnswer.setText("");
    }
}