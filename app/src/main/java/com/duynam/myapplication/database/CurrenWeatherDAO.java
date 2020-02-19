package com.duynam.myapplication.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.duynam.myapplication.model.City;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.untils.Constant;

import java.util.ArrayList;
import java.util.List;

public class CurrenWeatherDAO {

    DatabaseHelper databaseHelper;

    public CurrenWeatherDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertCity(CurrenLocalCity city) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMN_NAME_CITY, city.getName());
        values.put(Constant.COLUMN_TEMP_C, city.getTemp());
        values.put(Constant.COLUMN_IMAGE, city.getIcon());
        values.put(Constant.COLUMN_LAT, city.getLatitude());
        values.put(Constant.COLUMN_LON, city.getLongtitude());
        long id = db.insert(Constant.TABLE_CURRENT_WEATHER, null, values);
        db.close();
        Log.e("id", "insertnote: " + id);
    }

    public List<CurrenLocalCity> getAllCity() {
        List<CurrenLocalCity> cities = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constant.TABLE_CURRENT_WEATHER;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String name_city = cursor.getString(cursor.getColumnIndex(Constant.COLUMN_NAME_CITY));
                Double tempC = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_TEMP_C));
                String  image = cursor.getString(cursor.getColumnIndex(Constant.COLUMN_IMAGE));
                Double latitude = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_LAT));
                Double longtitude = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_LON));
                CurrenLocalCity city = new CurrenLocalCity(name_city, tempC, image, latitude, longtitude);
                cities.add(city);
            } while (cursor.moveToNext());
        }
        db.close();
        return cities;
    }

    public CurrenLocalCity getCity(String cityname) {
        CurrenLocalCity city = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(Constant.TABLE_CURRENT_WEATHER, new String[]{Constant.COLUMN_NAME_CITY, Constant.COLUMN_TEMP_C, Constant.COLUMN_IMAGE,
                Constant.COLUMN_LAT, Constant.COLUMN_LON},
                Constant.COLUMN_NAME_CITY + "=?",
                new String[]{cityname},
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String city_name = cursor.getString(cursor.getColumnIndex(Constant.COLUMN_NAME_CITY));
            Double tempC = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_TEMP_C));
            String image = cursor.getString(cursor.getColumnIndex(Constant.COLUMN_IMAGE));
            Double latitude = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_LAT));
            Double longtitude = cursor.getDouble(cursor.getColumnIndex(Constant.COLUMN_LON));
            city = new CurrenLocalCity(city_name, tempC, image, latitude, longtitude);
        }
        cursor.close();
        return city;
    }

    public long updateNote(CurrenLocalCity city) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMN_NAME_CITY, city.getName());
        values.put(Constant.COLUMN_TEMP_C, city.getTemp());
        values.put(Constant.COLUMN_IMAGE, city.getIcon());
        values.put(Constant.COLUMN_LAT, city.getLatitude());
        values.put(Constant.COLUMN_LON, city.getLongtitude());
        return db.update(Constant.TABLE_CURRENT_WEATHER, values, Constant.COLUMN_NAME_CITY + " = ?",
                new String[]{String.valueOf(city.getName())});
    }

    public void delete(CurrenLocalCity currenLocalCity){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(Constant.TABLE_CURRENT_WEATHER, Constant.COLUMN_NAME_CITY + "=?", new String[]{currenLocalCity.getName()});
        db.close();
    }


}
