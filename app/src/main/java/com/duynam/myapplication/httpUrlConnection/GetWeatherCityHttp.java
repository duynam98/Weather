package com.duynam.myapplication.httpUrlConnection;

import android.content.Context;
import android.os.AsyncTask;

import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.untils.Untils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetWeatherCityHttp extends AsyncTask<String, Long, String> {

    private CurrenLocalCity currenLocalCity;
    private OnLoadComplete onLoadComplete;
    private Context context;

    public GetWeatherCityHttp(Context context) {
        this.context = context;
    }

    public void setOnLoadComplete(OnLoadComplete onLoadComplete){
        this.onLoadComplete = onLoadComplete;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

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
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String name = null;
        String name_icon = null;
        Double temp = null;
        Double latitude = null;
        Double longtitude = null;
        if (s != null) {
            try {
                JSONObject object = new JSONObject(s);
                JSONObject latlong = object.getJSONObject("coord");
                JSONObject main = object.getJSONObject("main");
                JSONArray weather = object.getJSONArray("weather");
                JSONObject icon = weather.getJSONObject(0);
                if (object.has("name")) {
                    if (object.get("name") instanceof String) {
                        name = object.getString("name");
                    }
                    if (latlong.has("lat") && latlong.has("lon")){
                        if (latlong.get("lat") instanceof Double && latlong.get("lon") instanceof Double){
                            latitude = latlong.getDouble("lat");
                            longtitude = latlong.getDouble("lon");
                        }
                    }
                    if (main.has("temp")) {
                        if (main.get("temp") instanceof Integer || main.get("temp") instanceof Double) {
                            temp = object.getJSONObject("main").getDouble("temp");
                        }
                    }
                    if (icon.has("icon")) {
                        if (icon.get("icon") instanceof String) {
                            name_icon = icon.getString("icon");
                        }
                    }
                    currenLocalCity = new CurrenLocalCity(name, temp, name_icon, latitude, longtitude);
                    onLoadComplete.onListLoadComplete(currenLocalCity);
                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnLoadComplete{
        void onListLoadComplete(CurrenLocalCity localCityList);
    }

}
