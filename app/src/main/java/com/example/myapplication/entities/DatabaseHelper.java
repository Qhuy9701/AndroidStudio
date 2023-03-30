package com.example.myapplication.entities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //tên database
    private static final String DATABASE_NAME = "Trips";
    //tên bảng
    public static final String TABLE_TRIP = "Trips";
    //tên các cột
    public static final String TRIP_ID = "trip_id";
    public static final String TRIP_NAME = "trip_name";
    public static final String TRIP_TRANSPORTATION = "trip_transportation";
    public static final String TRIP_DESTINATION = "trip_destination";
    public static final String TRIP_DATE = "trip_date";
    public static final String TRIP_REQUIRES = "trip_requires";
    public static final String TRIP_DESCRIPTION = "trip_description";
    public static final String TRIP_SERVICES = "trip_services";

    private final SQLiteDatabase database;

    private static final String TABLE_TRIP_CREATE = String.format(
            "CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL)",
            TABLE_TRIP,
            TRIP_ID,
            TRIP_NAME,
            TRIP_TRANSPORTATION,
            TRIP_DESTINATION,
            TRIP_DATE,
            TRIP_REQUIRES,
            TRIP_DESCRIPTION,
            TRIP_SERVICES
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_TRIP_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xóa bảng cũ và tạo lại nếu có phiên bản mới của database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        onCreate(db);
    }

    public void insertTrip(String name , String trans , String des , String date , String requir , String descr , String services) {
        ContentValues values = new ContentValues();
        values.put(TRIP_NAME , name);
        values.put(TRIP_TRANSPORTATION,trans);
        values.put(TRIP_DESTINATION,des);
        values.put(TRIP_DATE, date);
        values.put(TRIP_REQUIRES,requir);
        values.put(TRIP_DESCRIPTION,descr);
        values.put(TRIP_SERVICES,services);

        long result = database.insert(TABLE_TRIP, null, values);

        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert trip");
        }

    }


    @SuppressLint("Range")
    public ArrayList<Trip> getTrip() {
        ArrayList<Trip> trips = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TRIP;

        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Trip trip = new Trip();
                trip.setTripId(cursor.getInt(cursor.getColumnIndex(TRIP_ID)));
                trip.setName(cursor.getString(cursor.getColumnIndex(TRIP_NAME)));
                trip.setTransportation(cursor.getString(cursor.getColumnIndex(TRIP_TRANSPORTATION)));
                trip.setDestination(cursor.getString(cursor.getColumnIndex(TRIP_DESTINATION)));
                trip.setDateOfTheTrip(cursor.getString(cursor.getColumnIndex(TRIP_DATE)));
                trip.setRequiresRiskAssessment(cursor.getString(cursor.getColumnIndex(TRIP_REQUIRES)));
                trip.setDescription(cursor.getString(cursor.getColumnIndex(TRIP_DESCRIPTION)));
                trip.setOtherServices(cursor.getString(cursor.getColumnIndex(TRIP_SERVICES)));

                trips.add(trip);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return trip list

        return trips;
    }

    @SuppressLint("Range")
    public ArrayList<Trip> getTripByName(String name) {
        ArrayList<Trip> trips = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_TRIP +
                " WHERE " + TRIP_NAME + " LIKE '%" + name + "%'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Trip trip = new Trip();
                trip.setTripId(cursor.getInt(cursor.getColumnIndex(TRIP_ID)));
                trip.setName(cursor.getString(cursor.getColumnIndex(TRIP_NAME)));
                trip.setTransportation(cursor.getString(cursor.getColumnIndex(TRIP_TRANSPORTATION)));
                trip.setDestination(cursor.getString(cursor.getColumnIndex(TRIP_DESTINATION)));
                trip.setDateOfTheTrip(cursor.getString(cursor.getColumnIndex(TRIP_DATE)));
                trip.setRequiresRiskAssessment(cursor.getString(cursor.getColumnIndex(TRIP_REQUIRES)));
                trip.setDescription(cursor.getString(cursor.getColumnIndex(TRIP_DESCRIPTION)));
                trip.setOtherServices(cursor.getString(cursor.getColumnIndex(TRIP_SERVICES)));

                trips.add(trip);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

    public void deleteTrip(int tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIP, TRIP_ID + " = ?", new String[] { String.valueOf(tripId) });
        db.close();
    }
    public void deleteAllTrips() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIP, null, null);
        db.close();
    }

}
