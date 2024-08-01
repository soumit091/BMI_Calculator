package com.example.bmi_calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightFeetEditText;
    private EditText heightInchesEditText;
    private Button calculateButton;
    private TextView resultTextView;
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        weightEditText = findViewById(R.id.weightEditText);
        heightFeetEditText = findViewById(R.id.heightFeetEditText);
        heightInchesEditText = findViewById(R.id.heightInchesEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightFeetStr = heightFeetEditText.getText().toString();
        String heightInchesStr = heightInchesEditText.getText().toString();

        if (!weightStr.isEmpty() && !heightFeetStr.isEmpty() && !heightInchesStr.isEmpty()) {
            int weight = Integer.parseInt(weightStr);
            int heightFeet = Integer.parseInt(heightFeetStr);
            int heightInches = Integer.parseInt(heightInchesStr);

            // Convert height from feet and inches to centimeters
            double heightInCm = heightFeet * 30.48 + heightInches * 2.54;

            // Convert centimeters to meters
            double heightInMeters = heightInCm / 100.0;

            double bmi = weight / (heightInMeters * heightInMeters);
            String bmiText;
            int bgColor;

            if (bmi < 18.5) {
                bmiText = String.format("Your BMI: %.2f - Underweight", bmi);
                bgColor = getResources().getColor(R.color.underweight_bg);
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiText = String.format("Your BMI: %.2f - Normal weight", bmi);
                bgColor = getResources().getColor(R.color.normalweight_bg);
            } else {
                bmiText = String.format("Your BMI: %.2f - Overweight", bmi);
                bgColor = getResources().getColor(R.color.overweight_bg);
            }

            resultTextView.setText(bmiText);
            mainLayout.setBackgroundColor(bgColor);
        } else {
            resultTextView.setText("Please enter weight, height in feet, and inches");
            mainLayout.setBackgroundColor(Color.WHITE);
        }
    }
}
