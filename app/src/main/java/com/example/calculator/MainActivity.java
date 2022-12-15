package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    TextView textViewTemp;
    float tempSum;
    char lastOperator;
    boolean isNewRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        textViewTemp = findViewById(R.id.textViewTemp);
        initCalculator();
    }

    public void concatNumber(View view) {
        Button btn = (Button) view;

        if (isNewRequest) {
            textViewResult.setText(btn.getText());
            isNewRequest = false;
        } else {
            textViewResult.append(btn.getText());
        }
    }

    public void operatorAction(View view) {
        updateTempSum();

        Button btn = (Button) view;

        lastOperator = btn.getText().toString().charAt(0);
        textViewResult.setText("");
        textViewTemp.setText(printNumber(tempSum) + " " + lastOperator);
    }

    public void reset(View view) {
        textViewResult.setText("");
        textViewTemp.setText("");
        initCalculator();
    }

    public void calculateResult(View view) {
        updateTempSum();

        textViewResult.setText(printNumber(tempSum));
        textViewTemp.setText("");
        initCalculator();
    }

    private void updateTempSum() {
        String textViewText = textViewResult.getText().toString();

        if (textViewText.length() > 0) {
            try {
                float newOperand = Float.parseFloat(textViewText);

                switch (lastOperator) {
                    case ('+'): {
                        tempSum += newOperand;
                        break;
                    }
                    case ('-'): {
                        tempSum -= newOperand;
                        break;
                    }
                    case ('%'): {
                        tempSum /= newOperand;
                        break;
                    }
                    case ('*'): {
                        tempSum *= newOperand;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } catch (Exception e) {
                // no action needed
            }
        }
    }

    private void initCalculator() {
        tempSum = 0;
        lastOperator = '+';
        isNewRequest = true;
    }

    private String printNumber(float number) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        return df.format(number);
    }
}