package com.duynam.myapplication.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.duynam.myapplication.R;
import com.duynam.myapplication.model.sevendayweather.Day;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utils {

    private Context context;

    public static String getCurrendate(String month) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/EEEE", Locale.getDefault());
        String currendate = format.format(new Date());
        String[] date = currendate.split("/");
        return String.format("%s , %s %s %s", date[2], date[0], month, date[1]);
    }

    public static Integer getCurrenHour() {
        SimpleDateFormat format = new SimpleDateFormat("HHmm", Locale.getDefault());
        String currendate = format.format(new Date());
        int hour = Integer.parseInt(currendate);
        return hour;
    }

    public static String convertImageName(String fullname) {
        return fullname.replaceAll(".gif", "").replaceAll(".GIF", "").toLowerCase();
    }

    public static String checkWeather(Context context, Day day) {
        if (day.getHumidMaxPct() > 50) {
            return context.getString(R.string.isorainday);
        } else if (day.getTempMinC() > 25.0) {
            return context.getString(R.string.sunny);
        } else if (day.getTempMinC() < 20 && day.getHumidMaxPct() > 50) {
            return context.getString(R.string.mist);
        }
        return context.getString(R.string.cloudy);
    }

    public static String setCurrentNameCity(Context context, double latitude, double longtitude) {
        String cityname = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
            cityname = addressList.get(0).getAdminArea();
            return cityname;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityname;
    }

    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    public static int random(){
        Random random = new Random();
        int speedcar = 0;
        speedcar = random.nextInt((100 - 30) + 1) + 30;
        return speedcar;
    }

    public static String checkWindDegree(Context context, int degree){
        if (degree>337.5) return context.getString(R.string.wind_degree_northerly);
        if (degree>292.5) return context.getString(R.string.wind_degree_North_Westerly);
        if(degree>247.5) return context.getString(R.string.wind_degree_Westerly);
        if(degree>202.5) return context.getString(R.string.wind_degree_South_Westerly);
        if(degree>157.5) return context.getString(R.string.wind_degree_Southerly);
        if(degree>122.5) return context.getString(R.string.wind_degree_South_Easterly);
        if(degree>67.5) return context.getString(R.string.wind_degree_Easterly);
        if(degree>22.5) return context.getString(R.string.wind_degree_North_Easterly);
        return context.getString(R.string.wind_degree_northerly);
    }

    public static String formatISODatetoDate(String ISODate){
        String result1 = null;
        DateFormat df1 = new SimpleDateFormat("yyy-MM-dd");
        try {
            result1 = String.valueOf(df1.parse(ISODate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result1.substring(0, 10);
    }

}
