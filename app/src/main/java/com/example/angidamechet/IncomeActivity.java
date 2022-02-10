package com.example.angidamechet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class IncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        EditText sumIncomeET = findViewById(R.id.etIncomeSum);
        EditText descIncomeET = findViewById(R.id.etIncomeDescription);
        Button incomeBtn = findViewById(R.id.btnIncome);

        Utils incomeUtils = new Utils(sumIncomeET, descIncomeET, incomeBtn);

        sumIncomeET.addTextChangedListener(incomeUtils.textWatcher);
        descIncomeET.addTextChangedListener(incomeUtils.textWatcher);

        incomeBtn.setOnClickListener(view -> incomeUtils.addNotesToSheet(this));
    }

}