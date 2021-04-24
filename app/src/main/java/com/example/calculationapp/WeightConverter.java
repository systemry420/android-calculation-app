package com.example.calculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeightConverter extends AppCompatActivity {
    EditText etInput;
    TextView tvGram, tvKilogram, tvOunce;
    LinearLayout card1, card2, card3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        card1 = findViewById(R.id.cardGram);
        card2 = findViewById(R.id.cardKilogram);
        card3 = findViewById(R.id.cardOunce);

        etInput = findViewById(R.id.etInput);
        tvGram = findViewById(R.id.tvGram);
        tvKilogram = findViewById(R.id.tvKilogram);
        tvOunce = findViewById(R.id.tvOunce);

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!etInput.getText().toString().equals("")) {
                    int input = Integer.parseInt(etInput.getText().toString());
                    if(input < 0)
                        return;
                    else {
                        tvGram.setText(String.valueOf(input / 0.0022046));
                        tvKilogram.setText(String.valueOf(input / 2.2046));
                        tvOunce.setText(String.valueOf(input * 16));

//                    tvGram.setVisibility(View.VISIBLE);
//                    tvKilogram.setVisibility(View.VISIBLE);
//                    tvOunce.setVisibility(View.VISIBLE);

                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);

                        etInput.clearFocus();
                    }
                } else {
                    tvGram.setText("");
                    tvKilogram.setText("");
                    tvOunce.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}