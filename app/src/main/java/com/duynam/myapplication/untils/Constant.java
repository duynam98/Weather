package com.duynam.myapplication.untils;

public class Constant {

    public static final String app_id = "884027ed";
    public static final String app_key = "b26a92bfaea6f49be8bbcac0f11fd43f";
    public static final String baseCurrenLocation = "api/current/";
    public static final int ITEM = 1;
    public static int MORE = 2;
    public static String TABLE_CURRENT_WEATHER = "Currentweather";
    public static String COLUMN_ID = "id";
    public static String COLUMN_LAT = "Latitude";
    public static String COLUMN_LON = "Longtitude";
    public static String COLUMN_NAME_CITY = "NameCity";
    public static String COLUMN_TEMP_C = "TempC";

    public static String CREATE_TABLE_CURRENT_WEATHER = "CREATE TABLE " + TABLE_CURRENT_WEATHER + "(" +
            COLUMN_ID + " INTEGER," +
            COLUMN_LAT + " DOUBLE," +
            COLUMN_LON + " DOUBLE," +
            COLUMN_NAME_CITY + " TEXT PRIMARY KEY," +
            COLUMN_TEMP_C + " DOUBLE" +
            ")";

}
