package com.example.myapplication.entities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //tên database
    private static final String DATABASE_NAME = "Trips";
    //tên bảng
    public static final String TABLE_TRIP = "Trips";
    public static final String TABLE_COST = "Cost";
    //tên các cột
    public static final String TRIP_ID = "trip_id";
    public static final String TRIP_NAME = "trip_name";
    public static final String TRIP_TRANSPORTATION = "trip_transportation";
    public static final String TRIP_DESTINATION = "trip_destination";
    public static final String TRIP_DATE = "trip_date";
    public static final String TRIP_REQUIRES = "trip_requires";
    public static final String TRIP_DESCRIPTION = "trip_description";
    public static final String TRIP_SERVICES = "trip_services";

    //tên các cột
    public static final String COST_ID = "cost_id";
    public static final String FK_TRIP_ID = "trip_id";
    public static final String COST_TYPE = "cost_type";
    public static final String COST_AMOUNT = "cost_amount";
    public static final String COST_TIME = "cost_time";
    public static final String COST_COMMENTS = "cost_comment";

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

    private static final String TABLE_COST_CREATE = String.format(
            "CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s INTEGER NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s REAL NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL)", // Add the new column definition here
            TABLE_COST,
            COST_ID,
            FK_TRIP_ID,
            COST_TYPE,
            COST_AMOUNT,
            COST_TIME,
            COST_COMMENTS
    );


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_TRIP_CREATE);
        Log.e("quanghuy", TABLE_COST_CREATE);
        db.execSQL(TABLE_COST_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xóa bảng cũ và tạo lại nếu có phiên bản mới của database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COST);
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
    public void insertCost(int tripId, String costType, double costAmount, String costTime, String costComment) {
        ContentValues values = new ContentValues();
        values.put(FK_TRIP_ID, tripId);
        values.put(COST_TYPE, costType);
        values.put(COST_AMOUNT, costAmount);
        values.put(COST_TIME, costTime);
        values.put(COST_COMMENTS, costComment);

        long result = database.insert(TABLE_COST, null, values);

        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert cost");
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
    @SuppressLint("Range")
    public ArrayList<Cost> getCost(int tripId) {
        ArrayList<Cost> costs = new ArrayList<>();

        // Select All Query with JOIN
        String my_query = "SELECT " + TABLE_COST + "." + COST_ID + ", " + TABLE_COST + "." + COST_TYPE + ", " + TABLE_COST + "." + COST_AMOUNT + ", " + TABLE_TRIP + "." + TRIP_NAME + " FROM " + TABLE_COST + " INNER JOIN " + TABLE_TRIP + " ON " + TABLE_COST + "." + FK_TRIP_ID + " = " + TABLE_TRIP + "." + TRIP_ID + " WHERE " + TABLE_COST + "." + FK_TRIP_ID + " = ?";

        Cursor cursor = database.rawQuery(my_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cost cost = new Cost();
                cost.setCostId(cursor.getInt(cursor.getColumnIndex(COST_ID)));
                cost.setTripId(cursor.getInt(cursor.getColumnIndex(FK_TRIP_ID)));
                cost.setCostType(cursor.getString(cursor.getColumnIndex(COST_TYPE)));
                cost.setCostAmount(cursor.getDouble(cursor.getColumnIndex(COST_AMOUNT)));
                cost.setCostTime(cursor.getString(cursor.getColumnIndex(COST_TIME)));
                cost.setCostComment(cursor.getString(cursor.getColumnIndex(COST_COMMENTS)));
                cost.setCost(cursor.getDouble(cursor.getColumnIndex("cost")));

                costs.add(cost);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return costs;
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
