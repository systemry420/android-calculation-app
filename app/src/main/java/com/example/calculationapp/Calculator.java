package com.example.calculationapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    private TextView tvDisplay;
    private String lastPressed =  "";
    private String currentDisplay = "0";
    private String firstNumber, secondNumber, currentNumber = "";
    private String operation = "";
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
        } else if("/-x+DLT=".contains(clickedBtnText)) {
            handleOperations(clickedBtnText);
        } else {
            handleNumbers(clickedBtnText);
        }
    }

    private void clearDisplay() {
        lastPressed = operation = "";
        currentDisplay = "0";
        firstNumber = secondNumber = "";
        tvDisplay.setText("0");
    }

    private void handleNumbers(String clickedBtnText) {
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
    }

    private void handleOperations(String clickedBtnText) {
        if (clickedBtnText.equals("DLT")) {
            if(currentDisplay.length() != 0 || lastPressed.length() != 0) {
                currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
                lastPressed = lastPressed.substring(0, lastPressed.length() - 1);
            }
        } else {
            if (operation.equals("")) {
                operation = clickedBtnText;
                Log.i("op = null", "handleClick: lastPressed" + lastPressed);
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
                // this still produces a bug
                try {
                    result = Integer.parseInt(fn) / Integer.parseInt(sn);
                    break;
                } catch (Exception e) {}
        }
        return String.valueOf(result);
    }
}