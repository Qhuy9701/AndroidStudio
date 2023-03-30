package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.entities.DatabaseHelper;
import com.example.myapplication.entities.Trip;

import java.util.List;

public class TripActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    private TripAdapter tripAdapter;
    private List<Trip> tripList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());

        listView = findViewById(R.id.listView);
        showTrips();
        Button btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có chắc chắn muốn xoá tất cả chuyến đi?")
                    .setCancelable(false)
                    .setPositiveButton("Đồng ý", (dialog, id) -> {
                        DatabaseHelper dbHelper = new DatabaseHelper(this);
                        dbHelper.deleteAllTrips();
                        Toast.makeText(this, "Xoá tất cả thành công", Toast.LENGTH_SHORT).show();
                        showTrips();
                    })
                    .setNegativeButton("Hủy", (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
        });

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        tripList = databaseHelper.getTrip();
        tripAdapter = new TripAdapter(TripActivity.this, tripList);
        listView.setAdapter(tripAdapter);

        SearchView searchView = findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tripList = databaseHelper.getTripByName(newText);
                tripAdapter = new TripAdapter(TripActivity.this, tripList);
                listView.setAdapter(tripAdapter);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showTrips() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Trip> trips = dbHelper.getTrip();
        tripAdapter = new TripAdapter(TripActivity.this, trips);
        listView.setAdapter(tripAdapter);
    }
}
