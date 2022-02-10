package com.example.angidamechet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeclineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decline);

        EditText sumDeclineET = findViewById(R.id.etDeclineSum);
        EditText descDeclineET = findViewById(R.id.etDeclineDescription);
        Button declineBtn = findViewById(R.id.btnDecline);

        Utils declineUtils = new Utils(sumDeclineET,descDeclineET, declineBtn);

        sumDeclineET.addTextChangedListener(declineUtils.textWatcher);
        descDeclineET.addTextChangedListener(declineUtils.textWatcher);

        declineBtn.setOnClickListener(view -> declineUtils.addNotesToSheet(this));
    }
}