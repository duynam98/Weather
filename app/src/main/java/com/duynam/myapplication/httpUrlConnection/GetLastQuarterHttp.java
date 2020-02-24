package com.duynam.myapplication.httpUrlConnection;

import android.os.AsyncTask;

import com.duynam.myapplication.model.modelUsingHttp.FullMoon;

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

public class GetLastQuarterHttp extends AsyncTask<String, Void, FullMoon> {

    private FullMoon fullMoon;
    private int timestamp;
    private String dateTimeISO;
    private int code;
    private String name;
    private OnLoadLastQuarter onLoadLastQuarter;

    public void setOnLoadLastQuarter(OnLoadLastQuarter onLoadLastQuarter) {
        this.onLoadLastQuarter = onLoadLastQuarter;
    }

    @Override
    protected FullMoon doInBackground(String... strings) {
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
            if (builder.toString() != null){
                JSONObject object = new JSONObject(builder.toString());
                JSONArray jsonFullMoon = object.getJSONArray("response");
                for (int i = 0; i <= jsonFullMoon.length(); i++){
                    JSONObject fullmoon_inMonth = jsonFullMoon.getJSONObject(i);
                    if (fullmoon_inMonth.optInt("code") == 3){
                        timestamp = fullmoon_inMonth.optInt("timestamp");
                        dateTimeISO = fullmoon_inMonth.optString("dateTimeISO");
                        code = fullmoon_inMonth.optInt("code");
                        name = fullmoon_inMonth.getString("name");
                        fullMoon = new FullMoon(timestamp, dateTimeISO, code, name);
                        break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullMoon;
    }

    @Override
    protected void onPostExecute(FullMoon fullMoon) {
        super.onPostExecute(fullMoon);
        if (onLoadLastQuarter != null){
            onLoadLastQuarter.onloadlastQuarterFinish(fullMoon);
        }
    }

    public interface OnLoadLastQuarter{
        void onloadlastQuarterFinish(FullMoon moon);
    }

}

