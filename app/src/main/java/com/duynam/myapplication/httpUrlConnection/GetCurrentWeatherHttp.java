package com.duynam.myapplication.httpUrlConnection;

import android.os.AsyncTask;
import android.util.Log;

import com.duynam.myapplication.model.CurrenWeather;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetCurrentWeatherHttp extends AsyncTask<String, Void, String> {

    private CurrenWeather currenWeather;
    public OnLoadData onLoadData = null;

    @Override
    protected String doInBackground(String... strings) {
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
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        currenWeather = new CurrenWeather();
        if (s != null) {
            Gson gson = new Gson();
            CurrenWeather weather = gson.fromJson(s, CurrenWeather.class);
            currenWeather = weather;
            Log.e("ObjectWeather", "onPostExecute: " + currenWeather );
            onLoadData.onLoadFinish(currenWeather);
        }
    }

    public interface OnLoadData{
        void onLoadFinish(CurrenWeather currenWeather);
    }

}
