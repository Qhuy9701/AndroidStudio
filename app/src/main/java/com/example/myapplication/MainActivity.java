package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.entities.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khai bao các button
        Button btnClear = findViewById(R.id.btnClear);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnViewAll = findViewById(R.id.btnViewAll);

        //Khai bao các textbox
        EditText txtName = findViewById(R.id.txtNameOfTrip);
        EditText txtTrans = findViewById(R.id.txtTransportation);
        EditText txtDestination = findViewById(R.id.txtDestination);
        EditText txtDate = findViewById(R.id.txtDateOfTheTrip);
        EditText txtRequires = findViewById(R.id.txtRequiresRiskAssessmen);
        EditText txtDescription = findViewById(R.id.txtDescription);
        EditText txtServices = findViewById(R.id.txtOtherServices);

        //bat su kien
        btnClear.setOnClickListener(view -> {
            //lay thong tin va set text
            txtName.setText("");
            txtTrans.setText("");
            txtDestination.setText("");
            txtDate.setText("");
            txtRequires.setText("");
            txtDescription.setText("");
            txtServices.setText("");
        });

        btnSave.setOnClickListener(view -> {
            String name = txtName.getText().toString();
            String trans = txtTrans.getText().toString();
            String desti = txtDestination.getText().toString();
            String date = txtDate.getText().toString();
            String requires = txtRequires.getText().toString();
            String descrip = txtDescription.getText().toString();
            String services = txtServices.getText().toString();

            // Khai báo biến để kiểm tra thông tin hợp lệ
            boolean isValid = true;

            // Kiểm tra các trường thông tin
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên chuyến đi", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (trans.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập phương tiện đi lại", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (desti.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập điểm đến", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (date.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập ngày đi", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                Toast.makeText(this, "Ngày đi không đúng định dạng (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (requires.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập yêu cầu đánh giá rủi ro", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (descrip.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mô tả chuyến đi", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else if (services.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập các dịch vụ khác", Toast.LENGTH_SHORT).show();
                isValid = false;
            }

            // Lưu thông tin vào cơ sở dữ liệu nếu thông tin hợp lệ
            if (isValid) {
                // Thông báo khi nhập thành công
                Toast.makeText(this , "Vé của bạn bao gồm các thông tin sau "
                        + name + "-"
                        + trans + "-"
                        + desti + "-"
                        + date + "-"
                        + requires + "-"
                        + descrip + "-"
                        + services, Toast.LENGTH_LONG).show();

                DatabaseHelper dbHandler = new DatabaseHelper(getApplicationContext());
                dbHandler.insertTrip(txtName.getText().toString(),
                        txtTrans.getText().toString(),
                        txtDestination.getText().toString(),
                        txtDate.getText().toString(),
                        txtRequires.getText().toString(),
                        txtDescription.getText().toString(),
                        txtServices.getText().toString());

                        txtName.setText("");
                        txtTrans.setText("");
                        txtDestination.setText("");
                        txtDate.setText("");
                        txtRequires.setText("");
                        txtDescription.setText("");
                        txtServices.setText("");

            }
        });
        btnViewAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TripActivity.class);
            startActivity(intent);
        });
    }
}