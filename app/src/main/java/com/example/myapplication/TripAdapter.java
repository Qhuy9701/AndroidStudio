package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.entities.DatabaseHelper;
import com.example.myapplication.entities.Trip;

import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip> {

    public static Context mContext;

    public TripAdapter(Context context, List<Trip> trips) {
        super(context, 0, trips);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trip trip = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.infotrip, parent, false);
        }

        TextView tvname = convertView.findViewById(R.id.tvName);
        TextView tvtrans = convertView.findViewById(R.id.tvTrans);
        TextView tvdestination = convertView.findViewById(R.id.tvDestination);
        TextView tvdate = convertView.findViewById(R.id.tvDate);
        TextView tvrequires = convertView.findViewById(R.id.tvRequires);
        TextView tvdescription = convertView.findViewById(R.id.tvDescription);
        TextView tvother = convertView.findViewById(R.id.tvServices);



        tvname.setText(trip.getName());
        tvtrans.setText(trip.getTransportation());
        tvdestination.setText(trip.getDestination());
        tvdate.setText(trip.getDateOfTheTrip());
        tvrequires.setText(trip.getRequiresRiskAssessment());
        tvdescription.setText(trip.getDescription());
        tvother.setText(trip.getOtherServices());

        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(mContext)
                    .setTitle("Xác nhận xoá")
                    .setMessage("Bạn có chắc muốn xoá?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Xoá khi nhấn nút "Có"
                            DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                            dbHelper.deleteTrip(trip.getTripId());
                            remove(trip);
                            notifyDataSetChanged();
                            Toast.makeText(mContext, "Xoá thành công", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn nút "Không"
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });


        return convertView;
    }
}
