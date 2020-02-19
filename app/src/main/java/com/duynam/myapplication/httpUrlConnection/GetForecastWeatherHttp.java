package com.duynam.myapplication.httpUrlConnection;

import android.os.AsyncTask;

import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Forecast;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetForecastWeatherHttp extends AsyncTask<String, Void, HashMap<String, List<?>>> {

    private List<Day> dayList = new ArrayList<>();
    private OnLoadForecast onLoadForecast;
    private List<Timeframe> timeframeList = new ArrayList<>();
    HashMap<String, List<?>> stringListHashMap = new HashMap<>();

    public void setOnLoadForecast(OnLoadForecast onLoadForecast) {
        this.onLoadForecast = onLoadForecast;
    }

    @Override
    protected HashMap<String, List<?>> doInBackground(String... strings) {
        stringListHashMap.put("listDay", dayList);
        stringListHashMap.put("listTime", timeframeList);

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
            }
            if (builder.toString() != null) {
                Gson gson = new Gson();
                dayList = gson.fromJson(builder.toString(), Forecast.class).getDays();
                timeframeList = dayList.get(0).getTimeframes();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringListHashMap;
    }

    @Override
    protected void onPostExecute(HashMap<String, List<?>> stringListHashMap) {
        super.onPostExecute(stringListHashMap);
        if (stringListHashMap != null){
            onLoadForecast.LoadForecast(dayList);
            onLoadForecast.LoadTimeFrame(timeframeList);
        }
    }

    public interface OnLoadForecast {
        void LoadForecast(List<Day> dayListhttp);
        void LoadTimeFrame(List<Timeframe> timeframeshttp);
    }

    //    @Override
//    protected String doInBackground(String... strings) {
//        try {
//            URL url = new URL(strings[0]);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = connection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder builder = new StringBuilder();
//            String line;
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
//                while ((line = bufferedReader.readLine()) != null){
//                    builder.append(line);
//                }
//            }
//            return builder.toString();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        if (s != null){
//            Gson gson = new Gson();
//            dayList = gson.fromJson(s, Forecast.class).getDays();
//            timeframeList = dayList.get(0).getTimeframes();
//            onLoadForecast.LoadForecast(dayList);
//            onLoadForecast.LoadTimeFrame(timeframeList);
//        }
//    }
}
