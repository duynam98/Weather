package com.duynam.myapplication.untils;

import android.widget.TextView;

import com.duynam.myapplication.view.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Untils {

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



}
