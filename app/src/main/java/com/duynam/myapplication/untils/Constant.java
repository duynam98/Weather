package com.duynam.myapplication.untils;

public class Constant {

    public static final String app_id = "884027ed";
    public static final String app_key = "b26a92bfaea6f49be8bbcac0f11fd43f";
    public static String TABLE_CURRENT_WEATHER = "Currentweather";
    public static String COLUMN_LAT = "Latitude";
    public static String COLUMN_LON = "Longtitude";
    public static String COLUMN_NAME_CITY = "NameCity";
    public static String COLUMN_TEMP_C = "TempC";
    public static String COLUMN_IMAGE = "Image";
    public static String OPENWEATHER = "https://api.openweathermap.org/data/2.5/weather";
    public static String APPID_OPENWEATHER = "appid=55b62a4688fd9e2485edb1c3cb0ac3c3&units=metric";

    public static String CREATE_TABLE_CURRENT_WEATHER = "CREATE TABLE " + TABLE_CURRENT_WEATHER + "(" +
            COLUMN_LAT + " DOUBLE," +
            COLUMN_LON + " DOUBLE," +
            COLUMN_NAME_CITY + " TEXT PRIMARY KEY," +
            COLUMN_TEMP_C + " DOUBLE," +
            COLUMN_IMAGE + " TEXT" +
            ")";

}
