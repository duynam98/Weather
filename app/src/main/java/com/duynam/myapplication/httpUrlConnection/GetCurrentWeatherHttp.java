package com.duynam.myapplication.httpUrlConnection;

import android.os.AsyncTask;

import com.duynam.myapplication.model.CurrenWeather;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCurrentWeatherHttp extends AsyncTask<String, Void, CurrenWeather> {

    private CurrenWeather currenWeather;
    public OnLoadData onLoadData;

    public void setOnLoadData(OnLoadData onLoadData) {
        this.onLoadData = onLoadData;
    }

    @Override
    protected CurrenWeather doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
            }
            currenWeather = new CurrenWeather();
            if (builder.toString() != null) {
                Gson gson = new Gson();
                CurrenWeather weather = gson.fromJson(builder.toString(), CurrenWeather.class);
                currenWeather = weather;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return currenWeather;
    }

    @Override
    protected void onPostExecute(CurrenWeather s) {
        super.onPostExecute(s);
        if (s!=null){
            onLoadData.onLoadFinish(currenWeather);
        }
    }

    public interface OnLoadData{
        void onLoadFinish(CurrenWeather currenWeather);
    }

}
