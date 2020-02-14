package com.duynam.myapplication.untils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

import com.duynam.myapplication.model.sevendayweather.Day;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Untils {

    private Context context;

    public static void getCurrendate(TextView textView, String month) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/EEEE");
        String currendate = format.format(new Date());
        String[] date = currendate.split("/");
        textView.setText(date[2] + "," + " " + date[0] + " " + month + " " + date[1]);
    }

    public static Integer getCurrenHour() {
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        String currendate = format.format(new Date());
        int hour = Integer.parseInt(currendate);
        return hour;
    }

    public static String convertImageName(String fullname) {

        String[] name = fullname.split("\\.");
        return name[0].toLowerCase();

    }

    public static String checkWeather(Day day) {
        if (day.getHumidMaxPct() > 50) {
            return "isorainswrsday";
        } else if (day.getTempMinC() > 25.0) {
            return "sunny";
        } else if (day.getTempMinC() < 20 && day.getHumidMaxPct() > 50) {
            return "mist";
        }
        return "cloudy";
    }

    public static String setCurrentNameCity(Context context, double latitude, double longtitude) {
        String cityname = null;
        Geocoder geocoder = new Geocoder( context , Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
            cityname = addressList.get(0).getAdminArea();
            return cityname;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityname;
    }


}
