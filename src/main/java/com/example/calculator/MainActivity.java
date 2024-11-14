package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String currentInput = "";
    private String currentOperator = "";
    private double firstOperand = Double.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateDisplay();
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String operator = button.getText().toString();

        if (!currentInput.isEmpty()) {
            if (!Double.isNaN(firstOperand)) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = performOperation(firstOperand, secondOperand, currentOperator);
                currentInput = String.valueOf(result);
                firstOperand = result;
            } else {
                firstOperand = Double.parseDouble(currentInput);
            }
            currentOperator = operator;
            currentInput = "";
            updateDisplay();
        }
    }

    public void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !Double.isNaN(firstOperand)) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = performOperation(firstOperand, secondOperand, currentOperator);
            currentInput = String.valueOf(result);
            firstOperand = Double.NaN;
            currentOperator = "";
            updateDisplay();
        }
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {

                    return Double.NaN;
                }
            default:
                return operand2;
        }
    }

    private void updateDisplay() {
        textView.setText(currentInput);
    }
}
