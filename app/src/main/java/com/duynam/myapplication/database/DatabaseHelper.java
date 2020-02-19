package com.duynam.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.duynam.myapplication.untils.Constant;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String CREATE_TABLE_CURRENT_WEATHER = "CREATE TABLE " + Constant.TABLE_CURRENT_WEATHER + "(" +
            Constant.COLUMN_LAT + " DOUBLE," +
            Constant.COLUMN_LON + " DOUBLE," +
            Constant.COLUMN_NAME_CITY + " TEXT PRIMARY KEY," +
            Constant.COLUMN_TEMP_C + " DOUBLE," +
            Constant.COLUMN_IMAGE + " TEXT" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "CurrentWeather", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CURRENT_WEATHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_CURRENT_WEATHER);
        onCreate(db);
    }
}
