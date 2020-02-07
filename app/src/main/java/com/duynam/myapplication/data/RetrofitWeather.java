package com.duynam.myapplication.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWeather {

    public static WeatherServices weather;
    public static WeatherServices getInstance(){
        if (weather == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.weatherunlocked.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weather = retrofit.create(WeatherServices.class);
        }
        return weather;
    }
}
