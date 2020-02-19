package com.duynam.myapplication.untils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.model.sevendayweather.Day;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

}
