package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddCost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        Intent intent = getIntent();
        int tripId = intent.getIntExtra("tripId",0);
        String tripName = intent.getStringExtra("tripName");

        TextView tvId = findViewById(R.id.tvId);
        TextView editTextName = findViewById(R.id.tvNameOfTrip);

        tvId.setText(String.valueOf(tripId));
        editTextName.setText(tripName);
    }
}