package com.example.angidamechet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    private final EditText sumET;
    private final EditText descriptionET;
    private final Button acceptBtn;

    public Utils(EditText sumET, EditText descriptionET, Button acceptBtn) {
        this.sumET = sumET;
        this.descriptionET = descriptionET;
        this.acceptBtn = acceptBtn;
    }

    public void addNotesToSheet(Context context) {
        final ProgressDialog loading = ProgressDialog.show(context, "Добавление записи", "Пожалуйста подождите");
        final String descInput = descriptionET.getText().toString().trim();
        String sumInput = sumET.getText().toString().trim();
        ;

        if (context.getClass().getSimpleName().equals("DeclineActivity")) {
            sumInput = "-" + sumInput;
        }

        final String finalSumInput = sumInput;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxxnRB963eAFynt7TUtyHXZ2w8zLYJL8WwonVBikaocDBWjD-U/exec",
                response -> {
                    loading.dismiss();
                    Toast.makeText(context, "Запись успешно добавлена", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                },
                error -> {
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("action", "addNote");
                params.put("sum", finalSumInput);
                params.put("desc", descInput);

                return params;
            }
        };

        int socketTimeOut = 10000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }

    public final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String sumInput = sumET.getText().toString().trim();
            String descInput = descriptionET.getText().toString().trim();

            acceptBtn.setEnabled(!sumInput.isEmpty() && !descInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
