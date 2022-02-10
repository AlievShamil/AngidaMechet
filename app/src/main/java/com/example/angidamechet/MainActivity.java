package com.example.angidamechet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button incomeET = findViewById(R.id.btnMainIncome);
        Button declineET = findViewById(R.id.btnMainDecline);

        incomeET.setOnClickListener(view -> {
            Intent incomeIntent = new Intent(this, IncomeActivity.class);
            startActivity(incomeIntent);
        });

        declineET.setOnClickListener(view -> {
            Intent declineIntent = new Intent(this, DeclineActivity.class);
            startActivity(declineIntent);
        });
    }
}