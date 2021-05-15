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
    String currentDisplay = "0";
    String firstNumber, secondNumber, currentNumber = "";
    String operation = "";
    private boolean evaluated = false;

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
                "/-x+DLT=".contains(clickedBtnText)
        ) {

            if (clickedBtnText.equals("DLT")) {
                if(currentDisplay.length() != 0 || lastPressed.length() != 0) {
                    currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
                    lastPressed = lastPressed.substring(0, lastPressed.length() - 1);
                }
            } else {
                if (operation == "") {
                    operation = clickedBtnText;
                    Log.i("op = null", "handleClick: lastpressed" + lastPressed);
                    if (lastPressed.equals(secondNumber))
                        firstNumber = currentDisplay;
                    else
                        firstNumber = lastPressed;
                    currentDisplay += operation;
                    lastPressed = "";
                } else if (clickedBtnText.equals("=")) {
                    secondNumber = lastPressed;
                    Log.i("op else ===", "handleClick: lastPressed = " + lastPressed);
                    currentDisplay = evaluate(firstNumber, operation, secondNumber);
                    evaluated = true;
                    operation = "";
                }
            }
            tvDisplay.setText(currentDisplay);
        } else { // if number

                if(currentDisplay.equals("0") && currentNumber.equals("")){
                    currentDisplay = clickedBtnText;
                }
                else {
                    if(evaluated){
                        Log.i("!secondNumber.equals()", "handleClick: secondNumber = " + secondNumber);
                        Toast.makeText(this, "a"+secondNumber, Toast.LENGTH_LONG).show();
                        lastPressed = "";
                        currentDisplay = "";
                        evaluated = false;
                        operation = "";
                        secondNumber = "";
                    }
                    currentDisplay = currentDisplay + clickedBtnText;
                }
                currentNumber += clickedBtnText;
                lastPressed += currentNumber;
                currentNumber = "";
                tvDisplay.setText(currentDisplay);

                return;
            }
    }

    private void clearDisplay() {
        lastPressed = operation = "";
        currentDisplay = "0";
        firstNumber = secondNumber = "";
        tvDisplay.setText("0");
        return;
    }

    private String evaluate(String fn, String op, String sn) {
        double result = 0.0;
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