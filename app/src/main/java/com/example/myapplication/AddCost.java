package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.entities.Cost;
import com.example.myapplication.entities.DatabaseHelper;
import com.example.myapplication.entities.Trip;

import java.util.List;

public class AddCost extends AppCompatActivity {
    private ListView listView;
    private int tripId; // Declare tripId as a global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        Intent intent = getIntent();
        tripId = intent.getIntExtra("tripId", 0);
        String tripName = intent.getStringExtra("tripName");

        TextView tvId = findViewById(R.id.tvId);
        TextView editTextName = findViewById(R.id.tvNameOfTrip);

        tvId.setText(String.valueOf(tripId));
        editTextName.setText(tripName);

        listView = findViewById(R.id.listviewcost);
        showCost(); // Call the method to display existing cost items
        Button btnBack2 = findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(v -> onBackPressed());
        Button btnAddCost = findViewById(R.id.btnInsertCost);
        btnAddCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText type = findViewById(R.id.txtTypeofexpense);
                EditText amount = findViewById(R.id.txtAmountoftheexpense);
                EditText time = findViewById(R.id.txtTimeoftheexpense);
                EditText comment = findViewById(R.id.txtAdditionalcomments);
                DatabaseHelper dbHelper = new DatabaseHelper(AddCost.this);

                // After successful insert
                try {
                    double costAmount = Double.parseDouble(amount.getText().toString());
                    dbHelper.insertCost(tripId, type.getText().toString(),
                            costAmount,
                            time.getText().toString(),
                            comment.getText().toString());

                    String message = "Thêm thành công: " +
                            type.getText().toString() + ", " +
                            amount.getText().toString() + ", " +
                            time.getText().toString() + ", " +
                            comment.getText().toString();
                    Toast.makeText(AddCost.this, message, Toast.LENGTH_SHORT).show();
                    showCost(); // Update listView with the latest cost list
                } catch (NumberFormatException e) {
                    Toast.makeText(AddCost.this, "Lỗi: Số tiền không hợp lệ", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddCost.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void showCost() {
        DatabaseHelper dbHelper = new DatabaseHelper(AddCost.this);
        List<Cost> costList = dbHelper.getCost(tripId);
        ArrayAdapter<Cost> adapter = new ArrayAdapter<>(AddCost.this,
                android.R.layout.simple_list_item_1, costList);
        listView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
