package com.duynam.myapplication.view.home;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import androidx.databinding.ObservableDouble;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.httpUrlConnection.GetCurrentWeatherHttp;
import com.duynam.myapplication.httpUrlConnection.GetForecastWeatherHttp;
import com.duynam.myapplication.httpUrlConnection.GetWeatherCityHttp;
import com.duynam.myapplication.model.CurrenWeather;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.untils.Constant;
import com.duynam.myapplication.untils.Utils;

import java.util.List;

import im.delight.android.location.SimpleLocation;

public class HomeActivityViewModel extends ViewModel {

    private Context context;
    public ObservableDouble temp = new ObservableDouble();
    public ObservableField<String> desc = new ObservableField<>();
    public ObservableField<String> sunriseTime = new ObservableField<>();
    public ObservableField<String> sunsetTime = new ObservableField<>();
    public ObservableDouble percent_rain = new ObservableDouble();
    public ObservableDouble rain_mm = new ObservableDouble();
    public ObservableDouble dew_poin = new ObservableDouble();
    public ObservableDouble humid = new ObservableDouble();
    public ObservableDouble real_feel = new ObservableDouble();
    public ObservableDouble wind_speed = new ObservableDouble();
    public ObservableInt wind_deg = new ObservableInt();
    private OnLoadList onLoadList;
    private DatabaseHelper databaseHelper;
    private CurrenWeatherDAO dao;
    private Result result;
    private CurrenLocalCity localCity;
    private SimpleLocation simpleLocation;


    public void setOnLoadList(OnLoadList onLoadList) {
        this.onLoadList = onLoadList;
    }

    public HomeActivityViewModel(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.dao = new CurrenWeatherDAO(databaseHelper);
        this.simpleLocation = new SimpleLocation(context);
    }

    public void getCurrenData(String latlong) {
        GetCurrentWeatherHttp getCurrentWeatherHttp = new GetCurrentWeatherHttp();
        getCurrentWeatherHttp.execute("http://api.weatherunlocked.com/api/current/" + latlong + "?" + "app_id=" + Constant.app_id +
                "&" + "app_key=" + Constant.app_key);
        GetCurrentWeatherHttp.OnLoadData onLoadData = new GetCurrentWeatherHttp.OnLoadData() {
            @Override
            public void onLoadFinish(CurrenWeather currenWeather) {
                temp.set(currenWeather.getTempC());
                desc.set(currenWeather.getWxDesc());
                dew_poin.set(currenWeather.getDewpointC());
                humid.set(currenWeather.getHumidPct());
                real_feel.set(currenWeather.getFeelslikeC());
                wind_speed.set(currenWeather.getWindspdKmh());
                wind_deg.set(currenWeather.getWinddirdeg());
            }
        };
        getCurrentWeatherHttp.setOnLoadData(onLoadData);
    }

    private String simplelocation() {
        String latlong;
        SimpleLocation simpleLocation = new SimpleLocation(context);
        latlong = simpleLocation.getLatitude() + "," + simpleLocation.getLongitude();
        return latlong;
    }

    public void getForecastData(String latlong) {
        GetForecastWeatherHttp getForecastWeatherHttp = new GetForecastWeatherHttp();
        getForecastWeatherHttp.execute("http://api.weatherunlocked.com/api/forecast/" +
                latlong + "?" + "app_id=" + Constant.app_id + "&" + "app_key=" + Constant.app_key);
        GetForecastWeatherHttp.OnLoadForecast onLoadForecast = new GetForecastWeatherHttp.OnLoadForecast() {
            @Override
            public void LoadForecast(List<Day> dayListhttp) {
                onLoadList.getDayList(dayListhttp);
                sunsetTime.set(dayListhttp.get(0).getSunsetTime());
                sunriseTime.set(dayListhttp.get(0).getSunriseTime());
                percent_rain.set(dayListhttp.get(0).getProbPrecipPct());
                rain_mm.set(dayListhttp.get(0).getPrecipTotalMm());
            }

            @Override
            public void LoadTimeFrame(List<Timeframe> timeframeshttp) {
                onLoadList.getTimeList(timeframeshttp);
            }
        };
        getForecastWeatherHttp.setOnLoadForecast(onLoadForecast);
    }

    public void addCitytoMenu(double latitude, double longtitude) {
        GetWeatherCityHttp http = new GetWeatherCityHttp();
        http.execute(Constant.OPENWEATHER + "?" + "lat=" + latitude + "&" + "lon=" + longtitude + "&" + Constant.APPID_OPENWEATHER);
        GetWeatherCityHttp.OnLoadComplete onLoadComplete = new GetWeatherCityHttp.OnLoadComplete() {
            @Override
            public void onListLoadComplete(CurrenLocalCity localCityList) {
                dao.insertCity(localCityList);
            }
        };
        http.setOnLoadComplete(onLoadComplete);
    }

    public void checkLocationgetData(Activity activity, TextView textView) {
        result = activity.getIntent().getParcelableExtra("latlongcity");
        localCity = activity.getIntent().getParcelableExtra("currenLocalCity");
        if (result == null && localCity == null) {
            getCurrenData(simplelocation());
            getForecastData(simplelocation());
            textView.setText(Utils.setCurrentNameCity(context, simpleLocation.getLatitude(), simpleLocation.getLongitude()));
        } else if (result != null){
            addCitytoMenu(Double.parseDouble(result.getLat()), Double.parseDouble(result.getLon()));
            String latlong = result.getLat() + "," + result.getLon();
            getCurrenData(latlong);
            getForecastData(latlong);
            textView.setText(result.getName());
        }else {
            String latlong = localCity.getLatitude() + "," + localCity.getLongtitude();
            getCurrenData(latlong);
            getForecastData(latlong);
            textView.setText(localCity.getName());
        }
    }


    public interface OnLoadList {
        void getDayList(List<Day> days);
        void getTimeList(List<Timeframe> timeframes);
    }

    //    public void get7dayForecast(String latlong) {
//        RetrofitWeather.getInstance().get7dayWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<Forecast>() {
//            @Override
//            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
//                daylistFull.addAll(response.body().getDays());
//                adapter.setData(daylistFull.subList(0, 3));
//                day.setSunriseTime(daylistFull.get(0).getSunriseTime());
//                day.setSunsetTime(daylistFull.get(0).getSunsetTime());
//                homeBinding.setDay(day);
//                timeframeList.addAll(response.body().getDays().get(0).getTimeframes());
//                homeBinding.setTimeframe(timeframeList.get(0));
//                adapter.notifyDataSetChanged();
//                hourAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Forecast> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }

    //    public void getCurrentWeather(String latlong) {
//        RetrofitWeather.getInstance().getCurrenWeather(latlong, Constant.app_id, Constant.app_key).enqueue(new Callback<CurrenWeather>() {
//            @Override
//            public void onResponse(Call<CurrenWeather> call, Response<CurrenWeather> response) {
//                if (response.code() == 200) {
//                    latitude = response.body().getLat();
//                    longtitude = response.body().getLon();
//                    currenWeather.setDewpointC(response.body().getDewpointC());
//                    currenWeather.setTempC(response.body().getTempC());
//                    currenWeather.setWxDesc(response.body().getWxDesc());
//                    currenWeather.setFeelslikeC(response.body().getFeelslikeC());
//                    currenWeather.setHumidPct(response.body().getHumidPct());
//                    currenWeather.setWindspdKmh(response.body().getWindspdKmh());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CurrenWeather> call, Throwable t) {
//
//            }
//        });
//    }

}
