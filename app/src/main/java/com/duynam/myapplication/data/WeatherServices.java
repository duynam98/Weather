package com.duynam.myapplication.data;

import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.searchCity.CityListResult;
import com.duynam.myapplication.model.sevendayweather.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherServices {

    @Headers("Accept: application/json")
    @GET("api/current/{latlong}")
    Call<CurrenWeather> getCurrenWeather(@Path("latlong") String latlong, @Query("app_id") String app_id, @Query("app_key") String app_key);

    @Headers("Accept: application/json")
    @GET("api/forecast/{latlong}")
    Call<Forecast> get7dayWeather(@Path("latlong") String latlong, @Query("app_id") String app_id, @Query("app_key") String app_key);

    @Headers("x-rapidapi-key: fb3a7f902emsh0c3934aa317e3d0p1eaff7jsn0825f131f08c")
    @GET("/latlon.php")
    Call<CityListResult> searchCity(@Query("location") String location);

}
