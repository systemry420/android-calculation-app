package com.example.calculationapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Calculator extends AppCompatActivity {
    TextView tvDisplay;
    String lastPressed =  "";
    String currentText = "0";
    String firstNumber;
    String secondNumber, currentNumber = "";
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvDisplay = findViewById(R.id.display);
    }


    public void handleClick(View view) {
        Button clickedBtn = (Button) view;
        String clickedBtnText = clickedBtn.getText().toString();

        if (clickedBtnText.equals("C")) {
            clearDisplay();
        } else if( // if operation
            clickedBtnText.equals("/") || clickedBtnText.equals("x") || clickedBtnText.equals("DLT") ||
            clickedBtnText.equals("-") || clickedBtnText.equals("+") || clickedBtnText.equals("=")
        ) {

            if (clickedBtnText.equals("DLT")) {
                currentText = currentText.substring(0, currentText.length() - 1);
                lastPressed = lastPressed.substring(0, lastPressed.length() - 1);
                tvDisplay.setText(currentText);
            } else {
                if (operation == "") {
                    operation = clickedBtnText;
                    Log.i("op = null", "handleClick: " + lastPressed);
                    firstNumber = lastPressed;
                    currentText += operation;
                    lastPressed = "";
                } else if (clickedBtnText.equals("=")) {
                    secondNumber = lastPressed;
                    Log.i("op else ===", "handleClick: " + lastPressed);
                    currentText = evaluate(firstNumber, operation, secondNumber);
                    operation = "";
                }

                tvDisplay.setText(currentText);
            }
        } else { // if number

                if(currentText.equals("0") && currentNumber.equals("")){
                    currentText = clickedBtnText;
                }
                else {
                    currentText = currentText + clickedBtnText;
                }
                currentNumber += clickedBtnText;
                lastPressed += currentNumber;
                currentNumber = "";
                tvDisplay.setText(currentText);

                return;
            }
    }

    private void clearDisplay() {
        lastPressed = operation = "";
        currentText = "0";
        firstNumber = secondNumber = "";
        tvDisplay.setText("0");
        return;
    }

    private String evaluate(String fn, String op, String sn) {
        double result = 0;
        Log.i("result", "evaluate: " + (fn + op + sn));
        switch (op) {
            case "+":
                result = Integer.parseInt(fn) + Integer.parseInt(sn);
                break;

            case "-":
                result = Integer.parseInt(fn) - Integer.parseInt(sn);
                break;

            case "x":
                result = Integer.parseInt(fn) * Integer.parseInt(sn);
                break;

            case "/":
                result = Integer.parseInt(fn) / Integer.parseInt(sn);
                break;
        }
        return String.valueOf(result);
    }
}